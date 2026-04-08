package Vehiculos;
import java.util.*;

public class Vehiculo {
    private String matricula;
    private List<Registro> registros;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
        registros = new ArrayList<>();
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

    @Override
    public String toString() {
        return matricula;
    }

}
