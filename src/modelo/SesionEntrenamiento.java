package modelo;

import java.time.LocalDate;

/**
 * Sesion de entrenamiento entre un socio (Socio 1) y su entrenador
 * o companero (Socio 2). Al procesarse, las calorias quemadas se
 * suman al puntaje acumulado del socio.
 */
public class SesionEntrenamiento {

    /** Identificador unico de la sesion (lo asigna la base de datos). */
    private int idSesion;
    /** Socio que realiza el entrenamiento (Socio 1). */
    private Socio socio;
    /** Entrenador o companero (Socio 2). */
    private Socio entrenador;
    /** Calorias quemadas (puntos obtenidos) en la sesion. */
    private int calorias;
    /** Fecha en la que se realizo la sesion. */
    private LocalDate fecha;

    /** Constructor vacio. */
    public SesionEntrenamiento() {
    }

    /**
     * Crea una sesion nueva, todavia sin id (se asigna al guardarla).
     *
     * @param socio      socio que entrena
     * @param entrenador entrenador o companero (otro socio)
     * @param calorias   calorias quemadas (debe ser positivo)
     * @param fecha      fecha de la sesion
     */
    public SesionEntrenamiento(Socio socio, Socio entrenador, int calorias, LocalDate fecha) {
        this.socio = socio;
        this.entrenador = entrenador;
        this.calorias = calorias;
        this.fecha = fecha;
    }

    /** @return el id de la sesion */
    public int getIdSesion() { return idSesion; }

    /** @param idSesion el id de la sesion */
    public void setIdSesion(int idSesion) { this.idSesion = idSesion; }

    /** @return el socio que entrena */
    public Socio getSocio() { return socio; }

    /** @param socio el socio que entrena */
    public void setSocio(Socio socio) { this.socio = socio; }

    /** @return el entrenador o companero */
    public Socio getEntrenador() { return entrenador; }

    /** @param entrenador el entrenador o companero */
    public void setEntrenador(Socio entrenador) { this.entrenador = entrenador; }

    /** @return las calorias quemadas en la sesion */
    public int getCalorias() { return calorias; }

    /** @param calorias las calorias quemadas en la sesion */
    public void setCalorias(int calorias) { this.calorias = calorias; }

    /** @return la fecha de la sesion */
    public LocalDate getFecha() { return fecha; }

    /** @param fecha la fecha de la sesion */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    /**
     * Devuelve la sesion en texto legible para mostrarla en consola.
     *
     * @return descripcion de la sesion
     */
    @Override
    public String toString() {
        String nombreSocio = (socio != null) ? socio.getNombre() : "(sin socio)";
        String nombreEntrenador = (entrenador != null) ? entrenador.getNombre() : "(sin entrenador)";
        return "Sesion #" + idSesion
                + " | Socio: " + nombreSocio
                + " | Con: " + nombreEntrenador
                + " | Calorias: " + calorias
                + " | Fecha: " + fecha;
    }
}