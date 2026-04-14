package Parking;

import Vehicles.*;
import java.util.*;

public class Parking {
    private List<Vehicle> officialVehicles;
    private List<Vehicle> residentVehicles;
    private List<Vehicle> vehiclesInParking;
    private ResidentReport residentReport;

    private Parking() {
        officialVehicles = new ArrayList<Vehicle>();
        residentVehicles = new ArrayList<Vehicle>();
        vehiclesInParking = new ArrayList<>();
        residentReport = new ResidentReport(residentVehicles);
    }

    private static Parking instance = new Parking();
    public static Parking getInstance() {
        return instance;
    }

    private boolean isVehicleInList(List<Vehicle> vehicles, String plateNumber) {
        boolean isInList = false;
        if (vehicles.isEmpty()) {
            return isInList;
        } else {
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getPlateNumber().equals(plateNumber)) {
                    isInList = true;
                    break;
                }
            }
        }
        return isInList;
    }
    // Option 1
    private boolean isOfficialVehicle(String plateNumber) {
        return isVehicleInList(officialVehicles, plateNumber);
    }

    private boolean isResidentVehicle(String plateNumber) {
        return isVehicleInList(residentVehicles, plateNumber);
    }

    private boolean isVehicleInParking(String plateNumber) {
        return isVehicleInList(vehiclesInParking, plateNumber);
    }

    private void addReportVehicle(List<Vehicle> vehicles, String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                vehicle.addReport();
            }
        }
    }

    private void addReportOfficialVehicle(String plateNumber) {
        if (isOfficialVehicle(plateNumber)) {
            addReportVehicle(officialVehicles, plateNumber);
        }
    }

    private void addReportResidentVehicle(String plateNumber) {
        if (isResidentVehicle(plateNumber)) {
            addReportVehicle(residentVehicles, plateNumber);
        }
    }

    public void addVehicle(String plateNumber) {
        Vehicle vehicle = new NonResidentVehicle(plateNumber);
        if (isVehicleInParking(plateNumber)) {
            System.out.println("This vehicle is already in the parking.");
            return;
        }
        if (isOfficialVehicle(plateNumber)) {
            addReportOfficialVehicle(plateNumber);
            vehicle = new OfficialVehicle(plateNumber);
            System.out.println("This is an official vehicle.");
        }
        if (isResidentVehicle(plateNumber)) {
            addReportResidentVehicle(plateNumber);
            vehicle = new ResidentVehicle(plateNumber);
            System.out.println("This is a resident vehicle.");
        }
        vehiclesInParking.add(vehicle);
        System.out.println("The entry has been reported successfully.");
    }

    // Option 2
    public void deleteVehicle(String plateNumber) {
        if (vehiclesInParking.isEmpty()) {
            System.out.println("The parking is already empty.");
            return;
        }
        List<Vehicle> newVehicles = vehiclesInParking;
        String message = "";
        for (Vehicle vehicle : newVehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                vehicle.reportExit();
                newVehicles.remove(vehicle);
                vehiclesInParking = newVehicles;
                message = "Exit reported successfully.";
                break;
            }
            message = "Unidentified plate number, please try again.";
        }
        System.out.println(message);
    }

    // Option 3
    public void registerOfficialVehicle(String plateNumber) {
        if (isOfficialVehicle(plateNumber)) {
            System.out.println("This vehicle has already been registered.");
        } else {
            officialVehicles.add(new OfficialVehicle(plateNumber));
            System.out.println("The official vehicle has been registered successfully.");
        }
    }

    // Option 4
    public void registerResidentVehicle(String plateNumber) {
        if (isResidentVehicle(plateNumber)) {
            System.out.println("This vehicle has already been registered.");
        }
        residentVehicles.add(new ResidentVehicle(plateNumber));
        System.out.println("The resident vehicle has been registered successfully.");
    }

    // Option 5
    private void restartVehicleMinutes() {
        for (Vehicle vehicle : residentVehicles) {
            ((ResidentVehicle) vehicle).setMinutesInParking(0);
        }
    }

    private void deleteOfficialVehicleRegisters() {
        for (Vehicle v : officialVehicles) {
            v.deleteReports();
        }
    }

    public void processMonthStart() {
        restartVehicleMinutes();
        deleteOfficialVehicleRegisters();
    }

    // Option 6
    public ResidentReport getResidentReport() {
        return residentReport;
    }
}
