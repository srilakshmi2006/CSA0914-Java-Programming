package smartparking.model;

import java.time.LocalDateTime;

public class ParkingTicket {
    private static int nextTicketNumber = 1000;
    private final String ticketNumber;
    private final Vehicle vehicle;
    private final int floor;
    private final int slot;
    private final LocalDateTime entryTime;

    public ParkingTicket(Vehicle vehicle, int floor, int slot) {
        this(vehicle, floor, slot, LocalDateTime.now());
    }

    public ParkingTicket(Vehicle vehicle, int floor, int slot, LocalDateTime entryTime) {
        if (vehicle == null || floor < 0 || slot < 0 || entryTime == null)
            throw new IllegalArgumentException("Invalid ticket details.");
        this.ticketNumber = "P" + (++nextTicketNumber);
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public String getTicketNumber() { return ticketNumber; }
    public Vehicle getVehicle() { return vehicle; }
    public int getFloor() { return floor; }
    public int getSlot() { return slot; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
