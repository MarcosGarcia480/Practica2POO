import Parking.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int OPTIONS_NUMBER = 7;

        Parking parking = Parking.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

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
                scanner.useDelimiter("\n");
                int option = scanner.nextInt();
                switch (option) {
                    case 1 :
                        parking.addVehicle(askForPlateNumber(scanner));
                        break;
                    case 2 :
                        parking.deleteVehicle(askForPlateNumber(scanner));
                        break;
                    case 3 :
                        parking.registerOfficialVehicle(askForPlateNumber(scanner));
                        break;
                    case 4 :
                        parking.registerResidentVehicle(askForPlateNumber(scanner));
                        break;
                    case 5 :
                        parking.processMonthStart();
                        System.out.println("The time of the residents has been reestablished and the official's registers has been eliminated.");
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
                        scanner.reset();
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
                System.err.println("Letters, blank spaces or special characters are not allowed.");
                System.out.println("Please write a number between 1 and " + OPTIONS_NUMBER);
                scanner.next();
            }
        }
        scanner.close();
    }

    public static String askForPlateNumber(Scanner scanner) {
        System.out.println("Write the plate number and press enter: ");
        return scanner.next();
    }
}
