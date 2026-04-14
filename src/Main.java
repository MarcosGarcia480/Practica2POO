import Parking.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parking parking = Parking.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        final int OPTIONS_NUMBER = 7;

        System.out.println("            =====================            ");
        System.out.println("            =      WELCOME      =            ");
        System.out.println("            =====================            ");

        while (!exit) {
            System.out.println("===========================================");
            System.out.println("Please select one of the following options: ");
            System.out.println("1. Report entry.");
            System.out.println("2. Report exit.");
            System.out.println("3. Register official vehicle.");
            System.out.println("4. Register resident vehicle.");
            System.out.println("5. Month start.");
            System.out.println("6. Resident payments.");
            System.out.println("7. Exit.");
            System.out.println("===========================================");
            try {
                int option = scanner.nextInt();
                String plateMessage = "Write the plate number without blank spaces: ";
                switch (option) {
                    case 1 :
                        System.out.println(plateMessage);
                        String plateNumberCase1 = scanner.next();
                        parking.addVehicle(plateNumberCase1);
                        break;
                    case 2 :
                        System.out.println(plateMessage);
                        String plateNumberCase2 = scanner.next();
                        parking.deleteVehicle(plateNumberCase2);
                        break;
                    case 3 :
                        System.out.println(plateMessage);
                        String plateNumberCase3 = scanner.next();
                        parking.registerOfficialVehicle(plateNumberCase3);
                        break;
                    case 4 :
                        System.out.println(plateMessage);
                        String plateNumberCase4 = scanner.next();
                        parking.registerResidentVehicle(plateNumberCase4);
                        break;
                    case 5 :
                        parking.processMonthStart();
                        System.out.println("The time of the residents has been reestablished and the official's register has been eliminated.");
                        break;
                    case 6 :
                        System.out.println("Write the name of the file you want to create: ");
                        String fileName = scanner.next();
                        try {
                            parking.getResidentReport().generateReport(fileName);
                            System.out.println("The file has been successfully created.");
                        } catch (IOException e) {
                            System.err.println("An unexpected error occurred during the file generation:  " + e.getMessage());
                        }
                        break;
                    case 7 :
                        System.out.println("See you!");
                        exit = true;
                        break;
                    default:
                        System.out.println("This option isn't available, try again.");
                        break;
                    }
            } catch (InputMismatchException ex) {
                System.err.println("Letters or special characters are not allowed.");
                System.out.println("Please write a number between 1 and " + OPTIONS_NUMBER);
                scanner.next();
            }
        }
        scanner.close();
    }
}
