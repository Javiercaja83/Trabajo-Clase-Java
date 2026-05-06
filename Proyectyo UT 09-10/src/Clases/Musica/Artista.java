package Clases.Musica;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;


public class Artista{


    private int codigo;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String pais;

    public Artista(int codigo, LocalDate fechaNacimiento, String nombre, String pais) {
        this.codigo = codigo;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.pais = pais;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean guardarDatos(Artista artista) {
        boolean exito = false;
        
        try (     FileOutputStream writer = new FileOutputStream("artista.txt", true); // true para modo append
            BufferedOutputStream bos = new BufferedOutputStream(writer)) {
            
             
            bos.write(String.format("Código: %d, Nombre: %s, Fecha de Nacimiento: %s, País: %s%n",
                    artista.getCodigo(), artista.getNombre(), artista.getFechaNacimiento(), artista.getPais()).getBytes());
            exito = true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return exito;


    }

}