package Parking;

import Vehicles.*;
import java.util.*;

public class Parking {
    private List<Vehicle> officialVehicles;
    private List<Vehicle> residentVehicles;
    private List<Vehicle> vehiclesInParking;
    private ResidentReport residentReport;

    private Parking() {
        officialVehicles = new ArrayList<>();
        residentVehicles = new ArrayList<>();
        vehiclesInParking = new ArrayList<>();
        residentReport = new ResidentReport(residentVehicles);
    }

    private static Parking instance = new Parking();
    public static Parking getInstance() {
        return instance;
    }

    // Option 1
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

    private boolean isOfficialVehicle(String plateNumber) {
        return isVehicleInList(officialVehicles, plateNumber);
    }

    private boolean isResidentVehicle(String plateNumber) {
        return isVehicleInList(residentVehicles, plateNumber);
    }

    private boolean isVehicleInParking(String plateNumber) {
        return isVehicleInList(vehiclesInParking, plateNumber);
    }

    private void reportVehicleEntry(List<Vehicle> vehicles, String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                vehicle.addReport();
            }
        }
    }

    public void addVehicle(String plateNumber) {
        if (isVehicleInParking(plateNumber)) {
            System.out.println("This vehicle is already in the parking.");
            return;
        }
        Vehicle vehicle = new NonResidentVehicle(plateNumber);
        if (isOfficialVehicle(plateNumber)) {
            reportVehicleEntry(officialVehicles, plateNumber);
            vehicle = new OfficialVehicle(plateNumber);
            System.out.println("This is an official vehicle.");
        }
        if (isResidentVehicle(plateNumber)) {
            reportVehicleEntry(residentVehicles, plateNumber);
            vehicle = new ResidentVehicle(plateNumber);
            System.out.println("This is a resident vehicle.");
        }
        vehiclesInParking.add(vehicle);
        System.out.println("The entry has been reported successfully.");
    }

    // Option 2
    private void reportExitForSpecialVehicleLists(List<Vehicle> vehicles, String plateNumber) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlateNumber().equals(plateNumber)) {
                vehicle.reportExit();
            }
        }
    }

    private void reportVehicleExit(String plateNumber, List<Vehicle> newVehicles) {
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

    public void deleteVehicle(String plateNumber) {
        if (vehiclesInParking.isEmpty()) {
            System.out.println("The parking is already empty.");
            return;
        }
        List<Vehicle> newVehicles = vehiclesInParking;
        if (isVehicleInParking(plateNumber)) {
            if (isOfficialVehicle(plateNumber)) {
                reportExitForSpecialVehicleLists(officialVehicles, plateNumber);
            } else if (isResidentVehicle(plateNumber)) {
                reportExitForSpecialVehicleLists(residentVehicles, plateNumber);
            }
            reportVehicleExit(plateNumber, newVehicles);
        }
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
