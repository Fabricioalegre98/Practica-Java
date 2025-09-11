import java.util.Scanner;

      import java.util.Scanner;

      public class clima {
          public static void main(String[] args) {
              double[][] clima = new double[3][3];
              String[] estados = {"NUBLADO", "SOLEADO", "LLUVIOSO"};

              // Inicializar la matriz de probabilidades
              // NUBLADO hoy
              clima[0][0] = 0.33; // NUBLADO mañana
              clima[0][1] = 0.01; // SOLEADO mañana
              clima[0][2] = 0.66; // LLUVIOSO mañana

              // Puedes definir las otras filas según tus reglas
              // SOLEADO hoy
              clima[1][0] = 0.2;
              clima[1][1] = 0.7;
              clima[1][2] = 0.1;

              // LLUVIOSO hoy
              clima[2][0] = 0.3;
              clima[2][1] = 0.2;
              clima[2][2] = 0.5;

              Scanner sc = new Scanner(System.in);
              int opcion;
              do {
                  int hoy;
                  do {
                      System.out.println("¿Cuál es el estado del clima hoy? (0: NUBLADO, 1: SOLEADO, 2: LLUVIOSO)");
                      hoy = sc.nextInt();
                  } while (hoy < 0 || hoy > 2);

                  System.out.println("Probabilidad de clima para el siguiente día:");
                  double maxProb = -1;
                  int maxIndex = -1;
                  for (int j = 0; j < 3; j++) {
                      System.out.printf("%s: %.2f%%\n", estados[j], clima[hoy][j] * 100);
                      if (clima[hoy][j] > maxProb) {
                          maxProb = clima[hoy][j];
                          maxIndex = j;
                      }
                  }
                  System.out.printf("La probabilidad mayor es: %s con %.2f%%\n", estados[maxIndex], maxProb * 100);

                  System.out.println("¿Desea consultar otro día? (1: Sí, 0: No)");
                  opcion = sc.nextInt();
              } while (opcion == 1);

              sc.close();
          }
      }