import java.util.Scanner;
public class HolaMundo {
    public static void main(String[] args) {
        Scanner Scan=new Scanner (System.in);
        System.out.println("como te llamas?");
        String nombre=Scan.nextLine();
        System.out.println("Hola " + nombre);

        Scan.close();
    }

}

//pida los 3 valores de un triangulo. que pida los 3 lados y el angulo.
//si ese angulo es rectangulo que pueda obviar uno de los 3 valores.
//si falta la hipotenusa que lo calcule y si falta uno de los catetos que pida el angulo.