package Vehiculos;

import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    public Registro() {
        this.fechaEntrada = LocalDateTime.now();
        this.fechaSalida = null;
    }

    public void registrarSalida() {
        this.fechaSalida = LocalDateTime.now();
    }

    public int calcularMinutos() {
        return fechaSalida.getMinute() - fechaEntrada.getMinute();
    }
}
