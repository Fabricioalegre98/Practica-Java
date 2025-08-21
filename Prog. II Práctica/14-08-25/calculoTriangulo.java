import java.util.Scanner;

public class calculoTriangulo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿El triángulo es rectángulo? (s/n): ");
        String esRectangulo = sc.next();

        double a = 0, b = 0, c = 0, angulo = 0;

        if (esRectangulo.equalsIgnoreCase("s")) {
            System.out.print("¿Qué lado falta? (a=cateto1, b=cateto2, c=hipotenusa, n=ninguno): ");
            String falta = sc.next();

            if (falta.equalsIgnoreCase("c")) {
                System.out.print("Ingrese cateto a: ");
                a = sc.nextDouble();
                System.out.print("Ingrese cateto b: ");
                b = sc.nextDouble();
                c = Math.sqrt(a * a + b * b);
                System.out.println("La hipotenusa es: " + c);
            } else if (falta.equalsIgnoreCase("a")) {
                System.out.print("Ingrese cateto b: ");
                b = sc.nextDouble();
                System.out.print("Ingrese hipotenusa c: ");
                c = sc.nextDouble();
                System.out.print("Ingrese el ángulo entre b y c (en grados): ");
                angulo = Math.toRadians(sc.nextDouble());
                a = b * Math.tan(angulo);
                System.out.println("El cateto a es: " + a);
            } else if (falta.equalsIgnoreCase("b")) {
                System.out.print("Ingrese cateto a: ");
                a = sc.nextDouble();
                System.out.print("Ingrese hipotenusa c: ");
                c = sc.nextDouble();
                System.out.print("Ingrese el ángulo entre a y c (en grados): ");
                angulo = Math.toRadians(sc.nextDouble());
                b = a * Math.tan(angulo);
                System.out.println("El cateto b es: " + b);
            } else {
                System.out.print("Ingrese cateto a: ");
                a = sc.nextDouble();
                System.out.print("Ingrese cateto b: ");
                b = sc.nextDouble();
                System.out.print("Ingrese hipotenusa c: ");
                c = sc.nextDouble();
                System.out.println("Lados ingresados: a=" + a + ", b=" + b + ", c=" + c);
            }
        } else {
            System.out.print("Ingrese lado a: ");
            a = sc.nextDouble();
            System.out.print("Ingrese lado b: ");
            b = sc.nextDouble();
            System.out.print("Ingrese lado c: ");
            c = sc.nextDouble();
            System.out.print("Ingrese un ángulo (en grados): ");
            angulo = sc.nextDouble();
            System.out.println("Lados: a=" + a + ", b=" + b + ", c=" + c + ", ángulo=" + angulo);
        }

        sc.close();
    }
}
