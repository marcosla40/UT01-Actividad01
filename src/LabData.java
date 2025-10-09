import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class LabData implements Serializable {

    final String ruta_pacientes = "./data/in/pacientes.csv";
    final String ruta_tecnicos = "./data/in/tecnicos.tsv";
    final String ruta_muestras = "./data/in/muestras.psv";

    void main(String[] args) {
        leerMuestras();
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

    public void leerTecnicos() {
        //marcos leeme estagit
    }


    public ArrayList<Object> leerMuestras() {

        ArrayList muestras = new ArrayList<>();


        String ruta = "./data/in/muestras.psv";

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("|");
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

}


