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

    public void altaVehiculoOficial(String matricula) {
        vehiculosOficiales.add(new VehiculoOficial(matricula));
        System.out.println("El vehículo oficial se ha registrado con éxito.");
    }

    public void altaVehiculoResidente(String matricula) {
        vehiculosResidentes.add(new VehiculoResidente(matricula));
        System.out.println("El vehículo residente se ha registrado con éxito.");
    }

    private boolean esVehiculoOficial(String matricula) {
        boolean esOficial = false;
        if (vehiculosEnParking.isEmpty()) {
            return esOficial;
        } else {
            for (VehiculoOficial vehiculoOficial : vehiculosOficiales) {
                if (vehiculoOficial.getMatricula().equals(matricula)) {
                    esOficial = true;
                    return esOficial;
                }
            }
        }
        return esOficial;
    }

    private boolean esVehiculoResidente(String matricula) {
        boolean esResidente = false;
        if (vehiculosEnParking.isEmpty()) {
            return esResidente;
        } else {
            for (VehiculoResidente vehiculoResidente : vehiculosResidentes) {
                if (vehiculoResidente.getMatricula().equals(matricula)) {
                    esResidente = true;
                    return esResidente;
                }
            }
        }
        return esResidente;
    }

    private boolean estaVehiculoEnParking(String matricula) {
        boolean estaEnParking = true;
        if (vehiculosEnParking.isEmpty()) {
            estaEnParking = false;
        } else {
            for (Vehiculo vehiculo : vehiculosEnParking) {
                if (!vehiculo.getMatricula().equals(matricula)) {
                    estaEnParking = false;
                }
            }
        }
        return estaEnParking;
    }

    public void agregarVehiculo(String matricula) {
        Vehiculo vehiculo = new VehiculoNoResidente(matricula);
        if (esVehiculoOficial(matricula)) {
            vehiculo = new VehiculoOficial(matricula);
            System.out.println("Este vehículo es oficial.");
        } else if (esVehiculoResidente(matricula)) {
            vehiculo = new VehiculoResidente(matricula);
            System.out.println("Este vehículo es residente.");
        } else if (estaVehiculoEnParking(matricula)) {
            System.out.println("El vehículo ya está en el parking.");
        } else {
            vehiculosEnParking.add(vehiculo);
            System.out.println("Entrada registrada con éxito, vehículo: " + vehiculo.getMatricula());
        }
    }

    public List<VehiculoOficial> getVehiculosOficiales() {
        return vehiculosOficiales;
    }

    public void setVehiculosEnParking(List<Vehiculo> vehiculosEnParking) {
        this.vehiculosEnParking = vehiculosEnParking;
    }

    public List<Vehiculo> getVehiculosEnParking() {
        return vehiculosEnParking;
    }
}
