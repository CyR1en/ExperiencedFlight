package us.cyrien.experiencedflight.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import us.cyrien.experiencedflight.entity.AirTrafficController;

public class BedListener implements Listener {

    private AirTrafficController atc;

    public BedListener(AirTrafficController atc) {
        this.atc = atc;
    }

    @EventHandler
    public void onBedEnterEvent(PlayerBedEnterEvent event) {
        if (atc.hasClearance(event.getPlayer()))
            atc.getFlight(event.getPlayer()).setInBed(true);
    }

    @EventHandler
    public void onBedLeaveEvent(PlayerBedLeaveEvent event) {
        if (atc.hasClearance(event.getPlayer()))
            atc.getFlight(event.getPlayer()).setInBed(false);
    }
}
