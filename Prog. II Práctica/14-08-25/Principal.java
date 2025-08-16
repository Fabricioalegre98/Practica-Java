public class Principal {
    public static void main(String[]args){
        //creo la variable animal sin definir un valor.
        Programa animal = new Programa();
        //a la variable nombre le asigno un nuevo objeto
        animal.nombre = "firulais";
        //a la variable edad le asigno un nuevo objeto
        animal.edad = 3;
        //a la variable especie le asigno un nuevo valor
        animal.especie = "perro";
        animal.habla();
        animal.identificarse();

        
    }
    
}