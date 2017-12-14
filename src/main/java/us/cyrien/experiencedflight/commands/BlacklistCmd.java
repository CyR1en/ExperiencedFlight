package us.cyrien.experiencedflight.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.experiencedflight.Messenger;
import us.cyrien.experiencedflight.handle.BlacklistManager;
import us.cyrien.mcutils.annotations.Command;
import us.cyrien.mcutils.annotations.Optional;
import us.cyrien.mcutils.annotations.Permission;
import us.cyrien.mcutils.annotations.Sender;

public class BlacklistCmd {

    @Command(aliases = {"blacklist", "bl"}, usage = "/blacklist <add|remove> [world]", desc = "Don't allow flight in a specific world")
    @Permission("expflight.blacklist")
    public void onCommand(@Sender CommandSender cs, String arg0, @Optional String world) {
        if (arg0.isEmpty() || (!arg0.equalsIgnoreCase("add") && !arg0.equalsIgnoreCase("remove"))) {
            Messenger.sendWarning(cs, "Invalid sub-command. remove or add world?");
            return;
        }
        BlacklistManager blacklistManager = ExperiencedFlight.getInstance().getBlacklistManager();
        if (arg0.equalsIgnoreCase("add")) {
            if (world == null)
                if (!(cs instanceof Player))
                    Messenger.sendWarning(cs, "As console, you need to provide a world to blacklist.");
                else {
                    World w = ((Player) cs).getWorld();
                    if (blacklistManager.contains(w))
                        Messenger.sendWarning(cs, "Flight is already black listed in this world.");
                    else {
                        blacklistManager.add(w);
                        Messenger.sendMsg(cs, ChatColor.DARK_AQUA + w.getName() + ChatColor.RESET + " have been " + ChatColor.DARK_GREEN + "added" + ChatColor.RESET + " to the list of blacklisted worlds.");
                    }
                }
            else {
                World w = Bukkit.getWorld(world);
                if (w == null)
                    Messenger.sendErr(cs, "The world that you provided does not exist.");
                else {
                    if (blacklistManager.contains(w))
                        Messenger.sendWarning(cs, "Flight is already black listed in this world.");
                    else {
                        blacklistManager.add(w);
                        Messenger.sendMsg(cs, ChatColor.DARK_AQUA + w.getName() + ChatColor.RESET + " have been " + ChatColor.DARK_GREEN + "added" + ChatColor.RESET + " to the list of blacklisted worlds.");
                    }
                }
            }
        } else {
            if (world == null)
                if (!(cs instanceof Player))
                    Messenger.sendWarning(cs, "As console, you need to provide a world to remove on the blacklist.");
                else {
                    World w = ((Player) cs).getWorld();
                    if (!blacklistManager.contains(w))
                        Messenger.sendWarning(cs, "This world is not blacklisted.");
                    else {
                        blacklistManager.remove(w);
                        Messenger.sendMsg(cs, ChatColor.DARK_AQUA + w.getName() + ChatColor.RESET + " have been " + ChatColor.DARK_RED + "removed " + ChatColor.RESET + "to the list of blacklisted worlds.");
                    }
                }
            else {
                World w = Bukkit.getWorld(world);
                if (w == null)
                    Messenger.sendErr(cs, "The world that you provided does not exist.");
                else {
                    if (!blacklistManager.contains(w))
                        Messenger.sendWarning(cs, "This world is not blacklisted.");
                    else {
                        blacklistManager.remove(w);
                        Messenger.sendMsg(cs, ChatColor.DARK_AQUA + w.getName() + ChatColor.RESET + " have been " + ChatColor.DARK_RED + "removed " + ChatColor.RESET + "to blacklisted worlds.");
                        ;
                    }
                }
            }
        }
    }
}
