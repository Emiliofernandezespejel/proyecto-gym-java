package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ranking de socios ordenado por puntaje acumulado, de mayor a menor.
 * Trabaja con la lista que ya está en memoria, así no se consulta la
 * base de datos a cada rato.
 */
public class Ranking {

    /**
     * Regresa una copia de la lista ordenada de mayor a menor puntaje.
     * La lista original no se modifica.
     *
     * @param socios lista de socios a ordenar
     * @return lista nueva ordenada por puntaje
     */
         public static List<Socio> ordenar(List<Socio> socios) {
        // Si la lista es null, regresa una lista vacía para no tronar
        if (socios == null) {
            return new ArrayList<>();
        }
        // Copia de la lista para no cambiar la original
        List<Socio> ordenados = new ArrayList<>(socios);
        // Mayor a menor puntaje; si empatan, por nombre
        ordenados.sort(Comparator.comparingInt(Socio::getPuntajeAcumulado).reversed()
                .thenComparing(Socio::getNombre));
        return ordenados;
    }
    /**
     * Imprime el ranking con la posición de cada socio.
     *
     * @param socios lista de socios
     */
    public static void mostrar(List<Socio> socios) {
        if (socios == null || socios.isEmpty()) {
            System.out.println("Aun no hay socios registrados.");
            return;
        }

        List<Socio> ordenados = ordenar(socios);
        System.out.println("=== RANKING GENERAL ===");
        int posicion = 1;
        // Cada vuelta del ciclo es un lugar del ranking
        for (Socio s : ordenados) {
            System.out.println(posicion + ". " + s.getNombre()
                    + " (" + s.getNivel() + ") - "
                    + s.getPuntajeAcumulado() + " puntos");
            posicion++;
        }
    }
}