package us.cyrien.experiencedflight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.cyrien.experiencedflight.commands.*;
import us.cyrien.experiencedflight.configuration.ExperiencedFlightConfigManager;
import us.cyrien.experiencedflight.entity.AirTrafficController;
import us.cyrien.experiencedflight.handle.BlacklistManager;
import us.cyrien.experiencedflight.listener.BedListener;
import us.cyrien.experiencedflight.listener.UserQuitListener;
import us.cyrien.experiencedflight.task.DecayTask;
import us.cyrien.mcutils.Frame;
import us.cyrien.mcutils.config.Config;
import us.cyrien.mcutils.config.ConfigManager;

public class ExperiencedFlight extends JavaPlugin {

    private static ExperiencedFlight experiencedFlight;

    private ExperiencedFlightConfigManager experiencedFlightConfigManager;
    private BlacklistManager blacklistManager;
    private AirTrafficController airTrafficController;
    private ConfigManager configManager;
    private DecayTask decayTask;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        experiencedFlightConfigManager = new ExperiencedFlightConfigManager(this, configManager);
        experiencedFlightConfigManager.setupConfigurations();
        airTrafficController = new AirTrafficController(this);
        blacklistManager = new BlacklistManager(this);
        experiencedFlight = this;
        decayTask = new DecayTask(this);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            initCommands();
            initListener();
            Frame.main();
            startDecay();
            blacklistManager.init();
        }, 1L);
    }

    private void initCommands() {
        Frame.addModule(ExpFlyCmd.class);
        Frame.addModule(GiveLvlCmd.class);
        Frame.addModule(ExpFlightCmnd.class);
        Frame.addModule(GiveXpCmd.class);
        Frame.addModule(BlacklistCmd.class);
    }

    private void initListener() {
        Bukkit.getPluginManager().registerEvents(new BedListener(airTrafficController), this);
        Bukkit.getPluginManager().registerEvents(new UserQuitListener(this), this);
    }

    @Override
    public void onDisable() {
        airTrafficController.cancelAllFlightsClearance("server shutting down or reloading");
    }


    public void startDecay() {
        decayTask.runTaskTimer(this, 1, 2);
    }

    public static ExperiencedFlight getInstance() {
        return experiencedFlight;
    }

    public Config getExpFlightConfig() {
        return experiencedFlightConfigManager.getExperiencedFlightConfig();
    }

    public ExperiencedFlightConfigManager getExperiencedFlightConfigManager() {
        return experiencedFlightConfigManager;
    }

    public AirTrafficController getATC() {
        return airTrafficController;
    }

    public BlacklistManager getBlacklistManager() {
        return blacklistManager;
    }

    public FlightClass getFlightClass(Player p) {
        if (p.hasPermission("expflight.firstclass"))
            return FlightClass.FIRST_CLASS;
        else if (p.hasPermission("expflight.business"))
            return FlightClass.BUSINESS_CLASS;
        else if (p.hasPermission("expflight.economy"))
            return FlightClass.ECONOMY_CLASS;
        else
            return FlightClass.TERRORIST;
    }
}
