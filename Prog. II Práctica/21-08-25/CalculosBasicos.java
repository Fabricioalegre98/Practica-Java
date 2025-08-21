public class CalculosBasicos {
    public static void main(String[]args){
        int A,B, C;
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Ingrese el valor de A: ");
        A = sc.nextByte();
        System.out.println("Ingrese el valor de B: ");
        B = sc.nextByte();
        if (A>B) {
            C = A * B;
            System.out.println("El resultado es: " + C);
        }else if (A<B) {
            C = B /A;
            System.out.println("El resultado es: " + C);
        }else{
            C = A + B;
            System.out.println("El resultado es: " + C);
        }
    }
}
