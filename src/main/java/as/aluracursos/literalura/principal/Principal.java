package as.aluracursos.literalura.principal;

import as.aluracursos.literalura.model.DatosGenerales;
import as.aluracursos.literalura.model.DatosLibro;
import as.aluracursos.literalura.model.Libros;
import as.aluracursos.literalura.repository.LibroRepository;
import as.aluracursos.literalura.service.ConsumoAPI;
import as.aluracursos.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorio;

    private List<Libros> libros;
    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                1 - Buscar libro por titulo
                2 - Registrar libro en la base de datos
                3 - Listar libros registrados
                4 - Buscar libros por autor
                5 - Listar autores vivos por año
                6 - Listar libros por idioma
                              
                0 - Salir
                """;
            System.out.println(menu);

            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
                System.out.println("Error: Por favor ingrese un número válido.");
            }

            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    registrarLibroEnBaseDeDatos();
                    break;
                case 3:
                    mostrarLibrosRegistrados();
                    break;
                case 4:
                    mostrarAutoresRegistrados();
                    break;
                case 5:
                    mostrarAutoresVivosPorAnio();
                    break;
                case 6:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    // El -1 que asignamos en el catch caerá aquí
                    System.out.println("Opción inválida");
            }
        }
    }

    private DatosGenerales buscarLibroPorTitulo() {
        System.out.println("Ingresa el título del libro que deseas buscar:");
        var tituloLibro = teclado.nextLine();
        var url = URL_BASE + tituloLibro.replace(" ", "%20");
        var json = consumoApi.getData(url);

//        if (json != null && !json.isEmpty()) {
//            var datos = conversor.obtenerDatos(json, DatosGenerales.class);
//        } else {
//            System.out.println("Error: No se recibió respuesta de la API o no existe el libro");
//        }
        var datos = conversor.obtenerDatos(json, DatosGenerales.class);
        return datos;
    }

    private void registrarLibroEnBaseDeDatos() {
        DatosGenerales datos = buscarLibroPorTitulo();
        Libros libro = new Libros(datos);
        repositorio.save(libro);
        //datosSeries.add(datos);
        System.out.println(datos);
    }

    private void mostrarLibrosRegistrados() {
        libros = repositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void mostrarAutoresRegistrados() {
        System.out.println("Ingresa el nombre del autor que deseas buscar:");
        var nombreAutor = teclado.nextLine();
        var librosEncontrados = repositorio.findByAutorContainsIgnoreCase(nombreAutor);

        if (librosEncontrados.isEmpty()) {
            System.out.println("No existe ningún libro con ese autor en la base de datos.");
        } else {
            librosEncontrados.stream()
                    .sorted(Comparator.comparing(Libros::getTitulo))
                    .forEach(System.out::println);
        }
    }

    private void mostrarAutoresVivosPorAnio() {
        System.out.println("Ingresa el año a partir del cual deseas buscar autores vivos:");
        var anio = teclado.nextInt();
        var librosEncontrados = repositorio.autoresVivosEnDeterminadoAnio(anio);
        if (librosEncontrados.isEmpty()) {
            System.out.println("No existe ningún autor vivo a partir de ese año en la base de datos.");
        } else {
            librosEncontrados.stream()
                    .sorted(Comparator.comparing(Libros::getAutor))
                    .forEach(System.out::println);
        }
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("""
            Ingresa el idioma para buscar libros:
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
            """);

        var entradaUsuario = teclado.nextLine();

        var idiomaCoded = "";

        if (entradaUsuario.equals("es") || entradaUsuario.startsWith("espa")) {
            idiomaCoded = "es";
        } else if (entradaUsuario.equals("en") || entradaUsuario.startsWith("ing")) {
            idiomaCoded = "en";
        } else if (entradaUsuario.equals("fr") || entradaUsuario.startsWith("fran")) {
            idiomaCoded = "fr";
        } else if (entradaUsuario.equals("pt") || entradaUsuario.startsWith("port")) {
            idiomaCoded = "pt";
        } else {
            System.out.println("Idioma no reconocido.");
            return;
        }
        var librosEncontrados = repositorio.buscarLibrosPorIdioma(idiomaCoded);

        if (librosEncontrados.isEmpty()) {
            System.out.println("No existe ningún libro en ese idioma en la base de datos.");
        } else {
            librosEncontrados.stream()
                    .sorted(Comparator.comparing(Libros::getTitulo))
                    .forEach(System.out::println);
        }
    }
}
