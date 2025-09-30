import java.util.Scanner;

public class ingresarNumeros {
    //ingresar 3 números mediante scanner e imprimir uno debajo ded otro
    public static void main(String[] args){
        Scanner numero = new Scanner(System.in);
        int myInt1 = numero.nextInt();
        int myInt2 = numero.nextInt();

        System.out.println(myInt1);
        System.out.println(myInt2);
        numero.close();

    }
}
