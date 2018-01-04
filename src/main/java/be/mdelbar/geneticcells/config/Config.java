
package be.mdelbar.geneticcells.config;

import java.util.Random;

/**
 * Class tracking game config (base values etc).
 *
 * @author Matthias Delbar
 */
public class Config {

    // Random generator    
    private final Random random;
    
    // Run parameters
    private int seed = 42;
    private int runGenerations = 1000;
    private int populationLimit = 10;
    
    // AP config
    private int cellInitialAbilityPoints = 10;
    private int cellBaseResistanceCold = 0;
    private int cellBaseEnergyProduction = 5;
    private int cellBaseEnergyConsumption = 5;
    
    
    public Config() {
        random = new Random(seed);
    }


    public Random getRandom() {
        return random;
    }
    
    public int getInitialAbilityPoints() {
        return cellInitialAbilityPoints;
    }

    public void setCellInitialAbilityPoints(int cellInitialAbilityPoints) {
        this.cellInitialAbilityPoints = cellInitialAbilityPoints;
    }

    public int getCellBaseResistanceCold() {
        return cellBaseResistanceCold;
    }

    public void setCellBaseResistanceCold(int cellBaseResistanceCold) {
        this.cellBaseResistanceCold = cellBaseResistanceCold;
    }

    public int getCellBaseEnergyProduction() {
        return cellBaseEnergyProduction;
    }

    public void setCellBaseEnergyProduction(int cellBaseEnergyProduction) {
        this.cellBaseEnergyProduction = cellBaseEnergyProduction;
    }

    public int getCellBaseEnergyConsumption() {
        return cellBaseEnergyConsumption;
    }

    public void setCellBaseEnergyConsumption(int cellBaseEnergyConsumption) {
        this.cellBaseEnergyConsumption = cellBaseEnergyConsumption;
    }

    public int getRunGenerations() {
        return runGenerations;
    }

    public void setRunGenerations(int runGenerations) {
        this.runGenerations = runGenerations;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getPopulationLimit() {
        return populationLimit;
    }

    public void setPopulationLimit(int populationLimit) {
        this.populationLimit = populationLimit;
    }
    
}
