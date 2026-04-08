import Parking.Parking;
import Vehiculos.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parking parking = Parking.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean salida = false;
        final int NUMERO_OPCIONES = 7;

        System.out.println("           ====================           ");
        System.out.println("           =    BIENVENIDO    =           ");
        System.out.println("           ====================           ");

        while (!salida) {
            System.out.println("==========================================");
            System.out.println("Selecione una de las siguientes opciones:");
            System.out.println("1. Registrar entrada.");
            System.out.println("2. Registrar salida.");
            System.out.println("3. Dar de alta vehículo oficial.");
            System.out.println("4. Dar de alta vehículo de residente.");
            System.out.println("5. Comienza mes.");
            System.out.println("6. Pagos de residentes.");
            System.out.println("7. Salir.");
            System.out.println("==========================================");
            String input = scanner.next();
            int opcion;
            try {
                opcion = Integer.parseInt(input.trim());
                if (opcion > NUMERO_OPCIONES || opcion < 1) {
                    System.out.println("Opción no disponible, por favor intente de nuevo.");
                } else {
                    String mensajeMatricula = "Introduzca la matricula del vehículo: ";
                    switch (opcion) {
                        case 1 :
                            System.out.println(mensajeMatricula);
                            break;
                        case 2 :
                            System.out.println(mensajeMatricula);
                            break;
                        case 3 :
                            System.out.println(mensajeMatricula);
                            break;
                        case 4 :
                            System.out.println(mensajeMatricula);
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
            } catch (NumberFormatException ex) {
                System.err.println("No se permiten letras o caracteres especiales.");
                System.out.println("Por favor introduzca un numero entre 1 y " + NUMERO_OPCIONES);
            }
        }
    }
}
