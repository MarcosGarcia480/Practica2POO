package Vehiculos;

public abstract class PayVehicle extends Vehicle {

    public PayVehicle(String plateNumber) {
        super(plateNumber);
    }

    public int calculateMinutesLastRegister() {
        return getReports().get(getReports().size() - 1).calculateMinutes();
    }
}
