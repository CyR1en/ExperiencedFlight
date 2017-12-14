package us.cyrien.experiencedflight.handle;

import org.bukkit.Bukkit;
import org.bukkit.World;
import us.cyrien.experiencedflight.ExperiencedFlight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlacklistManager {

    private ExperiencedFlight expFly;
    private Map<String, World> worlds;

    public BlacklistManager(ExperiencedFlight expFly) {
        this.expFly = expFly;
        worlds = new HashMap<>();
    }

    public void init() {
        List<String> l = (List<String>) expFly.getExpFlightConfig().getList("Blacklisted-Worlds");
        l.forEach(s -> {
            World w = Bukkit.getWorld(s);
            if(w != null)
                worlds.put(w.getName(), w);
        });
    }

    public void add(World world) {
        worlds.put(world.getName(),world);
        save();
    }

    public void remove(World world) {
        worlds.remove(world.getName(), world);
        save();
    }

    private void save() {
        List<String> l = new ArrayList<>();
        worlds.forEach((s, w) -> l.add(s));
        expFly.getExpFlightConfig().set("Blacklisted-Worlds", l);
        expFly.getExpFlightConfig().saveConfig();
        expFly.getExpFlightConfig().reloadConfig();
    }

    public boolean contains(World world) {
        return worlds.containsKey(world.getName());
    }
}
