package smartparking.model;

import java.time.LocalTime;

public class DeliveryVan extends Vehicle {
    public DeliveryVan(String regNo) { super(regNo); }
    @Override public double ratePerHour() { return 30.0; }

    public double calculateFee(int minutes, LocalTime entryTime) {
        double normal = super.calculateFee(minutes);
        boolean daytime = !entryTime.isBefore(LocalTime.of(9, 0))
                       && entryTime.isBefore(LocalTime.of(17, 0));
        return daytime ? normal * 2.0 : normal;
    }
}
