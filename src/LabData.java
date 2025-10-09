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

    public static void main(String[] args) {
        HashMap<String, Paciente> pacientes = null;
        HashMap<String, Tecnico> tecnicos;
        List<Muestra> muestras;

        pacientes = leerPacientes();
    }

    public static HashMap<String, Paciente> leerPacientes() {
        //añadimos la ruta

        String ruta = "./data/in/pacientes.csv";
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

    public static List<Tecnico> leerTecnicos(String rutaFichero) {

        List<Tecnico> personas;

        // 1) Cargar el archivo XML
        File file = new File(rutaFichero);

        try {
            // 2) Crear el parser de XML (DocumentBuilder)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();

            // 3) Parsear el archivo y obtener el documento en memoria
            Document doc = parser.parse(file);

            // 4) Obtener la lista de nodos <persona>
            NodeList lista = doc.getElementsByTagName("person");

            // 5) Crear lista Java para guardar las personas
            personas = new ArrayList<>();

            System.out.println(lista.getLength());

            // 6) Recorrer cada nodo <persona>
            for (int i = 0; i < lista.getLength(); i++) {
                Element e = (Element) lista.item(i); // Convertir el nodo a Element

                // 7) Extraer cada campo del XML
                int id = Integer.parseInt(
                        e.getElementsByTagName("id").item(0).getTextContent()
                );

                String nombre =
                        e.getElementsByTagName("name").item(0).getTextContent();

                String turno;

                String apellido;

                //TODO Instanciar un objeto persona con los datos de id, nombre y edad
                // Tecnico tecnico = new Tecnico(id, nombre,apellido, turno);

                //TODO Añadir la persona a la lista personas
                // personas.add(tecnico);
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        //TODO retornar la lista de personas
        return personas;
    }

    /**
     * Escribe los datos generando los ficheros de salida en ./data/out
     */
    public void generarMuestrasConsolidado(HashMap<String, Paciente> pacientes) {

    }

    public void generarMuestrasAppExterna() {
//TODO
    }

    public void generarSerializado() {
//TODO
    }

}
