import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class LabData implements Serializable {

    public static void main(String[] args) {
        final String ruta_pacientes = "./data/in/pacientes.csv";
        final String ruta_tecnicos = "./data/in/tecnicos.tsv";
        final String ruta_muestras = "./data/in/muestras.psv";

        HashMap<String, Paciente> pacientes = leerPacientes(ruta_pacientes);
        HashMap<String, Tecnico> tecnicos = leerTecnicos(ruta_tecnicos);
        List<Muestra> muestras;

        pacientes = leerPacientes(ruta_pacientes);

    }

    public static HashMap<String, Paciente> leerPacientes(String ruta) {

        HashMap<String, Paciente> pacientes = new HashMap<>();

        try {
            //creamos el objeto FileReader y BufferedReader
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";

            //imprimimos las lineas del fichero
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                Paciente paciente = new Paciente(partes[0], partes[1], partes[2], partes[3]);
                pacientes.put(partes[0], paciente);
            }
            System.out.println(pacientes);
            fr.close();
            br.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pacientes;

    }

    public static HashMap<String, Tecnico> leerTecnicos(String rutaFichero) {

        HashMap<String, Tecnico> tecnicos = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) { // lee la linea y si hay un tecnico inicia el bucle
                String[] partes = linea.split("\t");
                String id = partes[0];
                String nombre = partes[1];
                String apellido = partes[2];
                String turno = partes[3];

                Tecnico t = new Tecnico(id, nombre, apellido, turno);
                tecnicos.put(id, t); // put(clave principal, valor)
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + rutaFichero);
            e.printStackTrace();
        }
        /*
        LEER TECNICOS
        for (Tecnico t : tecnicos.values()) {
            System.out.println(t.toString());
        }
        */
        return tecnicos;
    }

    /** Escribe los datos generando los ficheros de salida en ./data/out
     */
    public void generarMuestrasConsolidado(){
//TODO
    }
    public void generarMuestrasAppExterna(){
//TODO
    }
    public void generarSerializado(){
//TODO
    }

}
