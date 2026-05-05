import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Clases.TarjetaBancaria;
import Clases.Usuario;
import Clases.Excepciones.ContraseniaInvalidaException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args)   {
        menuUsuario();

    }

    public static boolean menuUsuario() {
        boolean salir = false;
        boolean sesionIniciada = false;
        int opcion = 0;

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
                   sesionIniciada = crearUsuario();

                break;
                case 2:
                    sesionIniciada = iniciarSesion();
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
        
        System.out.println("=== Información de la Tarjeta Bancaria ===");
        System.out.print("Número de Tarjeta: ");
        numeroTarjeta = scanner.nextLine();
        
        System.out.print("Fecha de Caducidad (YYYY-MM-DD): ");
        fechaCaducidad = LocalDate.parse(scanner.nextLine());
        // Validamos que la fecha de caducidad no sea anterior o igual a la fecha actual, ya que una tarjeta vencida no es válida.
        if (fechaCaducidad.isBefore(LocalDate.now()) || fechaCaducidad.equals(LocalDate.now())) {
            System.out.println("La fecha de caducidad no es válida.");
            return false;
        }
        
        System.out.print("Nombre del Titular: ");
        nombreTitular = scanner.nextLine();
        
        System.out.print("CVV: ");
        CVV = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        tarjetaBancaria = new TarjetaBancaria(numeroTarjeta, fechaCaducidad, nombreTitular, CVV);
        try {
            Usuario usuario = new Usuario(nombre, fechaNacimiento, primerApellido, segundoApellido, fechaRegistro, email, password);
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
        String email, password; 
        System.out.println("=== Iniciar Sesión ===");
        System.out.print("Email: ");
        email = scanner.nextLine();
        System.out.print("Contraseña: ");
        password = scanner.nextLine();
        
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

    
}

