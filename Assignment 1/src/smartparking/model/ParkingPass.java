package smartparking.model;

public class ParkingPass {
    private final String ownerId;
    private final boolean monthly;
    private final boolean oneDay;

    public ParkingPass(String ownerId, boolean monthly, boolean oneDay) {
        if (ownerId == null || ownerId.isBlank())
            throw new IllegalArgumentException("Pass owner is required.");
        this.ownerId = ownerId;
        this.monthly = monthly;
        this.oneDay = oneDay;
    }
    public String getOwnerId() { return ownerId; }
    public boolean isValidForFreeParking() { return monthly || oneDay; }
}
