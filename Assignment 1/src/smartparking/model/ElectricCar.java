package smartparking.model;

public class ElectricCar extends Vehicle implements Chargeable {
    public ElectricCar(String regNo) { super(regNo); }
    @Override public double ratePerHour() { return 60.0; }
    @Override public double chargingFee(int kWh) {
        if (kWh < 0) throw new IllegalArgumentException("kWh cannot be negative.");
        return kWh * 3.0;
    }
}
