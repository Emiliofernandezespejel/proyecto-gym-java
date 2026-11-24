package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ranking de socios ordenado por puntaje acumulado (de mayor a menor).
 * Trabaja con la lista de socios que ya esta cargada en memoria,
 * para no consultar la base de datos a cada rato.
 */
public class Ranking {

    /**
     * Devuelve una copia de la lista de socios ordenada de mayor a menor
     * puntaje acumulado. La lista original no se modifica.
     *
     * @param socios lista de socios a ordenar
     * @return nueva lista ordenada por puntaje descendente
     */
    public static List<Socio> ordenar(List<Socio> socios) {
        List<Socio> ordenados = new ArrayList<>(socios);
        ordenados.sort(Comparator.comparingInt(Socio::getPuntajeAcumulado).reversed());
        return ordenados;
    }

    /**
     * Imprime en consola el ranking general con la posicion de cada socio.
     *
     * @param socios lista de socios (sin ordenar o ya ordenada)
     */
    public static void mostrar(List<Socio> socios) {
        if (socios == null || socios.isEmpty()) {
            System.out.println("Aun no hay socios registrados.");
            return;
        }

        List<Socio> ordenados = ordenar(socios);
        System.out.println("=== RANKING GENERAL ===");
        int posicion = 1;
        for (Socio s : ordenados) {
            System.out.println(posicion + ". " + s.getNombre()
                    + " (" + s.getNivel() + ") - "
                    + s.getPuntajeAcumulado() + " puntos");
            posicion++;
        }
    }
}

    