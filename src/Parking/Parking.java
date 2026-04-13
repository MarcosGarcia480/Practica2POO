package Parking;

import Vehicles.*;
import java.util.*;

public class Parking {
    private List<Vehicle> officialVehicles;
    private List<ResidentVehicle> residentVehicles;
    private List<Vehicle> vehiclesInParking;
    private ResidentReport residentReport;

    private Parking() {
        officialVehicles = new ArrayList<Vehicle>();
        residentVehicles = new ArrayList<>();
        vehiclesInParking = new ArrayList<>();
        residentReport = new ResidentReport(residentVehicles);
    }

    private static Parking instance = new Parking();
    public static Parking getInstance() {
        return instance;
    }

    // Option 1
    private boolean isOfficialVehicle(String plateNumber) {
        boolean isOficial = false;
        if (officialVehicles.isEmpty()) {
            return isOficial;
        } else {
            for (Vehicle officialVehicle : officialVehicles) {
                if (officialVehicle.getPlateNumber().equals(plateNumber)) {
                    isOficial = true;
                    break;
                }
            }
        }
        return isOficial;
    }

    private boolean isResidentVehicle(String plateNumber) {
        boolean isResident = false;
        if (residentVehicles.isEmpty()) {
            return isResident;
        } else {
            for (ResidentVehicle residentVehicle : residentVehicles) {
                if (residentVehicle.getPlateNumber().equals(plateNumber)) {
                    isResident = true;
                    break;
                }
            }
        }
        return isResident;
    }

    private boolean isVehicleInParking(String plateNumber) {
        boolean isInParking = false;
        if (vehiclesInParking.isEmpty()) {
            return isInParking;
        } else {
            for (Vehicle vehicle : vehiclesInParking) {
                if (vehicle.getPlateNumber().equals(plateNumber)) {
                    isInParking = true;
                    break;
                }
            }
        }
        return isInParking;
    }

    private void addReportOfficialVehicle(String plateNumber) {
        if (isOfficialVehicle(plateNumber)) {
            for (Vehicle officialVehicle : officialVehicles) {
                if (officialVehicle.getPlateNumber().equals(plateNumber)) {
                    officialVehicle.addReport();
                }
            }
        }
    }

    private void addReportResidentVehicle(String plateNumber) {
        if (isResidentVehicle(plateNumber)) {
            for (ResidentVehicle residentVehicle : residentVehicles) {
                if (residentVehicle.getPlateNumber().equals(plateNumber)) {
                    residentVehicle.addReport();
                }
            }
        }
    }

    public void addVehicle(String plateNumber) {
        Vehicle vehicle = new NonResidentVehicle(plateNumber);
        if (isOfficialVehicle(plateNumber) && !isVehicleInParking(plateNumber)) {
            addReportOfficialVehicle(plateNumber);
            vehicle = new OfficialVehicle(plateNumber);
            System.out.println("This is an official vehicle.");
        }
        if (isResidentVehicle(plateNumber) && !isVehicleInParking(plateNumber)) {
            addReportResidentVehicle(plateNumber);
            vehicle = new ResidentVehicle(plateNumber);
            System.out.println("This is a resident vehicle.");
        }
        if (isVehicleInParking(plateNumber)) {
            System.out.println("This vehicle is already in the parking.");
            return;
        }
        vehiclesInParking.add(vehicle);
        System.out.println("The vehicle has been added successfully.");
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
        for (ResidentVehicle v : residentVehicles) {
            v.setMinutesInParking(0);
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
