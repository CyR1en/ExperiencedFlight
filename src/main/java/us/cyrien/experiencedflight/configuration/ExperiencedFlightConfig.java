package us.cyrien.experiencedflight.configuration;

import us.cyrien.mcutils.config.BaseConfig;
import us.cyrien.mcutils.config.ConfigManager;

public class ExperiencedFlightConfig extends BaseConfig {

    public ExperiencedFlightConfig(ConfigManager configManager, String[] header) {
        super(configManager, header);
    }

    public ExperiencedFlightConfig(ConfigManager configManager, String[] header, boolean regen) {
        super(configManager, header, regen);
    }

    @Override
    public void initialize() {
        String[] commentArr;
        if (config.get("Economy-Class-Cost") == null) {
            commentArr = new String[]{"Cost of flight for players", "with expflight.economy permission", "Can only be greater than 1"};
            config.set("Economy-Class-Cost", 1.9, commentArr);
            config.saveConfig();
        }
        if (config.get("Economy-Class-Speed") == null) {
            commentArr = new String[]{"Flight speed for players", "with expflight.economy permission", "Can only be from 0 to 1"};
            config.set("Economy-Class-Speed", 0.1f, commentArr);
            config.saveConfig();
        }
        if (config.get("Business-Class-Cost") == null) {
            commentArr = new String[]{"Cost of flight for players", "with expflight.business permission", "Can only be greater than 1"};
            config.set("Business-Class-Cost", 1.3, commentArr);
            config.saveConfig();
        }
        if (config.get("Business-Class-Speed") == null) {
            commentArr = new String[]{"Flight speed for players", "with expflight.business permission", "Can only be from 0 to 1"};
            config.set("Business-Class-Speed", 0.15f, commentArr);
            config.saveConfig();
        }
        if (config.get("First-Class-Cost") == null) {
            commentArr = new String[]{"Cost of flight for players", "with expflight.firstclass permission", "Can only be greater than 1"};
            config.set("First-Class-Cost", 1.1, commentArr);
            config.saveConfig();
        }
        if (config.get("First-Class-Speed") == null) {
            commentArr = new String[]{"Flight speed for players", "with expflight.firstclass permission", "Can only be from 0 to 1"};
            config.set("First-Class-Speed", 0.2f, commentArr);
            config.saveConfig();
        }
        if (config.get("Auto-Disable") == null) {
            commentArr = new String[]{"Auto disable ExperiencedFlight", "when a player lands", "Can only be true or false"};
            config.set("Auto-Disable", false, commentArr);
            config.saveConfig();
        }
        if (config.get("Blacklisted-Worlds") == null) {
            commentArr = new String[]{"Worlds where you don't", "want ExperiencedFlight to be used."};
            config.set("Blacklisted-Worlds", new String[]{"worldName", "worldName", "etc"}, commentArr);
            config.saveConfig();
        }
    }
}
