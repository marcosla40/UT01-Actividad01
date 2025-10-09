import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabData implements Serializable {

    private HashMap<String,Paciente> pacientes;
    private HashMap<String,Tecnico> tecnicos;
    private ArrayList<Muestra> muestras;

    public LabData(HashMap<String, Paciente> pacientes, HashMap<String, Tecnico> tecnicos, ArrayList<Muestra> muestras) {
        this.pacientes = pacientes;
        this.tecnicos = tecnicos;
        this.muestras = muestras;
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

    public static ArrayList<Muestra> leerMuestras(String ruta_muestras) {

        ArrayList<Muestra> muestras = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(ruta_muestras))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("\\|");
                Muestra muestra = new Muestra(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
                muestras.add(muestra);
                System.out.println("Muestra" + muestra);
            }

            System.out.println(muestras.size());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return muestras;
    }

    /**
     * Escribe los datos generando los ficheros de salida en ./data/out
     */

    public void generarMuestrasConsolidado(String ruta_muestrasConsolidado) {

        try {
            FileWriter fw = new FileWriter(ruta_muestrasConsolidado);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("#id_muestra,id_paciente,paciente_nombre,paciente_apellido,id_tecnico,tecnico_nombre,tecnico_apellido,tipo,estado");
            bw.newLine();

            for (int i = 0; i < muestras.size(); i++) {

                Muestra m = muestras.get(i);
                Paciente p = pacientes.get(m.getIdPaciente());
                Tecnico t = tecnicos.get(m.getIdTecnico());

                String linea = m.getIdMuestra() + "," + m.getIdPaciente() + "," + p.getNombre() + "," + p.getApellido() + "," + m.getIdTecnico() + "," + t.getNombre() + "," + t.getApellido() + "," + m.getTipo();
                bw.write(linea);
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void generarSerializado(String ruta) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
            oos.writeObject(this);

            System.out.println("Pacientes: " + this.pacientes.size());
            System.out.println("Técnicos: " + this.tecnicos.size());
            System.out.println("Muestras: " + this.muestras.size());

            System.out.println("Archivo serializado correctamente");
        } catch (IOException e) {
            System.out.println("Error al serializar el objeto");
        }
    }






}
