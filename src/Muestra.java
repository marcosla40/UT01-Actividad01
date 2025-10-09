import java.io.Serializable;

public class Muestra implements Serializable {
private String idMuestra;
private String idPaciente;
private String idTecnico;
private String tipo;

    public Muestra(String idMuestra, String idPaciente, String idTecnico, String tipo, String parte, String s) {
        this.idMuestra = idMuestra;
        this.idPaciente = idPaciente;
        this.idTecnico = idTecnico;
        this.tipo = tipo;
    }

    public String getIdMuestra() {
        return idMuestra;
    }

    public void setIdMuestra(String idMuestra) {
        this.idMuestra = idMuestra;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
