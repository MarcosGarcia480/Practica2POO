package Parking;

import Vehiculos.*;
import java.util.*;

public class Parking {
    private List<VehiculoOficial> vehiculoOficiales;
    private List<VehiculoResidente> vehiculoResidentes;
    private List<Vehiculo> vehiculosEnParking;

    private Parking() {
        vehiculoOficiales = new ArrayList<>();
        vehiculoResidentes = new ArrayList<>();
        vehiculosEnParking = new ArrayList<>();
    }

    private static Parking instance = new Parking();
    public static Parking getInstance() {
        return instance;
    }

    private void reiniciarMinutosVehiculos() {
        for (VehiculoResidente v : vehiculoResidentes) {
            v.setMinutosEnParking(0);
            v.eliminarRegistros();
        }
    }

    private void eliminarVehiculosOficiales() {
        for (VehiculoOficial v : vehiculoOficiales) {
            v.eliminarRegistros();
        }
    }

    public void procesarInicioMes() {
        reiniciarMinutosVehiculos();
        eliminarVehiculosOficiales();
    }

    public List<VehiculoOficial> getVehiculoOficiales() {
        return vehiculoOficiales;
    }

    public List<Vehiculo> getVehiculosEnParking() {
        return vehiculosEnParking;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        this.vehiculosEnParking.add(vehiculo);
    }
}
