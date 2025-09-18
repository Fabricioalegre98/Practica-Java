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

//pedrile al usuario que ingrese 2 valores numericos y
// que se pueda hacer una operacion... si A es mayor que B entonces a A
// se le asigna A*B, si A es menor que ve es B/A y si son iguales se suman y el
// resultado se guarda en la variable A