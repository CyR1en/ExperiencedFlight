package us.cyrien.mcutils.config;

import us.cyrien.mcutils.logger.Logger;

import java.io.File;

public abstract class BaseConfig {

    private final String[] header;
    private ConfigManager configManager;
    private boolean regen;

    protected Config config;

    public BaseConfig(ConfigManager configManager, String[] header) {
        this(configManager, header, false);
    }

    public BaseConfig(ConfigManager configManager, String[] header, boolean regen) {
        this.configManager = configManager;
        this.header = header;
        this.regen = regen;
    }

    public boolean init() {
        File f = configManager.getConfigFile(this.getClass().getSimpleName() + ".yml");
        if (!f.exists()) {
            config = configManager.getNewConfig(this.getClass().getSimpleName() + ".yml", header);
            initialize();
            Logger.warn(this.getClass().getSimpleName() + ".yml" + " have been generated or new fields have been added. " +
                    "Please make sure to fill in all config fields correctly.");
            return false;
        } else if (f.exists() && regen) {
            boolean deleted = f.delete();
            if(deleted) {
                config = configManager.getNewConfig(this.getClass().getSimpleName() + ".yml", header);
                initialize();
                Logger.warn(this.getClass().getSimpleName() + ".yml" + " have been re-generated. Old data have been erased.");
                return false;
            }
        }
        config = configManager.getNewConfig(this.getClass().getSimpleName() + ".yml", header);
        initialize();
        return true;
    }

    public abstract void initialize();

    public Config getConfig() {
        return config;
    }
}
