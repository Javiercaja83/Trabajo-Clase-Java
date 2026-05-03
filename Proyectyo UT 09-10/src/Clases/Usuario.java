package Clases;

import Clases.Abstractas.Persona;
import Clases.Excepciones.ContraseniaInvalidaException;

import java.time.LocalDate;
                    //Importamos la clase Persona desde el paquete Clases.Abstractas para que Usuario pueda heredar de ella.
public class Usuario extends Persona {
    private String PrimerApellido;
    private String SegundoApellido;
    private LocalDate FechaRegistro;
    private final String email;
    private String password;
    private TarjetaBancaria tarjetaBancaria;

    public Usuario(String nombre, LocalDate fechaNacimiento, String primerApellido, String segundoApellido, 
        LocalDate fechaRegistro, String email, String password) {
        // Llamamos al constructor de la clase padre (Persona) para inicializar los atributos heredados.
        super(nombre, fechaNacimiento);
        this.PrimerApellido = primerApellido;
        this.SegundoApellido = segundoApellido;
        this.FechaRegistro = fechaRegistro;
        this.email = email;
        //aqui validamos la contraseña antes de asignarla al atributo, para asegurarnos de que cumple con los requisitos de seguridad.
        if (esContraseniaValida(password)) {
            this.password = password;
        } else {
            // Si la contraseña no es válida, lanzamos una excepción para informar al usuario del error.
            throw new ContraseniaInvalidaException("La contraseña no cumple con los requisitos.");
        }
    }

    public boolean esContraseniaValida(String password) {
        // Verificar que la contraseña tenga al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número.
        //si tiene menos de 8 caracteres, no es válida
        if (password.length() < 8) {
            return false;
        }
        //Una bandera por cada requisito para verificar que se cumplan todos
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        //recoremos cada caracter de la contraseña para verificar si cumple con los requisitos
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) { //si el caracter es una letra mayúscula, marcamos la bandera correspondiente
                tieneMayuscula = true;
            } else if (Character.isLowerCase(c)) { //si el caracter es una letra minúscula, marcamos la bandera correspondiente
                tieneMinuscula = true;
            } else if (Character.isDigit(c)) { //si el caracter es un número, marcamos la bandera correspondiente
                tieneNumero = true;
            }
        }

        //Como el ejercicio solo nos exige una de cada tipo de caracter, si las tres banderas son verdaderas, la contraseña es válida.
        //  no hace falta contar cuántas mayúsculas, minúsculas o números tiene, con que tenga al menos uno de cada tipo es suficiente.
        return tieneMayuscula && tieneMinuscula && tieneNumero;
    }

}