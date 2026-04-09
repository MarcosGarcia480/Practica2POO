package Vehiculos;

import Interfaces.IImporte;

public class VehiculoNoResidente extends VehiculoPaga implements IImporte {
    private static final double COSTO_POR_MINUTO = 0.02;

    public VehiculoNoResidente(String matricula) {
        super(matricula);
    }

    @Override
    public void registrarSalida() {
        super.registrarSalida();
        getImporte(calcularMinutosUltimoRegistro());
    }

    @Override
    public double getImporte(int minutos) {
        return minutos * COSTO_POR_MINUTO;
    }
}
