import Clases.Enums.Genero;
import Clases.Excepciones.ContraseniaInvalidaException;
import Clases.Musica.Artista;
import Clases.Musica.Cancion;
import Clases.Musica.GestorXmlPlaylist;
import Clases.Musica.HistorialReproducciones;
import Clases.Musica.Playlist;
import Clases.Musica.Reproduccion;
import Clases.TarjetaBancaria;
import Clases.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Cancion> canciones = new ArrayList<>();
    private static ArrayList<Playlist> playlists = new ArrayList<>();
    private static ArrayList<Artista> artistas = new ArrayList<>();
    private static HistorialReproducciones historial = new HistorialReproducciones();
    private static Playlist playlist = new Playlist("favoritos");

    public static void main(String[] args) {
        boolean sesionIniciada = false;

        cargarDatosPrueba();

        while (!sesionIniciada) {
            sesionIniciada = menuUsuario();
            if (sesionIniciada) {
                menuUsuarioLogueado();
            }
        }

    }
    //Primer metodo, menu para iniciar sesion o crear usuario, devuelve un booleano para saber si se ha iniciado sesion o no
    public static boolean menuUsuario() {
        //boolean del menu
        boolean salir = false;
        //boolean a devolver para saber si se ha iniciado sesion o no
        boolean sesionIniciada = false;
        int opcion = 0;
        //mientras no se haya iniciado sesion y no se haya seleccionado salir, se muestra el menu
        while (!salir && !sesionIniciada) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    sesionIniciada = crearUsuario(); // si el usuario se crea correctamente, se inicia sesion automaticamente
                    break;
                case 2:
                    sesionIniciada = iniciarSesion();   // si el inicio de sesion es correcto, se devuelve true y se inicia sesion,
                                                        // sino se devuelve false y se muestra el menu de nuevo
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        return sesionIniciada;
    }

    public static boolean crearUsuario() {
        //variables para almacenar los datos del usuario, se piden por consola y crear el usuario
        String nombre, primerApellido, segundoApellido, email, password, numeroTarjeta, nombreTitular;
        LocalDate fechaNacimiento, fechaRegistro, fechaCaducidad;
        TarjetaBancaria tarjetaBancaria;
        int CVV = 0;

        System.out.println("=== Crear Usuario ===");

        System.out.print("Nombre: ");
        nombre = scanner.nextLine();

        System.out.print("Primer Apellido: ");
        primerApellido = scanner.nextLine();

        System.out.print("Segundo Apellido: ");
        segundoApellido = scanner.nextLine();

        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
        fechaNacimiento = LocalDate.parse(scanner.nextLine());

        fechaRegistro = LocalDate.now(); // Asignamos la fecha actual como fecha de registro
        System.out.print("Fecha de Registro : " + fechaRegistro + "\n");

        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.print("Contraseña: ");
        password = scanner.nextLine();
        //como la tarjeta bancaria es otra clase, indicamos que se introduzcan los datos de la tarjeta bancaria,
        //  y se crea un objeto TarjetaBancaria con esos datos para asignarlo al usuario
        System.out.println("=== Información de la Tarjeta Bancaria ===");
        System.out.print("Número de Tarjeta: ");
        numeroTarjeta = scanner.nextLine();

        System.out.print("Fecha de Caducidad (YYYY-MM-DD): ");
        fechaCaducidad = LocalDate.parse(scanner.nextLine());
        // Validamos que la fecha de caducidad no sea anterior o igual a la fecha
        // actual, ya que una tarjeta vencida no es válida.
        if (fechaCaducidad.isBefore(LocalDate.now()) || fechaCaducidad.equals(LocalDate.now())) {
            System.out.println("La fecha de caducidad no es válida.");
            return false;
        }

        System.out.print("Nombre del Titular: ");
        nombreTitular = scanner.nextLine();

        System.out.print("CVV: ");
        CVV = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        // Creamos la tarjeta bancaria con los datos introducidos
        tarjetaBancaria = new TarjetaBancaria(numeroTarjeta, fechaCaducidad, nombreTitular, CVV);
        //intentamos crear el usuario con los datos introducidos, si la contraseña no cumple con los requisitos,
        // se lanza una excepción y se muestra un mensaje de error, devolviendo false para indicar que no se ha creado el usuario ni se ha iniciado sesión.
        // Si el usuario se crea correctamente, se devuelve true para indicar que se ha iniciado sesión automáticamente.
        try {
            Usuario usuario = new Usuario(nombre, fechaNacimiento, primerApellido, segundoApellido, fechaRegistro,
                    email, password);
            usuario.setTarjetaBancaria(tarjetaBancaria);
            usuarios.add(usuario);
            System.out.println("Usuario creado exitosamente.");
            return true;
        } catch (ContraseniaInvalidaException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
        return false;
    }

    public static boolean iniciarSesion() {
        // en esta funcion solo necesitamos el email y la contraseña para verificar si el usuario existe en la lista de usuarios,
        //  por lo que solo pedimos esos datos por consola
        String email, password;
        System.out.println("=== Iniciar Sesión ===");
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Contraseña: ");
        password = scanner.nextLine();

        //verificamos si existe un usuario con ese email y contraseña con un stream y un anyMatch,
        // que devuelve true si encuentra un usuario que cumpla con esa condición, y false si no lo encuentra,
        //  indicando si se ha iniciado sesión correctamente o no.
        boolean existeUsuario = usuarios.stream()
                .anyMatch(usuario -> usuario.getEmail().equals(email)
                        && usuario.getPassword().equals(password));
        if (existeUsuario) {
            System.out.println("Sesión iniciada correctamente.");
            return true;
        } else {
            System.out.println("Email o contraseña incorrectos.");
            return false;
        }

    }

    //menu una vez logueado
    public static void menuUsuarioLogueado() {
        int opcion;

        do {
            System.out.println("=== Menú Usuario ===");
            System.out.println("1. Buscar canción");
            System.out.println("2. Reproducir canción");
            System.out.println("3. Crear playlist");
            System.out.println("4. Guardar playlists en XML");
            System.out.println("5. Cerrar sesión");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Buscar canción .");
                    buscarCancion(playlist); //playlist es un atributo de la clase Main, asi que no hace falta pasarlo como parametro a la funcion
                     break;
                case 2:
                    System.out.println("Reproducir canción .");
                    reproducirCancionPorCodigo(playlist);
                    break;
                case 3:
                    createPlaylist();
                    break;
                case 4:
                    GestorXmlPlaylist.guardarPlaylist(playlists);
                    break;
                case 5:
                    playlist.getCanciones().stream().forEach(cancion -> cancion.guardarDatos(cancion));
                    artistas.stream().forEach(artista -> artista.guardarDatos(artista));
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
    }

    public static void reproducirCancionPorCodigo(Playlist playlist) {
        String codigoCancion;
        System.out.println("Ingrese el código de la canción que desea reproducir:");
        codigoCancion = scanner.nextLine();

        Cancion cancionEncontrada = playlist.getCanciones().stream()
                .filter(cancion -> String.valueOf(cancion.getCodigo()).equals(codigoCancion)) // Buscamos la canción por
                                                                                              // código con un stream y
                                                                                              // un filtro
                .findFirst()// es como un if else, si lo encuentra devuelve la primera canción que cumpla la
                            // condición,
                .orElse(null); // si no lo encuentra devuelve un Optional vacío

        if (cancionEncontrada != null) {
            reproducirCancion(cancionEncontrada);
        } else {
            System.out.println("Canción no encontrada.");
        }
    }

    public static void buscarCancion(Playlist playlist) {
        String busqueda;
        System.out.print("Introduce texto para buscar: ");
        busqueda = scanner.nextLine().toLowerCase();

        boolean encontrada = false;

        for (Cancion cancion : playlist.getCanciones()) {
            if (cancion.getTitulo().toLowerCase().contains(busqueda)) {
                System.out.println("Código: " + cancion.getCodigo() + " | Título: " + cancion.getTitulo());
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("No se encontraron canciones.");
        }
    }

    public static void createPlaylist() {
        System.out.print("Introduce el titulo de la playlist: ");
        String titulo = scanner.nextLine();
        Playlist nuevaPlaylist = new Playlist(titulo);

        System.out.println("Canciones disponibles:");
        for (Cancion cancion : canciones) {
            System.out.println("Codigo: " + cancion.getCodigo() + " | Titulo: " + cancion.getTitulo());
        }

        int codigo;
        do {
            System.out.print("Introduce el codigo de la cancion (-1 para terminar): ");
            codigo = scanner.nextInt();
            scanner.nextLine();

            if (codigo != -1) {
                Cancion cancionEncontrada = buscarCancionPorCodigo(codigo);
                if (cancionEncontrada != null) {
                    nuevaPlaylist.agregarCancion(cancionEncontrada);
                    System.out.println("Cancion anadida: " + cancionEncontrada.getTitulo());
                } else {
                    System.out.println("No existe una cancion con ese codigo.");
                }
            }
        } while (codigo != -1);

        playlists.add(nuevaPlaylist);
        playlist = nuevaPlaylist;
        System.out.println("Playlist creada: " + nuevaPlaylist.getTitulo());
    }

    public static Cancion buscarCancionPorCodigo(int codigo) {
        return canciones.stream()
                .filter(cancion -> cancion.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public static void reproducirPlaylist(Playlist playlist) {
        System.out.println("Reproduciendo playlist...");
        System.out.println(
                "'Introduzca el codigo '-1' para detener la reproducción de la playlist en cualquier momento.'");

        for (Cancion cancion : playlist.getCanciones()) {
            System.out.print("Pulsa cualquier numero para reproducir, o -1 para terminar: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == -1) {
                break;
            }

            reproducirCancion(cancion);
        }

    }

    public static void reproducirCancion(Cancion cancion) {

        System.out.println("Reproduciendo canción: " + cancion.getTitulo());
        System.out.println("Ingrese el segundo donde desea detener la canción:"); // pido el segundo donde se detenga la cancion

        int segundoDetencion = scanner.nextInt();
        scanner.nextLine();
        Reproduccion reproduccion = new Reproduccion(cancion, LocalDate.now(), segundoDetencion, segundoDetencion);
        // la fecha actual y los segundos escuchados (en este caso, el mismo que el segundo de detención)
        historial.agregarReproduccion(reproduccion);
        System.out.println("Canción detenida en el segundo: " + segundoDetencion);
    }

    public static void cargarDatosPrueba() {
        Artista artista1 = new Artista(01, LocalDate.of(2004, 6, 10), "javier", "gomez");
        Artista artista2 = new Artista(02, LocalDate.of(2003, 12, 1), "javier", "espania");
        artista1.guardarDatos(artista1);
        artista2.guardarDatos(artista2);
        artistas.add(artista1);
        artistas.add(artista2);


        Usuario usuario1 = new Usuario("javier", LocalDate.of(2004, 6, 10), "gomez", "espania", LocalDate.now(),
                "javier@test.com", "Prueba123");
        usuarios.add(usuario1);
        Cancion cancion1 = new Cancion("album1", artistas, 01, 240, LocalDate.of(2020, 5, 20), null, "cancion1");
        Cancion cancion2 = new Cancion("album2", artistas, 02, 200, LocalDate.of(2021, 3, 15), null, "cancion2");
        cancion1.guardarDatos(cancion1);
        cancion2.guardarDatos(cancion2);
        canciones.add(cancion1);
        canciones.add(cancion2);
        playlist.agregarCancion(cancion1);
        playlist.agregarCancion(cancion2);
        playlists.add(playlist);

    }

     public static void MostrarCancionMasEscuchada() {

        if (historial.getHistorial().isEmpty()) {
            System.out.println("No hay reproducciones");
            return;
        }

        HashMap<Cancion, Integer> contador = new HashMap<>();

        for (Reproduccion r : historial.getHistorial()) {
            Cancion c = r.getCancion();
            contador.put(c, contador.getOrDefault(c, 0) + 1);
        }

        Cancion mas = null;
        int max = 0;

        for (Cancion c : contador.keySet()) {
            if (contador.get(c) > max) {
                max = contador.get(c);
                mas = c;
            }
        }

        System.out.println("Canción más escuchada: " +
                (mas != null ? mas.getTitulo() : "Ninguna"));
    }

    public static void MostrarGeneroMasEscuchado() {

        HashMap<String, Integer> contador = new HashMap<>();

        for (Reproduccion r : historial.getHistorial()) {

            if (r.getCancion().getGeneros() == null) continue;

            for (Genero g : r.getCancion().getGeneros()) {

                String nombre = g.toString();

                contador.put(nombre,
                        contador.getOrDefault(nombre, 0) + 1);
            }
        }

        String mejor = null;
        int max = 0;

        for (String g : contador.keySet()) {

            if (contador.get(g) > max) {
                max = contador.get(g);
                mejor = g;
            }
        }

        System.out.println("Género más escuchado: " + mejor);
    }
public static void MostrarArtistaFavorito() {

    HashMap<String, Integer> contador = new HashMap<>();

    for (Reproduccion r : historial.getHistorial()) {

        if (r.getCancion().getArtistas() == null)
            continue;

        for (Artista a : r.getCancion().getArtistas()) {

            String nombreArtista = a.getNombre();

            contador.put(
                    nombreArtista,
                    contador.getOrDefault(nombreArtista, 0) + 1);
        }
    }

    String masEscuchado = null;
    int max = 0;

    for (String s : contador.keySet()) {

        if (contador.get(s) > max) {

            max = contador.get(s);
            masEscuchado = s;
        }
    }

    System.out.println("Artista más escuchado: " + masEscuchado);
}


}
