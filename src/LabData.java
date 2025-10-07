import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class LabData implements Serializable {

    final String ruta_pacientes = "./data/in/pacientes.csv";
    final String ruta_tecnicos = "./data/in/tecnicos.tsv";
    final String ruta_muestras = "./data/in/muestras.psv";

    private HashMap<String, Paciente> pacientes;
    private HashMap<String, Tecnico> tecnicos;
    private List<Muestra> muestras;

    public LabData() {
        this.pacientes = new LinkedHashMap<>();
        this.tecnicos = new LinkedHashMap<>();
        this.muestras = new ArrayList<>();
    }

    public void leerPacientes() {

    }
}
