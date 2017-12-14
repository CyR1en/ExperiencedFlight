package us.cyrien.experiencedflight.entity;

import org.bukkit.entity.Player;
import us.cyrien.experiencedflight.ExperiencedFlight;

import java.util.HashMap;
import java.util.Map;

public class AirTrafficController {

    private ExperiencedFlight expFlight;
    private Map<Player, Pilot> flightList;

    public AirTrafficController(ExperiencedFlight expFlight) {
        flightList = new HashMap<>();
        this.expFlight = expFlight;
    }

    public void addFlight(Player p) {
        Pilot passenger = new Pilot(expFlight, p);
        flightList.put(p, passenger);
    }

    public Map<Player, Pilot> getFlightList() {
        return flightList;
    }

    public void removeFlight(Player p) {
        flightList.remove(p);
    }

    public Pilot getFlight(Player p) {
        return flightList.get(p);
    }

    public void cancelAllFlightsClearance(String reason) {
        flightList.forEach((pl, psngr) -> psngr.cancelClearance(reason));
    }

    public boolean hasClearance(Player p) {
        return flightList.containsKey(p);
    }


}
