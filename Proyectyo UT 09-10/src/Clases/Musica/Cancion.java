package Clases.Musica;

import Clases.Enums.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public class Cancion {

    private int codigo;
    private String titulo;
    private List<Artista> artistas;
    private String album;
    private List<Genero> generos;
    private int duracion;
    private LocalDate fechaLanzamiento;

    public Cancion(String album, List<Artista> artistas, int codigo, int duracion, LocalDate fechaLanzamiento, List<Genero> generos, String titulo) {
        this.album = album;
        this.artistas = artistas;
        this.codigo = codigo;
        this.duracion = duracion;
        this.fechaLanzamiento = fechaLanzamiento;
        this.generos = generos;
        this.titulo = titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public boolean guardarDatos(Cancion cancion) {
        boolean exito = false;
        
        try (     FileOutputStream writer = new FileOutputStream("cancion.txt", true); // true para modo append
            BufferedOutputStream bos = new BufferedOutputStream(writer)) {
            
             
            bos.write(String.format(
                "Código: %d, Nombre: %s, Fecha de Lanzamiento: %s, Álbum: %s%n",
                    cancion.getCodigo(), cancion.getTitulo(), cancion.getFechaLanzamiento(), cancion.getAlbum()).getBytes());

            exito = true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return exito;

    }

}
