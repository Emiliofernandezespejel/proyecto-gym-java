package modelo;

import java.time.LocalDate;

/**
 * Sesión de entrenamiento: un socio entrena con otro socio que hace de entrenador.
 */
public class SesionEntrenamiento {

    /** Id de la sesión (lo pone la base de datos). */
    private int idSesion;

    /** Socio que entrena. */
    private Socio socio;

    /** Entrenador o compañero (también es un socio). */
    private Socio entrenador;

    /** Calorías quemadas en la sesión. */
    private int calorias;

    /** Día en que se hizo la sesión. */
    private LocalDate fecha;

    /** Constructor vacío. */
    public SesionEntrenamiento() {
    }

    /**
     * Crea una sesión nueva, el id se pone al guardarla en la base de datos.
     *
     * @param socio      socio que entrena
     * @param entrenador entrenador o compañero
     * @param calorias   calorías quemadas
     * @param fecha      fecha de la sesión
     */
    public SesionEntrenamiento(Socio socio, Socio entrenador, int calorias, LocalDate fecha) {
        this.socio = socio;
        this.entrenador = entrenador;
        this.calorias = calorias;
        this.fecha = fecha;
    }

    /** @return el id de la sesión */
    public int getIdSesion() { return idSesion; }

    /** @param idSesion el id de la sesión */
    public void setIdSesion(int idSesion) { this.idSesion = idSesion; }

    /** @return el socio que entrena */
    public Socio getSocio() { return socio; }

    /** @param socio el socio que entrena */
    public void setSocio(Socio socio) { this.socio = socio; }

    /** @return el entrenador o compañero */
    public Socio getEntrenador() { return entrenador; }

    /** @param entrenador el entrenador o compañero */
    public void setEntrenador(Socio entrenador) { this.entrenador = entrenador; }

    /** @return las calorías quemadas */
    public int getCalorias() { return calorias; }

    /** @param calorias las calorías quemadas */
    public void setCalorias(int calorias) { this.calorias = calorias; }

    /** @return la fecha de la sesión */
    public LocalDate getFecha() { return fecha; }

    /** @param fecha la fecha de la sesión */
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    /**
     * Devuelve la sesión como texto para mostrarla en pantalla.
     *
     * @return la sesión en texto
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