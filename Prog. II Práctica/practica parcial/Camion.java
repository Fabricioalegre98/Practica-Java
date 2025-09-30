/*La clase Camion, que también hereda de Vehiculo.
        Java
/**
 * Representa un Camion, que es un tipo específico de Vehiculo.
 */
public class Camion extends vehiculo {
    private double capacidadCargaKg;
    private int cantidadEjes;

    public Camion(String marca, String modelo, String patente, int anioFabricacion,
                  double capacidadCargaKg, int cantidadEjes) {
        super(marca, modelo, patente, anioFabricacion);
        this.capacidadCargaKg = capacidadCargaKg;
        this.cantidadEjes = cantidadEjes;
    }

    @Override
    public String obtenerFichaTecnica() {
        return "FICHA TÉCNICA - CAMIÓN:\n" +
                "  - Marca y Modelo: " + marca + " " + modelo + "\n" +
                "  - Patente: " + patente + "\n" +
                "  - Capacidad de Carga: " + capacidadCargaKg + " Kg\n" +
                "  - Ejes: " + cantidadEjes;
    }
}
