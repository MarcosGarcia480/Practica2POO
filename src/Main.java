import Parking.Parking;
import Vehiculos.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parking parking = Parking.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean salida = false;
        final int NUMERO_OPCIONES = 7;

        System.out.println("====================");
        System.out.println("=    BIENVENIDO    =");
        System.out.println("====================");

        while (!salida) {
            System.out.println("=========================================");
            System.out.println("Selecione una de las siguientes opciones:");
            System.out.println("1. Registrar entrada.");
            System.out.println("2. Registrar salida.");
            System.out.println("3. Dar de alta vehículo oficial.");
            System.out.println("4. Dar de alta vehículo de residente.");
            System.out.println("5. Comienza mes.");
            System.out.println("6. Pagos de residentes.");
            System.out.println("7. Salir.");
            System.out.println("=========================================");
            int opcion = scanner.nextInt();
            if (opcion > NUMERO_OPCIONES || opcion < 1) {
                System.out.println("Opción no disponible, por favor intente de nuevo.");
            } else {
                switch (opcion) {
                    case 1 :
                        System.out.println("Introduzca la matricula del vehículo: ");
                        String matricula = scanner.next();
                        parking.agregarVehiculo(new Vehiculo(matricula));
                        break;
                    case 2 :
                        break;
                    case 3 :
                        break;
                    case 4 :
                        break;
                    case 5 :
                        break;
                    case 6 :
                        break;
                    case 7 :
                        System.out.println("¡Hasta la próxima!");
                        salida = true;
                        break;
                }
            }
        }
    }
}
