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
        }
    }

    private void eliminarRegistrosVehiculosOficiales() {
        for (VehiculoOficial v : vehiculosOficiales) {
            v.eliminarRegistros();
        }
    }

    public void procesarInicioMes() {
        reiniciarMinutosVehiculos();
        eliminarRegistrosVehiculosOficiales();
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
        if (vehiculosOficiales.isEmpty()) {
            return esOficial;
        } else {
            for (VehiculoOficial vehiculoOficial : vehiculosOficiales) {
                if (vehiculoOficial.getMatricula().equals(matricula)) {
                    esOficial = true;
                    break;
                }
            }
        }
        return esOficial;
    }

    private boolean esVehiculoResidente(String matricula) {
        boolean esResidente = false;
        if (vehiculosResidentes.isEmpty()) {
            return esResidente;
        } else {
            for (VehiculoResidente vehiculoResidente : vehiculosResidentes) {
                if (vehiculoResidente.getMatricula().equals(matricula)) {
                    esResidente = true;
                    break;
                }
            }
        }
        return esResidente;
    }

    private boolean estaVehiculoEnParking(String matricula) {
        boolean estaEnParking = false;
        if (vehiculosEnParking.isEmpty()) {
            return estaEnParking;
        } else {
            for (Vehiculo vehiculo : vehiculosEnParking) {
                if (vehiculo.getMatricula().equals(matricula)) {
                    estaEnParking = true;
                    break;
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
        if (esVehiculoOficial(matricula) && !estaVehiculoEnParking(matricula)) {
            agregarRegistroVehiculoOficial(matricula);
            vehiculo = new VehiculoOficial(matricula);
            System.out.println("Este vehículo es oficial.");
        }
        if (esVehiculoResidente(matricula) && !estaVehiculoEnParking(matricula)) {
            agregarRegistroVehiculoResidente(matricula);
            vehiculo = new VehiculoResidente(matricula);
            System.out.println("Este vehículo es residente.");
        }
        if (estaVehiculoEnParking(matricula)) {
            System.out.println("El vehículo ya está en el parking.");
            return;
        }
        vehiculosEnParking.add(vehiculo);
        System.out.println("El vehículo se ha añadido correctamente.");
    }

    public List<VehiculoOficial> getVehiculosOficiales() {
        return vehiculosOficiales;
    }

    public void eliminarVehiculo(String matricula) {
        if (vehiculosEnParking.isEmpty()) {
            System.out.println("El parking ya está vacio.");
            return;
        }
        List<Vehiculo> nuevosVehiculos = vehiculosEnParking;
        String mensaje = "";
        for (Vehiculo vehiculo : nuevosVehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                vehiculo.registrarSalida();
                nuevosVehiculos.remove(vehiculo);
                vehiculosEnParking = nuevosVehiculos;
                mensaje = "Salida registrada con éxito.";
                break;
            }
            mensaje = "Matrícula no reconocida, por favor inténtelo de nuevo";
        }
        System.out.println(mensaje);
    }

    public InformeResidente getInformeResidente() {
        return informeResidente;
    }
}
