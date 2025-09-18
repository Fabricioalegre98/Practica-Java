import java.util.Scanner;

public class CalculadorDeMarkov {
    public static Double[][] Mp = new Double[3][3];
    public static final String[] ESTADOS = {"Nublado", "Soleado", "Lluvioso"};
    public static int cantidadMediciones = 1;

    public static void main(String[] args) {
        inicializarMatriz();
        Scanner scanner = new Scanner(System.in);

        imprimirMatriz();

        System.out.println("Ingrese el estado actual (0=Nublado, 1=Soleado, 2=Lluvioso):");
        int estadoActual = leerEstado(scanner);
        String estadoActualString = ESTADOS[estadoActual];
        System.out.println("Estado actual: " + estadoActualString);

        int estadoPosterior = mostrarTransicionesYDeterminarPosterior(estadoActual);
        System.out.println("Estado más probable posterior: " + ESTADOS[estadoPosterior]);

        System.out.println("Ingrese el estado observado en el siguiente paso (0=Nublado, 1=Soleado, 2=Lluvioso):");
        int estadoFuturo = leerEstado(scanner);

        actualizarMatrizTransicion(estadoActual, estadoFuturo);

        imprimirMatriz();

        System.out.println("Ingrese la cantidad de pasos a futuro:");
        int pasos = scanner.nextInt();
        Double[] estadoFuturoProb = calcularEstadoFuturo(Mp, estadoActual, pasos);

        System.out.println("Probabilidades después de " + pasos + " paso(s):");
        int estadoMasProbable = 0;
        for (int i = 0; i < 3; i++) {
            System.out.printf("%s: %.4f\n", ESTADOS[i], estadoFuturoProb[i]);
            if (estadoFuturoProb[i] > estadoFuturoProb[estadoMasProbable]) {
                estadoMasProbable = i;
            }
        }

        System.out.println("Estado más probable después de " + pasos + " paso(s): " + ESTADOS[estadoMasProbable]);
        scanner.close();
    }

    public static void inicializarMatriz() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Mp[i][j] = 1.0 / 3;
            }
        }
    }

    public static int leerEstado(Scanner scanner) {
        int estado = scanner.nextInt();
        while (estado < 0 || estado > 2) {
            System.out.println("Valor incorrecto, vuelva a ingresar (0=Nublado, 1=Soleado, 2=Lluvioso):");
            estado = scanner.nextInt();
        }
        return estado;
    }

    public static int mostrarTransicionesYDeterminarPosterior(int estadoActual) {
        int estadoPosterior = 0;
        double maxProb = Mp[estadoActual][0];
        for (int i = 0; i < 3; i++) {
            System.out.printf("%.2f ", Mp[estadoActual][i]);
            if (Mp[estadoActual][i] > maxProb) {
                maxProb = Mp[estadoActual][i];
                estadoPosterior = i;
            }
        }
        System.out.println();
        return estadoPosterior;
    }

    public static void actualizarMatrizTransicion(int estadoActual, int estadoFuturo) {
        for (int i = 0; i < 3; i++) {
            int a = (i == estadoFuturo) ? 1 : 0;
            Mp[i][estadoActual] = ((Mp[i][estadoActual] * cantidadMediciones) + a) / (cantidadMediciones + 1);
        }
        cantidadMediciones++;
    }

    public static void imprimirMatriz() {
        System.out.println("Matriz de transición:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%.2f ", Mp[i][j]);
            }
            System.out.println();
        }
    }

    public static Double[] calcularEstadoFuturo(Double[][] Mp, int estadoActual, int pasos) {
        Double[] estado = new Double[3];
        for (int i = 0; i < 3; i++) {
            estado[i] = (i == estadoActual) ? 1.0 : 0.0;
        }

        for (int p = 0; p < pasos; p++) {
            Double[] nuevoEstado = new Double[3];
            for (int i = 0; i < 3; i++) {
                nuevoEstado[i] = 0.0;
                for (int j = 0; j < 3; j++) {
                    nuevoEstado[i] += estado[j] * Mp[j][i];
                }
            }
            estado = nuevoEstado;
        }
        return estado;
    }
    }

