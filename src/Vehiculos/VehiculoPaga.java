package Vehiculos;

public abstract class VehiculoPaga extends Vehiculo {

    public VehiculoPaga(String matricula) {
        super(matricula);
    }

    public int calcularMinutosUltimoRegistro() {
        return getRegistros().get(getRegistros().size() - 1).calcularMinutos();
    }
}
