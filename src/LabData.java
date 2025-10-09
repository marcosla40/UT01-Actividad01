import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
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
        this.pacientes = new HashMap<>();
        this.tecnicos = new HashMap<>();
        this.muestras = new ArrayList<>();
    }

    public void leerPacientes() {
        // leo pacientes
        // pruebo
        // gg
        // pedro
        // pedro
        // pedro
        /*
        a
        a
        a
        a
        a
        a
        a
        a
        a
        a
        a
        a
         */
    }

    public static HashMap<String, Tecnico> leerTecnicos(String rutaFichero) {

        HashMap<String, Tecnico> tecnicos = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) { // lee la linea y si hay un tecnico inicia el bucle
                String[] partes = linea.split("\t");

                String id = partes[0];
                String nombre = partes[1];
                String apellido = partes[3];
                String turno = partes[4];

                Tecnico t = new Tecnico(id, nombre, apellido, turno);
                tecnicos.put(id, t); // put(clave principal, valor)

            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + rutaFichero);
            e.printStackTrace();
        }

        return tecnicos;
    }
}
