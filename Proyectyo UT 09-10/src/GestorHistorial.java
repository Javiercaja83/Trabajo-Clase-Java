import Clases.Musica.HistorialReproducciones;
import Clases.Musica.Reproduccion;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GestorHistorial{
    
public static void guardarHistorial(HistorialReproducciones historial) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("historial.txt"))){

        for (Reproduccion r : historial.getHistorial()){
            String linea = r.getCancion() + ";" +
            r.getFechaReproduccion() + ";" +
            r.getSegundosEscuchados();

            bw.write(linea);
            bw.newLine();
        }
        System.out.println("Historial guardado correctamente");
    } catch (IOException e){
        e.printStackTrace();
    }

}
}

