package Parking;

import Vehiculos.*;
import java.util.*;

public class Parking {
    private List<VehiculoOficial> vehiculosOficiales;
    private List<VehiculoResidente> vehiculosResidentes;
    private List<Vehiculo> vehiculosEnParking;

    private Parking() {
        vehiculosOficiales = new ArrayList<>();
        vehiculosResidentes = new ArrayList<>();
        vehiculosEnParking = new ArrayList<>();
    }

    private static Parking instance = new Parking();
    public static Parking getInstance() {
        return instance;
    }

    private void reiniciarMinutosVehiculos() {
        for (VehiculoResidente v : vehiculosResidentes) {
            v.setMinutosEnParking(0);
            v.eliminarRegistros();
        }
    }

    private void eliminarVehiculosOficiales() {
        for (VehiculoOficial v : vehiculosOficiales) {
            v.eliminarRegistros();
        }
    }

    public void procesarInicioMes() {
        reiniciarMinutosVehiculos();
        eliminarVehiculosOficiales();
    }

    private void revisarVehiculoOficial(String matricula) {
        for (VehiculoOficial vehiculoOficial : vehiculosOficiales) {
            if (vehiculoOficial.getMatricula().equals(matricula)) {
                vehiculosEnParking.add(new VehiculoOficial(matricula));
            }
        }
    }

    private void revisarVehiculoResidente(String matricula) {
        for (VehiculoResidente vehiculoResidente : vehiculosResidentes) {
            if (vehiculoResidente.getMatricula().equals(matricula)) {
                vehiculosEnParking.add(new VehiculoResidente(matricula));
            }
        }
    }

    public void agregarVehiculo(String matricula) {
        revisarVehiculoOficial(matricula);
        revisarVehiculoResidente(matricula);

    }

    public List<VehiculoOficial> getVehiculosOficiales() {
        return vehiculosOficiales;
    }

    public List<Vehiculo> getVehiculosEnParking() {
        return vehiculosEnParking;
    }
}
