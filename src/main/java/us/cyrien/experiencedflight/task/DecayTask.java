package us.cyrien.experiencedflight.task;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.experiencedflight.Messenger;
import us.cyrien.experiencedflight.entity.Pilot;
import us.cyrien.experiencedflight.handle.ExperienceManager;

import java.util.Map;

public class DecayTask extends BukkitRunnable {

    private ExperiencedFlight expFlight;

    public DecayTask(ExperiencedFlight expFlight) {
        this.expFlight = expFlight;
    }

    private boolean inLiquid(Player p) {
        Material m = p.getLocation().getBlock().getType();
        return m == Material.STATIONARY_WATER || m == Material.WATER || m == Material.LAVA || m == Material.STATIONARY_LAVA;
    }

    private boolean closeToGround(Player player) {
        Location l = player.getLocation();
        int hY = l.getWorld().getHighestBlockYAt(l);
        int diff = (int) (l.getY() - hY);
        //System.out.println("hY = " + hY + " | pY = " + new DecimalFormat("#0.0").format(l.getY()) + " | Diff = " + diff);
        return diff < 4;
    }

    private boolean inValidWord(World world) {
        return expFlight.getBlacklistManager().contains(world);
    }


    @Override
    public void run() {
        for (Map.Entry<Player, Pilot> flights : expFlight.getATC().getFlightList().entrySet()) {
            Player player = flights.getKey();
            Pilot pilot = flights.getValue();
            if (player.isFlying() && !player.isInsideVehicle() && !pilot.isInBed() && !inValidWord(player.getWorld()) && !player.isDead() && !pilot.isPaused() && !(player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR))) {
                float currentExp = ExperienceManager.getExp(player);
                if (player.getLevel() == 0 && currentExp < pilot.getFlightClass().getCost()) {
                    pilot.cancelClearance("you ran out of experience");
                    expFlight.getATC().removeFlight(player);
                    continue;
                }
                ExperienceManager.changeExp(player, -pilot.getFlightClass().getCost());
                pilot.setOnAir(true);
                pilot.setPaused(false);
            } else if ((player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) && pilot.hasClearance()) {
                player.setFlySpeed(0.1f);
                expFlight.getATC().removeFlight(player);
                Messenger.sendMsg(player, ChatColor.RED + "Disabled ExperiencedFlight" + ChatColor.RESET + ". You're no longer in survival");
            } else if (player.isFlying() && !pilot.hasClearance()) {
                pilot.cancelClearance("you disabled ExperiencedFlight");
                Messenger.sendMsg(player, ChatColor.RED + "Disabled ExperiencedFlight");
            } else if (!player.isFlying() && pilot.isOnAir()) {
                if (expFlight.getExperiencedFlightConfigManager().getExperiencedFlightConfig().getBoolean("Auto-Disable") && (player.isOnGround() || inLiquid(player))) {
                    pilot.cancelClearance(null);
                    expFlight.getATC().removeFlight(player);
                    Messenger.sendMsg(player, ChatColor.RED + "Disabled ExperiencedFlight." + ChatColor.RESET + ". You landed");
                } else if (!pilot.isPaused() && player.getAllowFlight()) {
                    pilot.setPaused(true);
                    player.setFlying(false);
                    Messenger.sendMsg(player, "Your flight have been " + ChatColor.GOLD + "paused" + ChatColor.RESET);
                } else if (pilot.isPaused() && player.getAllowFlight()) {
                    if (closeToGround(player)) {
                        pilot.setOnAir(false);
                        player.setAllowFlight(false);
                        pilot.setAllowDamage(true);
                    }
                }
            } else if (player.isFlying() && pilot.isPaused()) {
                pilot.setOnAir(false);
                pilot.setPaused(false);
                Messenger.sendMsg(player, "Your flight have been " + ChatColor.GREEN + "continued" + ChatColor.RESET);
            } else if ((player.isOnGround() || inLiquid(player)) && pilot.isPaused() && !player.getAllowFlight() && pilot.getAllowDamage()) {
                pilot.setAllowDamage(false);
                Bukkit.getScheduler().runTaskLater(expFlight, () -> player.setAllowFlight(true), 2L);
            } else if (player.isDead()) {
                Bukkit.getScheduler().runTaskLater(expFlight, () -> pilot.cancelClearance("you died"), 2L);
                expFlight.getATC().removeFlight(player);
            } else if (pilot.isInBed() && player.isFlying()) {
                pilot.setPaused(true);
                player.setFlying(false);
                pilot.setOnAir(false);
                Messenger.sendMsg(player, "Your flight have been " + ChatColor.GOLD + "paused" + ChatColor.RESET);
            } else if (!pilot.isInBed() && pilot.isPaused() && pilot.isInBed()) {
                pilot.setPaused(false);
                Messenger.sendMsg(player, "Your flight have been " + ChatColor.GREEN + "continued" + ChatColor.RESET);
            } else if (inValidWord(player.getWorld()) && player.isFlying()) {
                pilot.cancelClearance("ExperiencedFlight is blacklisted in this world");
                expFlight.getATC().removeFlight(player);
            }
        }

    }
}
