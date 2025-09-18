public class Programa {
    public String nombre;
    public int edad;
    public String especie;

    public void habla() {
        System.out.println("¡Guau! ¡Guau!");
    }

    public void identificarse() {
        System.out.println("Soy " + nombre + ", tengo " + edad + " años y soy un " + especie + ".");
    }

    public static void main(String[] args) {
        Programa p = new Programa();
        p.nombre = "Firulais";
        p.edad = 3;
        p.especie = "perro";
        p.habla();
        p.identificarse();
    }
}