package us.cyrien.experiencedflight.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.experiencedflight.entity.AirTrafficController;
import us.cyrien.experiencedflight.entity.Pilot;
import us.cyrien.mcutils.logger.Logger;

public class UserQuitListener implements Listener {

    private ExperiencedFlight expFlight;
    private AirTrafficController atc;

    public UserQuitListener(ExperiencedFlight expFlight) {
        this.expFlight = expFlight;
        atc = expFlight.getATC();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if(atc.getFlightList().containsKey(p)) {
            Pilot pilot = atc.getFlight(p);
            pilot.cancelClearance();
            atc.removeFlight(p);
            Logger.info(p.getName() + "'s flight clearance have been removed because the player left the game.");
        }
    }
}
