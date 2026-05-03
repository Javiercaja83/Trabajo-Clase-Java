package Clases.Musica;

import java.time.LocalDate;


public class Artista {


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


}
