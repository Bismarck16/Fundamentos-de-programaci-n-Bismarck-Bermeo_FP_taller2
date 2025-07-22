import java.util.*;

class Nutriente {
    String grupo;
    double gramos;

    public Nutriente(String grupo, double gramos) {
        this.grupo = grupo.toLowerCase();
        this.gramos = gramos;
    }
}

class Pelicula {
    String titulo;
    int anio;
    String genero;

    public Pelicula(String titulo, int anio, String genero) {
        this.titulo = titulo;
        this.anio = anio;
        this.genero = genero;
    }
}

public class SistemaAlimentacionYPeliculas {

    // Módulo para ingresar nutrientes
    public static List<Nutriente> ingresarNutrientes(Scanner sc) {
        List<Nutriente> lista = new ArrayList<>();
        System.out.print("¿Cuántos ingredientes deseas ingresar? ");
        int n = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Ingrediente #" + (i + 1) + " - Grupo (caloría, proteína, grasa, carbohidrato): ");
            String grupo = sc.nextLine();
            System.out.print("Cantidad en gramos: ");
            double gramos = sc.nextDouble(); sc.nextLine();
            lista.add(new Nutriente(grupo, gramos));
        }
        return lista;
    }

    // Módulo para calcular y mostrar resultado nutricional
    public static void evaluarComida(List<Nutriente> lista) {
        double calorias = 0, proteinas = 0, grasas = 0, carbohidratos = 0;

        for (Nutriente n : lista) {
            switch (n.grupo) {
                case "caloría": calorias += n.gramos; break;
                case "proteína": proteinas += n.gramos; break;
                case "grasa": grasas += n.gramos; break;
                case "carbohidrato": carbohidratos += n.gramos; break;
            }
        }

        System.out.println("\n--- Resumen Nutricional ---");
        System.out.println("Calorías: " + calorias);
        System.out.println("Proteínas: " + proteinas);
        System.out.println("Grasas: " + grasas);
        System.out.println("Carbohidratos: " + carbohidratos);

        if (grasas <= 20 && carbohidratos <= 50 && proteinas >= 10) {
            System.out.println("La comida es nutricionalmente aceptable.");
        } else {
            System.out.println("La comida NO es nutricionalmente aceptable.");
        }
    }

    // Módulo para ingresar películas
    public static List<Pelicula> ingresarPeliculas(Scanner sc) {
        List<Pelicula> lista = new ArrayList<>();
        System.out.print("\n¿Cuántas películas deseas ingresar? ");
        int n = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Título de la película #" + (i + 1) + ": ");
            String titulo = sc.nextLine();
            System.out.print("Año de lanzamiento: ");
            int anio = sc.nextInt(); sc.nextLine();
            System.out.print("Género: ");
            String genero = sc.nextLine();
            lista.add(new Pelicula(titulo, anio, genero));
        }
        return lista;
    }

    // Módulo para ordenar y mostrar películas
    public static void ordenarMostrarPeliculas(List<Pelicula> peliculas) {
        peliculas.sort(Comparator.comparingInt(p -> p.anio));
        System.out.println("\n--- Películas ordenadas por año ---");
        for (Pelicula p : peliculas) {
            System.out.println(p.anio + " - " + p.titulo + " [" + p.genero + "]");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nutrientes
        List<Nutriente> nutrientes = ingresarNutrientes(sc);
        evaluarComida(nutrientes);

        // Películas
        List<Pelicula> peliculas = ingresarPeliculas(sc);
        ordenarMostrarPeliculas(peliculas);
    }
}
