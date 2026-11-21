package smartparking.model;

public class EBike extends Vehicle {
    public EBike(String regNo) { super(regNo); }
    // The new requirement specifies a flat parking fee.
    @Override public double calculateFee(int minutes) {
        if (minutes < 0) throw new IllegalArgumentException("Minutes cannot be negative.");
        return minutes == 0 ? 0.0 : 25.0;
    }
    @Override public double ratePerHour() { return 0.0; }
}
