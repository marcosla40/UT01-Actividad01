import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        final String ruta_pacientes = "./data/in/pacientes.csv";
        final String ruta_tecnicos = "./data/in/tecnicos.tsv";
        final String ruta_muestras = "./data/in/muestras.psv";

        final String ruta_muestrasConsolidado = "./data/out/muestras_consolidado.csv";
        final String ruta_serializado = "./data/out/labdata.ser";

        HashMap<String, Paciente> pacientes = LabData.leerPacientes(ruta_pacientes);
        HashMap<String, Tecnico> tecnicos = LabData.leerTecnicos(ruta_tecnicos);
        ArrayList<Muestra> muestras = LabData.leerMuestras(ruta_muestras);

        LabData lab = new LabData(pacientes, tecnicos, muestras);

        lab.generarMuestrasConsolidado(pacientes,tecnicos,muestras,ruta_muestrasConsolidado);
        lab.generarSerializado(ruta_serializado);
    }
}
