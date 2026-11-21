package smartparking.service;

import smartparking.model.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class ParkingFacility {
    private final Vehicle[][] slots;
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();

    public ParkingFacility(int[] slotsPerFloor) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0)
            throw new IllegalArgumentException("At least one floor is required.");
        slots = new Vehicle[slotsPerFloor.length][];
        for (int f = 0; f < slotsPerFloor.length; f++) {
            if (slotsPerFloor[f] <= 0) throw new IllegalArgumentException("Each floor needs slots.");
            slots[f] = new Vehicle[slotsPerFloor[f]];
        }
    }

    public ParkingTicket enter(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle is required.");
        String reg = vehicle.getRegistrationNumber();
        if (activeTickets.containsKey(reg)) throw new IllegalArgumentException("Duplicate registration: " + reg);

        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = vehicle;
                    ParkingTicket ticket = new ParkingTicket(vehicle, f, s);
                    activeTickets.put(reg, ticket);
                    return ticket;
                }
            }
        }
        return null;
    }

    public double exit(String registrationNumber, int parkedMinutes,
                       ParkingPass pass, int chargingKWh) {
        String reg = registrationNumber.toUpperCase();
        ParkingTicket ticket = activeTickets.get(reg);
        if (ticket == null) throw new IllegalArgumentException("No active ticket for " + reg);

        Vehicle v = ticket.getVehicle();
        double fee = 0.0;
        if (pass == null || !pass.isValidForFreeParking()) {
            if (v instanceof DeliveryVan van)
                fee = van.calculateFee(parkedMinutes, ticket.getEntryTime().toLocalTime());
            else
                fee = v.calculateFee(parkedMinutes);
            if (v instanceof Chargeable chargeable)
                fee += chargeable.chargingFee(chargingKWh);
        }
        slots[ticket.getFloor()][ticket.getSlot()] = null;
        activeTickets.remove(reg);
        return fee;
    }

    public boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length || slot < 0 || slot >= slots[floor].length)
            return false;
        if (slots[floor][slot] == null) return false;
        String reg = slots[floor][slot].getRegistrationNumber();
        slots[floor][slot] = null;
        activeTickets.remove(reg);
        return true;
    }

    public int getOccupancy() { return activeTickets.size(); }
    public int getFloorCount() { return slots.length; }
    public int getSlotsOnFloor(int floor) { return slots[floor].length; }
}
