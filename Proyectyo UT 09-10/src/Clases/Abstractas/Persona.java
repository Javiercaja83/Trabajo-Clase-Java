package Clases.Abstractas;

import java.time.LocalDate;
import java.io.Serializable;

/*
* Esta clase existe porque tanto el Usuario como el Artista comparten atributos comunes como el nombre y la fecha de nacimiento.
  Al hacerla abstracta, evitamos que se puedan crear instancias directas de Persona, ya que solo tiene sentido crear instancias de Usuario o Artista.
  Además, al implementar Serializable, permitimos que los objetos de las clases que heredan de Persona puedan ser serializados, 
  lo cual es útil para guardar su estado en archivos como en el caso de Artista.
*/

public abstract class Persona implements Serializable {
    private String nombre;
    private LocalDate fechaNacimiento;

    public Persona(String nombre, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
