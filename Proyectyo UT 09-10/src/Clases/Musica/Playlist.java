
package Clases.Musica;

import java.util.LinkedList;

public class Playlist {

   
    private LinkedList <Cancion> canciones = new LinkedList<>();

    // Creamos la playlist
    public Playlist(LinkedList<Cancion> canciones) {
         this.canciones = canciones;
    }

    // Quitamos la playlist por titulo
    public void quitarPlaylist(String titulo){
        boolean existeTitulo = canciones.stream().anyMatch(cancion->cancion.getTitulo().equals(titulo));
        if (existeTitulo){
           canciones.removeAll(canciones);
        } else {
            System.out.println("No existe la playlist");
            
        }

    }

    

    










    
}
