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

    private void revisarSiEsVehiculoOficial(String matricula) {
        for (VehiculoOficial vehiculoOficial : vehiculosOficiales) {
            if (vehiculoOficial.getMatricula().equals(matricula)) {
                vehiculosEnParking.add(new VehiculoOficial(matricula));
                System.out.println("El vehículo es oficial.");
            } else {
                System.out.println("El vehículo no es oficial.");
            }
        }
    }

    private void revisarSiEsVehiculoResidente(String matricula) {
        for (VehiculoResidente vehiculoResidente : vehiculosResidentes) {
            if (vehiculoResidente.getMatricula().equals(matricula)) {
                vehiculosEnParking.add(new VehiculoResidente(matricula));
                System.out.println("El vehículo es residente.");
            } else {
                System.out.println("El vehículo no es residente.");
            }
        }
    }

    private void revisarVehiculoEnParking(String matricula) {
        if (vehiculosEnParking.isEmpty()) {
            vehiculosEnParking.add(new Vehiculo(matricula));
            System.out.println("El vehículo se ha registrado con éxito");
        } else {
            for (Vehiculo vehiculo : vehiculosEnParking) {
                if (!vehiculo.getMatricula().equals(matricula)) {
                    vehiculosEnParking.add(new Vehiculo(matricula));
                    System.out.println("El vehículo se ha registrado con éxito");
                } else {
                    System.out.println("Este vehiculo ya está en el parking.");
                }
            }
        }
    }

    public void agregarVehiculo(String matricula) {
        revisarSiEsVehiculoOficial(matricula);
        revisarSiEsVehiculoResidente(matricula);
        revisarVehiculoEnParking(matricula);
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
