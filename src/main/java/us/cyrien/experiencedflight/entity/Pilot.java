package us.cyrien.experiencedflight.entity;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.cyrien.experiencedflight.ExperiencedFlight;
import us.cyrien.experiencedflight.FlightClass;
import us.cyrien.experiencedflight.Messenger;

public class Pilot {

    private ExperiencedFlight expFlight;
    private AirTrafficController atc;
    private Player passenger;
    private FlightClass flightClass;
    private boolean onAir;
    private boolean isPaused;
    private boolean allowDamage;

    private boolean inBed;


    public Pilot(ExperiencedFlight expFlight, Player p) {
        this.expFlight = expFlight;
        atc = expFlight.getATC();
        flightClass = expFlight.getFlightClass(p);
        passenger = p;
        onAir = false;
        isPaused = false;
        allowDamage = false;
    }

    public void cancelClearance() {
        cancelClearance(null);
    }

    public void giveClearance() {
        passenger.setAllowFlight(true);
        passenger.setFlySpeed(flightClass.getSpeed());
        Messenger.sendMsg(passenger, "Your flight class is : " + ChatColor.GOLD + flightClass.toString());
        Messenger.sendMsg(passenger, "Your flight cost is : " + ChatColor.GOLD + flightClass.getCost());
        Messenger.sendMsg(passenger, "Your flight speed is : " + ChatColor.GOLD + passenger.getFlySpeed());
    }

    public void cancelClearance(String reason) {
        passenger.setAllowFlight(false);
        passenger.setFlying(false);
        passenger.setFlySpeed(0.1f);
        setOnAir(false);
        if (reason != null)
            Messenger.sendWarning(passenger, "your flight have been cancelled because " + reason);
    }

    public Player getPassenger() {
        return passenger;
    }

    public void setPaused(boolean b) {
        isPaused = b;
    }

    public void setOnAir(boolean b) {
        onAir = b;
    }

    public void setAllowDamage(boolean b) {
        allowDamage = b;
    }

    public boolean getAllowDamage() {
        return allowDamage;
    }

    public boolean isOnAir() {
        return onAir;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isInBed() {
        return inBed;
    }

    public void setInBed(boolean inBed) {
        this.inBed = inBed;
    }


    public FlightClass getFlightClass() {
        return flightClass;
    }


    public boolean isFlying() {
        return passenger.isFlying();
    }

    public boolean hasClearance() {
        return atc.hasClearance(this.passenger);
    }
}
