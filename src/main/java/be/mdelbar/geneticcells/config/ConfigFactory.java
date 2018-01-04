
package be.mdelbar.geneticcells.config;

/**
 *
 * @author Matthias Delbar
 */
public class ConfigFactory {

    private static Config instance;
    
    public static Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
}
