package Vehicles;
import java.util.*;

public abstract class Vehicle {
    private final String plateNumber;
    private List<Report> reports;

    public Vehicle(String plateNumber) {
        this.plateNumber = plateNumber;
        reports = new ArrayList<>();
        reports.add(new Report());
    }

    public List<Report> getReports() {
        return reports;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void deleteReports() {
        reports = new ArrayList<>();
    }

    public void reportExit() {
        reports.get(reports.size() - 1).reportExit();
    }

    public void addReport() {
        reports.add(new Report());
    }

    @Override
    public String toString() {
        return plateNumber;
    }
}
