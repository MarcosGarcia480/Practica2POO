package Vehicles;

public class ResidentVehicle extends PayVehicle {
    private static final double COST_PER_MINUTE = 0.002;

    private int minutesInParking;

    public ResidentVehicle(String plateNumber) {
        super(plateNumber);
        this.minutesInParking = 0;
    }

    @Override
    public void reportExit() {
        super.reportExit();
        this.minutesInParking += calculateMinutesLastRegister();
    }

    public void setMinutesInParking(int minutesInParking) {
        this.minutesInParking = minutesInParking;
    }

    public double getTotalCost() {
        return minutesInParking * COST_PER_MINUTE;
    }

    @Override
    public String toString() {
        return getPlateNumber() + "   " + minutesInParking + "   " + getTotalCost();
    }
}
