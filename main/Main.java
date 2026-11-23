package main;

import dao.SocioDAO;
import modelo.Socio;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocioDAO dao = new SocioDAO();
        int opcion = 0;

        while (opcion != 3) {
            System.out.println("\n--- MENÚ SOCIO ---");
            System.out.println("1. Registrar Socio (Opción A)");
            System.out.println("2. Buscar Socio (Opción C)");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                if (opcion == 1) {
                    System.out.print("Nombre del socio: ");
                    String nombre = sc.nextLine();
                    if(nombre.trim().isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                        continue;
                    }
                    
                    System.out.print("Nivel (Principiante/Intermedio/Avanzado): ");
                    String nivel = sc.nextLine();
                    
                    // Validación del Nivel
                    if(!nivel.matches("(?i)Principiante|Intermedio|Avanzado")) {
                        System.out.println("Nivel inválido. Se asignará Principiante por defecto.");
                        nivel = "Principiante";
                    } else {
                        nivel = nivel.substring(0,1).toUpperCase() + nivel.substring(1).toLowerCase();
                    }

                    Socio nuevoSocio = new Socio(nombre, nivel);
                    
                    if (dao.registrarSocio(nuevoSocio)) {
                        System.out.println("Socio registrado con éxito en MySQL.");
                    }

                } else if (opcion == 2) {
                    System.out.print("Ingrese ID o Nombre a buscar: ");
                    String criterio = sc.nextLine();
                    
                    List<Socio> resultados = dao.buscarSocio(criterio);
                    if (resultados.isEmpty()) {
                        System.out.println("No se encontraron resultados.");
                    } else {
                        System.out.println("--- RESULTADOS ---");
                        for (Socio s : resultados) {
                            System.out.println("ID: " + s.getIdSocio() + 
                                               " | Nombre: " + s.getNombre() + 
                                               " | Nivel: " + s.getNivel() + 
                                               " | Puntos: " + s.getPuntajeAcumulado());
                        }
                    }
                } else if (opcion != 3) {
                    System.out.println("Opción inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un número en el menú.");
            }
        }
        System.out.println("Saliendo del sistema...");
        sc.close();
    }
}
