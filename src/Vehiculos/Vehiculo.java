package Vehiculos;
import java.util.*;

public class Vehiculo {
    private String matricula;
    private List<Registro> registros;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
        registros = new ArrayList<>();
        registros.add(new Registro());
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public String getMatricula() {
        return matricula;
    }

    public void eliminarRegistros() {
        registros = new ArrayList<>();
    }

    public void registrarSalida() {
        registros.get(registros.size() - 1).registrarSalida();
    }

    public void agregarRegistro() {
        registros.add(new Registro());
    }

    @Override
    public String toString() {
        return matricula;
    }

}
