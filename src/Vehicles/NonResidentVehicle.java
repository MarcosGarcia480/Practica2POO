package Vehicles;

public class NonResidentVehicle extends PayVehicle {
    private static final double COST_PER_MINUTE = 0.02;

    public NonResidentVehicle(String plateNumber) {
        super(plateNumber);
    }

    @Override
    public void reportExit() {
        super.reportExit();
        getImport(calculateMinutesLastRegister());
        System.out.println("Fee payed of: " + getImport(calculateMinutesLastRegister()) + "€");
    }

    public double getImport(int minutes) {
        return minutes * COST_PER_MINUTE;
    }
}
