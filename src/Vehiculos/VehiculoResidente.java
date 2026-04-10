package Vehiculos;

public class VehiculoResidente extends VehiculoPaga {
    private static final double COSTO_POR_MINUTO = 0.002;

    private int minutosEnParking;

    public VehiculoResidente(String matricula) {
        super(matricula);
        this.minutosEnParking = 0;
    }

    @Override
    public void registrarSalida() {
        super.registrarSalida();
        this.minutosEnParking += calcularMinutosUltimoRegistro();
    }

    public void setMinutosEnParking(int minutosEnParking) {
        this.minutosEnParking = minutosEnParking;
    }

    public double getCostoTotal() {
        return minutosEnParking * COSTO_POR_MINUTO;
    }

    @Override
    public String toString() {
        return getMatricula() + "   " + minutosEnParking + "   " + getCostoTotal();
    }
}
