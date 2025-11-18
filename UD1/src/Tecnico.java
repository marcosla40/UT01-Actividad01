public class Tecnico {

    private String idTecnico;
    private String nombre;
    private String apellido;
    private String turno;

    public Tecnico(String idTecnico, String nombre, String apellido, String turno) {
        this.idTecnico = idTecnico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.turno = turno;
    }

    public String getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(String idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Tecnico{" +
                "idTecnico='" + idTecnico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", turno='" + turno + '\'' +
                '}';
    }
}
