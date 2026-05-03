package Clases;

import java.time.LocalDate;

public class TarjetaBancaria {
    private final String numeroTarjeta;
    private LocalDate fechaCaducidad;
    private String NombreTitular;
    private int CVV;

    public TarjetaBancaria(String numeroTarjeta, LocalDate fechaCaducidad,
         String nombreTitular, int CVV) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.NombreTitular = nombreTitular;
        this.CVV = CVV;
    }

}
