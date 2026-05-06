package Clases.Musica;

import java.time.LocalDate;

public class Reproduccion{
    private final Cancion cancion;
    private final LocalDate fechaReproduccion;
    private final int segundoDetenido;
    private final int segundosEscuchados;
     
    public Reproduccion(Cancion cancion, LocalDate fechaReproduccion, int segundoDetenido, int segundosEscuchados) {
        this.cancion = cancion;
        this.fechaReproduccion = fechaReproduccion;
        this.segundoDetenido = segundoDetenido;
        this.segundosEscuchados = segundosEscuchados;
        
        /*
        play => 1 cancion
        de esa cancion se reproduce un tiempo determinado, //timepo reproducido
         y se detiene en un segundo específico. //segundo detenido
        se guarda la fecha de reproducción, //fecha de reproducción
         el segundo en el que se detuvo y los segundos que se escucharon. segundos escuchados y segundos faltantes

        
        */
    }

    public Cancion getCancion() {
        return cancion;
    }

    public LocalDate getFechaReproduccion() {
        return fechaReproduccion;
    }

    public int getSegundoDetenido() {
        return segundoDetenido;
    }

    public int getSegundosEscuchados() {
        return segundosEscuchados;
    }

    public boolean reproducir() {
        // Lógica para reproducir la canción
        
        return true;
    }
    
}
