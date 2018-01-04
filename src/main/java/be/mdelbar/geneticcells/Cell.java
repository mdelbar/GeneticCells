
package be.mdelbar.geneticcells;

import be.mdelbar.geneticcells.ability.Ability;
import be.mdelbar.geneticcells.ability.AbilityPoints;
import be.mdelbar.geneticcells.config.ConfigFactory;
import java.awt.Color;

/**
 *
 * @author Matthias Delbar
 */
public class Cell {
    
    private static final int BASE_RESISTANCE_COLD = ConfigFactory.getInstance().getCellBaseResistanceCold();
    private static final int BASE_ENERGY_PRODUCTION = ConfigFactory.getInstance().getCellBaseEnergyProduction();
    private static final int BASE_ENERGY_CONSUMPTION = ConfigFactory.getInstance().getCellBaseEnergyConsumption();
    
    private AbilityPoints ap;

    
    // Empty constructor
    public Cell() {
        this.ap = new AbilityPoints();
    }
    
    // Copy constructor
    public Cell(Cell old) {
        this.ap = new AbilityPoints(old.ap);
    }
    
    
    public Color toColor() {
        return new Color(calculateResistanceCold(), calculateEnergyProduction(), calculateEnergyConsumption());
    }

    // Percentage scale, +10% resistance for every AP added
    public int calculateResistanceCold() {
        return BASE_RESISTANCE_COLD + 10*ap.getAP(Ability.RESISTANCE_COLD);
    }

    // Flat bonus to production
    public int calculateEnergyProduction() {
        return BASE_ENERGY_PRODUCTION + ap.getAP(Ability.ENERGY_PRODUCTION);
    }

    // Possible for a cell to have more than the "useful amount" of AP in energy consumption, but consumption is always at least 1
    public int calculateEnergyConsumption() {
        return Math.max(1, BASE_ENERGY_CONSUMPTION - ap.getAP(Ability.ENERGY_CONSUMPTION));
    }

    
    
    public AbilityPoints getAp() {
        return ap;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", calculateEnergyProduction(), calculateEnergyConsumption(), calculateResistanceCold());
    }

    
}
