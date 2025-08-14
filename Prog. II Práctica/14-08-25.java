//mi primer programa en Java
public class Programa
{
    String nombre;
    int edad;
    String especie; //"perro", "gato".
    public void habla() 
    {
        if (this.especie.equals("perro"))
            {
            System.out.println("guau");
                }else if (this.especie.equals("gato"))
                    {
                        System.out.println("miau");
                    }
    }
    System.out.println("Nombre: "+this.nombre);
    System.out.println("Especie: "+this.especie);
    System.out.println("Edad: "+this.edad);
    System.out.println("Me llamo: "+this.nombre +"soy un: "+this.especie +"y tengo: "+this.edad +" años");
}