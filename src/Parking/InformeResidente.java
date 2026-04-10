package Parking;

import Vehiculos.VehiculoResidente;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class InformeResidente {
    private List<VehiculoResidente> vehiculos;

    public InformeResidente(List<VehiculoResidente> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void generarInforme(String nombre) throws IOException {
        Scanner scanner = null;
        PrintWriter output = null;

        try {
            scanner = new Scanner(new BufferedReader(new StringReader(vehiculos.toString())));
            output = new PrintWriter(new FileWriter(nombre));
            output.println("Matrícula   Tiempo estacionado (min.)   Cantidad a pagar");
            scanner.useDelimiter(",\\s*");
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
