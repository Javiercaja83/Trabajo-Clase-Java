package Clases.Musica;

import java.util.LinkedList;

public class Playlist {

    private String titulo;
    private LinkedList<Cancion> canciones;

    public Playlist(String titulo) {
        this.titulo = titulo;
        this.canciones = new LinkedList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public LinkedList<Cancion> getCanciones() {
        return canciones;
    }

    public void addCancion(Cancion c) {
        canciones.add(c);
    }

    public boolean estaVacia() {
        return canciones.isEmpty();
    }
}
    

    










    

