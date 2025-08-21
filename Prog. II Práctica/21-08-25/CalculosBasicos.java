public class CalculosBasicos {
    public static void main(String[]args){
        int A,B;
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Ingrese el valor de A: ");
        A = sc.nextByte();
        System.out.println("Ingrese el valor de B: ");
        B = sc.nextByte();
        if (A>B) {
            A = A * B;
            System.out.println("El resultado es: " + A);
        }else if (A<B) {
            A = B /A;
            System.out.println("El resultado es: " + A);
        }else{
            A = A + B;
            System.out.println("El resultado es: " + A);
        }
    }
}
