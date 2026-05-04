import java.time.LocalDate;
import java.util.Scanner;

import Clases.TarjetaBancaria;
import Clases.Usuario;
import Clases.Excepciones.ContraseniaInvalidaException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args)   {
        menuPrincipal();

    }

    public static void menuPrincipal() {
        boolean salir = false;
        int opcion = 0;

        while (!salir) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        
    }

    public static Usuario crearUsuario() {
        //todo: crear usuario con sus atributos, manejar la excepcion de contraseña inválida y mostrar un mensaje de error al usuario si la contraseña no cumple con los requisitos.
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
        
        System.out.print("Fecha de Registro (YYYY-MM-DD): ");
        fechaRegistro = LocalDate.now(); // Asignamos la fecha actual como fecha de registro
        
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
            return null;
        }
        
        System.out.print("Nombre del Titular: ");
        nombreTitular = scanner.nextLine();
        
        System.out.print("CVV: ");
        CVV = scanner.nextInt();

        tarjetaBancaria = new TarjetaBancaria(numeroTarjeta, fechaCaducidad, nombreTitular, CVV);
        try {
            Usuario usuario = new Usuario(nombre, fechaNacimiento, primerApellido, segundoApellido, fechaRegistro, email, password);
            usuario.setTarjetaBancaria(tarjetaBancaria);
            System.out.println("Usuario creado exitosamente.");
            return usuario;
        } catch (ContraseniaInvalidaException e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
        }
        return null;
    }
}
