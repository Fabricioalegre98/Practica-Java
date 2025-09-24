import java.util.Scanner;

public class ingresarNumeros {
    //ingresar 3 numeros mediante scanner e imprimir uno debajo ded otro
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int myInt1 = scanner.nextInt();
        int myInt2 = scanner.nextInt();

        System.out.println(myInt1);
        System.out.println(myInt2);
        scanner.close();

    }
}
