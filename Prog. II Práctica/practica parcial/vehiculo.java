/*Objetivo del Ejercicio:
Reforzar los conocimientos de Programación Orientada a Objetos (POO) para el examen.
Deberás modelar un sistema para gestionar una flota de vehículos,
aplicando herencia desde una clase abstracta y encapsulando la lógica de
almacenamiento en una clase gestora que maneje un array de tamaño variable de forma manual.

Vehiculo.java
        Esta es la clase base abstracta que define los atributos y comportamientos comunes a todos los vehículos.
*/
import java.time.LocalDate;

/**
 * Clase abstracta que representa un Vehículo genérico.
 * No puede ser instanciada directamente.
 */
public abstract class vehiculo {
    protected String marca;
    protected String modelo;
    protected String patente;
    protected int anioFabricacion;

    public vehiculo(String marca, String modelo, String patente, int anioFabricacion) {
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.anioFabricacion = anioFabricacion;
    }

    /**
     * Calcula la antigüedad del vehículo en años.
     * @return Los años de antigüedad.
     */
    public int calcularAntiguedad() {
        int anioActual = LocalDate.now().getYear();
        return anioActual - this.anioFabricacion;
    }

    /**
     * Método abstracto que debe ser implementado por las clases hijas.
     * @return Un String con los detalles técnicos específicos del vehículo.
     */
    public abstract String obtenerFichaTecnica();
}




