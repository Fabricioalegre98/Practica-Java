import java.util.Scanner;
public class Switch {
    public static void main(String[] args) {
        System.out.println("ingrese un numero del 1 al 3: ");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        switch (a) {
            case 1:
                System.out.println("a es 1");
                break;
            case 2:
                System.out.println("a es 2");
                break;
            case 3:
                System.out.println("a es 3");
                break;
            default:
                System.out.println("a no es ni 1, ni 2, ni 3");
                break;
        }
    }
}
