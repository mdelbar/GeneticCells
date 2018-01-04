
package be.mdelbar.geneticcells;

import be.mdelbar.geneticcells.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Matthias Delbar
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) {
        logger.info("Running Genetic Cells");
        // Ignore args for now, future implementations will allow us to set config this way
        
        // Create population
        Cell[] population = new Cell[ConfigFactory.getInstance().getPopulationLimit()];
        for(int i = 0; i < ConfigFactory.getInstance().getPopulationLimit(); i++) {
            population[i] = new Cell();
        }
        
        logger.info("===========================================");
        logger.info("Starting population: {}", new Object[]{population});
        logger.info("===========================================");
        
        // Evolve population for N generations (set in config)
        for(int i = 0; i < ConfigFactory.getInstance().getRunGenerations(); i++) {
            if(i % 100 == 0) {
                logger.debug("Population at generation {}: {}", i, population);
            }
            population = evolve(population);
        }
        
        logger.info("===========================================");
        logger.info("Final population: {}", new Object[]{population});
        logger.info("===========================================");
    }
    
    private static Cell[] evolve(Cell[] population) {
        Cell[] extendedPop = extendPopulation(population);
        int[] fitnesses = calculateFitness(extendedPop);
        Cell[] orderedPop = orderPopulation(extendedPop, fitnesses);
        return reducePopulation(orderedPop, ConfigFactory.getInstance().getPopulationLimit());
    }
    

    private static Cell[] extendPopulation(Cell[] pop) {
        // We will add 1 child Cell for every 2 parents
        Cell[] newPop = new Cell[pop.length + pop.length/2];

        // Use arraycopy for SUPER PERFORMANCE AW YISS
        System.arraycopy(pop, 0, newPop, 0, pop.length);

        for(int i = 0; i < pop.length; i += 2) {
            Cell child = reproduce(newPop[i], newPop[i+1]);
            newPop[pop.length + i/2] = child;
        }

        return newPop;
    }
    
    private static int[] calculateFitness(Cell[] population) {
        int[] fitnesses = new int[population.length];
        for(int i = 0; i < population.length; i++) {
            fitnesses[i] = GeneticUtils.calculateFitness(population[i]);
        }
        return fitnesses;
    }
    
    private static Cell[] orderPopulation(Cell[] pop, int[] fitnesses) {
        // Do a simple sort based on previously calculated fitnesses.
        // We can't rely on Java's Compare method to properly cache fitness results (and calculating fitness is expensive).
        boolean swapped = true;
        for(int elsToCmp = fitnesses.length - 2; swapped && elsToCmp >= 0; elsToCmp--) {
            swapped = false;
            for(int i = 0; i <= elsToCmp; i++) {
                if(fitnesses[i] < fitnesses[i+1]) {
                    swapped = true;
                    // Swap fitnesses
                    int tmpFitness = fitnesses[i];
                    fitnesses[i] = fitnesses[i+1];
                    fitnesses[i+1] = tmpFitness;
                    // Swap wallpapers
                    Cell tmpCell = pop[i];
                    pop[i] = pop[i+1];
                    pop[i+1] = tmpCell;
                }
            }
        }

        return pop;
    }

    private static Cell reproduce(Cell parent1, Cell parent2) {
        // Crossover parents (generates 1 child)
        Cell child = GeneticUtils.crossover(parent1, parent2);

        // Mutate child
        return GeneticUtils.mutate(child);
    }
    
    private static Cell[] reducePopulation(Cell[] pop, int limit) {
        Cell[] newPop = new Cell[limit];

        // Use arraycopy for SUPER PERFORMANCE AW YISS
        System.arraycopy(pop, 0, newPop, 0, limit);

        return newPop;
    }
    
}
