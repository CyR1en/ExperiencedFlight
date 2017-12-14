package us.cyrien.experiencedflight.configuration;

import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.mcutils.config.Config;
import us.cyrien.mcutils.config.ConfigManager;

public class ExperiencedFlightConfigManager {

    private final String[] EXPFLIGHT_CONFIG_HEADER = new String[]{"ExperiencedFlight Configuration", "   ","Monetizing Flights using experience"};

    private ExperiencedFlight experiencedFlight;
    private ExperiencedFlightConfig experiencedFlightConfig;
    private ConfigManager configManager;

    public ExperiencedFlightConfigManager(ExperiencedFlight experiencedFlight, ConfigManager configManager) {
        this.configManager = configManager;
        experiencedFlightConfig = new ExperiencedFlightConfig(configManager, EXPFLIGHT_CONFIG_HEADER);
    }

    public boolean setupConfigurations() {
        return experiencedFlightConfig.init();
    }

    public void reloadAllConfig() {
        experiencedFlightConfig.getConfig().reloadConfig();
    }

    public Config getExperiencedFlightConfig() {
        return experiencedFlightConfig.getConfig();
    }

}
