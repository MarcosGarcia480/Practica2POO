package Parking;

import Vehicles.ResidentVehicle;
import Vehicles.Vehicle;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ResidentReport {
    private List<Vehicle> vehicles;

    public ResidentReport(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void generateReport(String name) throws IOException {
        Scanner scanner = null;
        PrintWriter output = null;
        File file = new File("./" + name + ".txt");

        try {
            scanner = new Scanner(new BufferedReader(new StringReader(vehicles.toString())));
            output = new PrintWriter(new FileWriter(file));

            output.println("Plate Number   Time Parked (min.)   Amount to Pay");
            scanner.useDelimiter(", ");
            while (scanner.hasNext()) {
                output.println(scanner.next());
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

}
