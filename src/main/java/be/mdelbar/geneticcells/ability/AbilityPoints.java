
package be.mdelbar.geneticcells.ability;

import be.mdelbar.geneticcells.config.ConfigFactory;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Matthias Delbar
 */
public class AbilityPoints {

    private Map<Ability, Integer> abilities;
    private int pool;
    
    public AbilityPoints() {
        pool = ConfigFactory.getInstance().getInitialAbilityPoints();
        abilities = new HashMap<>();
        for(Ability ab : Ability.values()) {
            abilities.put(ab, 0);
        }
    }
    
    // Copy constructor
    public AbilityPoints(AbilityPoints old) {
        this.abilities = new HashMap<>(old.abilities);
        pool = old.pool;
    }
    
    public int getAP(Ability ability) {
        return abilities.get(ability);
    }
    
    public void increaseAP(Ability ability, int number) {
        if(number > pool) {
            throw new IllegalArgumentException("Not enough Ability Points left in pool!");
        }
        pool -= number;
        abilities.put(ability, abilities.get(ability) + number);
    }
    
    public void decreaseAP(Ability ability, int number) {
        int ap = abilities.get(ability);
        if(number > ap) {
            throw new IllegalArgumentException("Can't remove that many AP for this ability!");
        }
        abilities.put(ability, ap - number);
        pool += number;
    }

    
    
    public int getPool() {
        return pool;
    }
    
}
