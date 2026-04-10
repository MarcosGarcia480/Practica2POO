package Parking;

import Vehiculos.*;
import java.util.*;

public class Parking {
    private List<VehiculoOficial> vehiculosOficiales;
    private List<VehiculoResidente> vehiculosResidentes;
    private List<Vehiculo> vehiculosEnParking;
    private InformeResidente informeResidente;

    private Parking() {
        vehiculosOficiales = new ArrayList<>();
        vehiculosResidentes = new ArrayList<>();
        vehiculosEnParking = new ArrayList<>();
        informeResidente = new InformeResidente(vehiculosResidentes);
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

    private void agregarRegistroVehiculoOficial(String matricula) {
        if (esVehiculoOficial(matricula)) {
            for (VehiculoOficial vehiculoOficial : vehiculosOficiales) {
                if (vehiculoOficial.getMatricula().equals(matricula)) {
                    vehiculoOficial.agregarRegistro();
                }
            }
        }
    }

    private void agregarRegistroVehiculoResidente(String matricula) {
        if (esVehiculoResidente(matricula)) {
            for (VehiculoResidente vehiculoResidente : vehiculosResidentes) {
                if (vehiculoResidente.getMatricula().equals(matricula)) {
                    vehiculoResidente.agregarRegistro();
                }
            }
        }
    }

    public void agregarVehiculo(String matricula) {
        Vehiculo vehiculo = new VehiculoNoResidente(matricula);
        if (esVehiculoOficial(matricula)) {
            agregarRegistroVehiculoOficial(matricula);
        } else if (esVehiculoResidente(matricula)) {
            agregarRegistroVehiculoResidente(matricula);
        } else if (estaVehiculoEnParking(matricula)) {
            System.out.println("El vehículo ya está en el parking.");
        } else {
            vehiculosEnParking.add(vehiculo);
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
