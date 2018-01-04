
package be.mdelbar.geneticcells;

import be.mdelbar.geneticcells.ability.Ability;
import be.mdelbar.geneticcells.ability.AbilityPoints;
import be.mdelbar.geneticcells.config.ConfigFactory;
import java.util.Random;

/**
 * Separate utility class with static methods for the genetic part.
 * Not necessary to separate this into its own class (could just be in Cell), but I wanted to.
 * Plus, this way promotes a stateless (static) approach which is a nice bonus.
 *
 * @author Matthias Delbar
 */
public class GeneticUtils {

    public static Cell crossover(Cell c1, Cell c2) {
        // Basic/quick implementation until we figure out how to implement decent reproduction: just copy one of the parents
        Random r = ConfigFactory.getInstance().getRandom();
        if(r.nextBoolean()) {
            return new Cell(c1);
        }
        else {
            return new Cell(c2);
        }
    }
    
    public static Cell mutate(Cell c) {
        Cell cMut = new Cell(c);
        Random r = ConfigFactory.getInstance().getRandom();
        
        // Get random ability to alter
        Ability targetAbility = Ability.values()[r.nextInt(Ability.values().length)];
        AbilityPoints ap = cMut.getAp();
        if(r.nextBoolean()) {
            // Increase random AP by random amount (taken from AP pool), at least 1
            // Only do if we have points to decrease
            if(ap.getPool() > 0) {
                ap.increaseAP(targetAbility, r.nextInt(ap.getPool()) + 1);
            }
        }
        else {
            // Decrease random AP by random amount (goes to AP pool), at least 1
            // Only do if we have points to decrease
            int points = ap.getAP(targetAbility);
            if(points > 0) {
                ap.decreaseAP(targetAbility, r.nextInt(points) + 1);
            }
        }
        
        return cMut;
    }
    
    public static int calculateFitness(Cell c) {
        // Basic/quick implementation until we add organisms and environment.
        // Fitness = energy production - energy consumption
        return c.calculateEnergyProduction() - c.calculateEnergyConsumption();
    }
    
}
