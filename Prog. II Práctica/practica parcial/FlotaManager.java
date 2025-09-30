/*El gestor que encapsula el array de Vehiculo y su lógica de crecimiento. 🛠️
Java
/**
 * Gestiona la flota de vehículos, manejando un array interno
 * con capacidad de redimensionamiento automático.
 */
public class FlotaManager {
    private vehiculo[] vehiculos;
    private int cantidadActual;
    private static final int CAPACIDAD_INICIAL = 3; // Capacidad inicial para 3 vehículos

    public FlotaManager() {
        this.vehiculos = new vehiculo[CAPACIDAD_INICIAL];
        this.cantidadActual = 0;
    }

    /**
     * Agrega un vehículo a la flota. Si el array está lleno, lo agranda.
     * @param v El vehículo a agregar.
     */
    public void agregarVehiculo(vehiculo v) {
        if (cantidadActual == vehiculos.length) {
            redimensionar();
        }
        vehiculos[cantidadActual] = v;
        cantidadActual++;
        System.out.println("-> Vehículo " + v.patente + " agregado a la flota.");
    }

    /**
     * Duplica la capacidad del array de vehículos.
     */
    private void redimensionar() {
        System.out.println("\n¡INFO: Capacidad de la flota superada! Duplicando espacio...");
        int nuevaCapacidad = vehiculos.length * 2;
        vehiculo[] nuevoArray = new vehiculo[nuevaCapacidad];
        System.arraycopy(vehiculos, 0, nuevoArray, 0, vehiculos.length);
        this.vehiculos = nuevoArray;
        System.out.println("INFO: Nueva capacidad para " + nuevaCapacidad + " vehículos.\n");
    }

    /**
     * Devuelve un String con la información de todos los vehículos en la flota.
     * @return Un String con el inventario completo.
     */
    public String listarTodaLaFlota() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- INVENTARIO COMPLETO DE LA FLOTA ---\n");
        for (int i = 0; i < cantidadActual; i++) {
            vehiculo vehiculo = vehiculos[i];
            sb.append("-----------------------------------------\n");
            // La magia del polimorfismo: se llama al método de Coche o Camion según corresponda.
            sb.append(vehiculo.obtenerFichaTecnica()).append("\n");
            sb.append("  - Antigüedad: ").append(vehiculo.calcularAntiguedad()).append(" años.\n");
        }
        sb.append("-----------------------------------------\n");
        sb.append("Total de vehículos en la flota: ").append(cantidadActual);
        return sb.toString();
    }
}
