public class repasoMarkov {
    private double [][] matriz;
    public repasoMarkov () {
        matriz = new double [3][3];
    }
    public double [][] getMatriz() {
        return this.matriz;
    }
    public void setMatriz (double [][] NuevaMatriz){
        if (validar_Matriz(NuevaMatriz)) {
            this.matriz = NuevaMatriz;
        }
    }
    // validamos si la matriz es de 3x3
    private boolean validar_Matriz (double [][] valMatriz){
        if (valMatriz == null || valMatriz.length != 3) {
            return false;
        }
        for (int i = 0; i < valMatriz.length; i++) {
            if (valMatriz[i].length != 3) {
                return false;
            }
        }
        return true;
    }

}