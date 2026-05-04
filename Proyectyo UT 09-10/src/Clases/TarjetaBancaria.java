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

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNombreTitular() {
        return NombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        NombreTitular = nombreTitular;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int cVV) {
        CVV = cVV;
    }

}
