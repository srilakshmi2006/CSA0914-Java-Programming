package smartparking.app;

import smartparking.model.*;
import smartparking.service.ParkingFacility;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] slotsPerFloor = {3, 4, 2, 5, 3}; // unequal floor sizes: jagged array
        ParkingFacility facility = new ParkingFacility(slotsPerFloor);
        Scanner input = new Scanner(System.in);

        System.out.println("=== CAMPUS SMART-PARKING SYSTEM ===");
        System.out.println("Floors: " + facility.getFloorCount());
        System.out.println("Available vehicle types: 1-TwoWheeler 2-Car 3-ElectricCar 4-EBike 5-DeliveryVan");
        System.out.print("Enter vehicle type (1-5): ");
        int type = Integer.parseInt(input.nextLine().trim());
        System.out.print("Enter registration number: ");
        String reg = input.nextLine().trim();

        Vehicle vehicle = switch (type) {
            case 1 -> new TwoWheeler(reg);
            case 2 -> new Car(reg);
            case 3 -> new ElectricCar(reg);
            case 4 -> new EBike(reg);
            case 5 -> new DeliveryVan(reg);
            default -> throw new IllegalArgumentException("Unknown vehicle type.");
        };

        ParkingTicket ticket = facility.enter(vehicle);
        if (ticket == null) {
            System.out.println("Parking full. No slot available.");
        } else {
            System.out.println("Parking successful!");
            System.out.println("Ticket: " + ticket.getTicketNumber());
            System.out.println("Allocated floor: " + ticket.getFloor());
            System.out.println("Allocated slot: " + ticket.getSlot());
            System.out.println("Current occupancy: " + facility.getOccupancy());
            System.out.print("Enter parked duration in minutes: ");
            int minutes = Integer.parseInt(input.nextLine().trim());
            System.out.print("Monthly/one-day pass? (y/n): ");
            boolean hasPass = input.nextLine().trim().equalsIgnoreCase("y");
            System.out.print("Charging units (kWh, 0 if not EV): ");
            int kWh = Integer.parseInt(input.nextLine().trim());
            ParkingPass pass = hasPass ? new ParkingPass("CAMPUS-USER", true, false) : null;
            double fee = facility.exit(reg, minutes, pass, kWh);
            System.out.printf("Parking fee: Rs %.2f%n", fee);
            System.out.println("Vehicle exited. Current occupancy: " + facility.getOccupancy());
        }
        input.close();
    }
}
