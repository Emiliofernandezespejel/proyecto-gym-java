package modelo;

public class Socio {
    private int idSocio;
    private String nombre;
    private String nivel;
    private int puntajeAcumulado;

    public Socio() {
    }

    public Socio(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntajeAcumulado = 0;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getPuntajeAcumulado() {
        return puntajeAcumulado;
    }

    public void setPuntajeAcumulado(int puntajeAcumulado) {
        this.puntajeAcumulado = puntajeAcumulado;
    }
}