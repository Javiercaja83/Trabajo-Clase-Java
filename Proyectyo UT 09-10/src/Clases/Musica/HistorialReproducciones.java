package Clases.Musica;

import java.util.ArrayList;
import java.util.List;

public class HistorialReproducciones {
    
    private final List<Reproduccion> historial;
    private static final int MAX_REPRODUCCIONES = 100;
    
    public HistorialReproducciones() {
        this.historial = new ArrayList<>();
    }

    public void agregarReproduccion(Reproduccion reproduccion) {
        if (historial.size() >= MAX_REPRODUCCIONES) {
            historial.remove(0); // Elimina la reproducción más antigua
        }
     
        historial.add(reproduccion);
    }

    public List<Reproduccion> getHistorial() {
        return new ArrayList<>(historial); // Devuelve una copia del historial
    }

    public void limpiarHistorial() {
        historial.clear();
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("El historial de reproducciones está vacío.");
        
        } else { 
            System.out.println("-------- Historial de Reproducciones --------");
            for (Reproduccion reproduccion : historial) {
                System.out.println("Canción: " + reproduccion.getCancion().getTitulo() +
                                   " | Fecha: " + reproduccion.getFechaReproduccion() +
                                   " | Segundos Escuchados: " + reproduccion.getSegundosEscuchados());
            }
             System.out.println("--------------------------------------------");
        }
    }

}
