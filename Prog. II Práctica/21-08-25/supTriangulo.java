import java.util.Scanner; // Importamos la clase Scanner para leer la entrada del usuario

public class supTriangulo {
    private static int A= 10;
    private static final int B =20;

    public static void main(String[]args) {
      System.out.println("tu nombre: ");
      Scanner Input = new Scanner(System.in);
        String Nombre = Input.nextLine(); // Leemos el nombre del usuario
      System.out.println(Nombre);

        // Ejemplo de suma de dos números


        System.out.println(A+B);
        A = A + B; // Suma de A y B

    }
}
