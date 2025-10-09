import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.*;

class LabDataTest {

    @Test
    public void testGenerarSerializado() throws IOException, ClassNotFoundException {
        String ruta = "./data/out/labdata.ser";

        // Leer el objeto serializado
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
        LabData lab = (LabData) ois.readObject();
        ois.close();

        // Comprobar tamaños
        assertEquals(3, lab.getPacientes().size(), "Número de pacientes");
        assertEquals(2, lab.getTecnicos().size(), "Número de técnicos");
        assertEquals(4, lab.getMuestras().size(), "Número de muestras");

        // Comprobar que todas las referencias existen
        for (Muestra m : lab.getMuestras()) {
            assertNotNull(lab.getPacientes());
            assertNotNull(lab.getTecnicos());
        }
    }
}