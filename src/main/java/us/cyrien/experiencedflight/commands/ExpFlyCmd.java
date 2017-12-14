package us.cyrien.experiencedflight.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.experiencedflight.Messenger;
import us.cyrien.experiencedflight.entity.AirTrafficController;
import us.cyrien.experiencedflight.handle.BlacklistManager;
import us.cyrien.mcutils.annotations.Command;
import us.cyrien.mcutils.annotations.Permission;
import us.cyrien.mcutils.annotations.Sender;

public class ExpFlyCmd {

    @Command(aliases = {"expfly", "xpfly", "xfly", "xf"}, usage = "/expfly", desc = "Fly using experience.")
    @Permission("expflight.expfly")
    public void command(@Sender CommandSender sender) {
        BlacklistManager bm = ExperiencedFlight.getInstance().getBlacklistManager();
        if (!(sender instanceof Player)) {
            Messenger.sendErr(sender, "Only players can access this command");
        } else {
            Player p = (Player) sender;
            if (p.getLevel() == 0) {
                Messenger.sendWarning(p, "You don't have enough levels to fly");
                return;
            } else if (p.getGameMode().equals(GameMode.CREATIVE) || p.getGameMode().equals(GameMode.SPECTATOR)) {
                Messenger.sendWarning(p, "You can only use ExperiencedFlight in survival game modes");
                return;
            } else if (bm.contains(p.getWorld())) {
                Messenger.sendWarning(p, "This world is a blacklisted by the Air Traffic Controller");
                return;
            }
            AirTrafficController atc = ExperiencedFlight.getInstance().getATC();
            boolean enabled = atc.getFlight(p) != null;
            if (enabled) {
                atc.getFlight(p).cancelClearance();
                atc.removeFlight(p);
                Messenger.sendMsg(p, ChatColor.RED + "Disabled ExperiencedFlight" + ChatColor.RESET + ". Thanks for flying with ExperiencedFlight!");
            } else {
                atc.addFlight(p);
                atc.getFlight(p).giveClearance();
                Messenger.sendMsg(p, ChatColor.GREEN + "Enabled Experienced Flight.");
            }
        }
    }
}
