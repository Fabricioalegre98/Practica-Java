public class Coche {
    //atributos
    String Color;
    String Marca;
    int Velocidad;
    double Peso;

    //Métodos (comportamiento)
    public void acelerar(int cantidad) {
        this.Velocidad += cantidad;
    }

    public void frenar(int cantidad) {
        this.Velocidad -= cantidad;
    }
    public Coche(String Marca, String Color) {
        //Constructor
        // creamos 2 objetos
        this.Marca = Marca; //asignamos un estado especifico
        this.Color = Color;
    }
    public void mostrarDatos(){
        System.out.println("La marca del coche es "+this.Marca+" y es de color " +this.Color);
    }

}
