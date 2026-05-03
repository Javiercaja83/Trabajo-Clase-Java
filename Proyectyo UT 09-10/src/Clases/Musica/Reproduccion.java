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

    
}
