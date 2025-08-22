
public class AsignarValores {
    public static void main(String[] args) {

        int a = 100;
        double d = a;
        float b = 100.00f;
        int c = (int) b; // Cast necesario para convertir float a int
        final float pi = 3.14f; // Constante
        System.out.println("Valor de a (int): " + a);
        System.out.println("Valor de d (double): " + d);
        System.out.println("Valor de b (float): " + b);
        System.out.println("Valor de c (int): " + c);
        System.out.println("Valor de pi (constante float): " + pi);


        boolean b1 = a > 0 && b > 0;
        if (!b1) {
            System.out.println("a no es mayor que 0 y b es mayor que 0");
        } else {
            System.out.println("Condición no cumplida");

        }

    }
}
