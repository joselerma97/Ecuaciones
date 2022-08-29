import modelos.Coeficientes;
import modelos.Vector;

public class Main {

    public static void main(String[] args){
        Coeficientes coeficientes =  new Coeficientes();
        coeficientes.llenarCoeficientes();

        System.out.println("\nSistema de Ecuaciones:");
        System.out.println(coeficientes);

        if(coeficientes.isValida()){
            coeficientes.normalizar();
            System.out.println("\nNormalizacion del sistema:");
            System.out.println(coeficientes);

            Vector soluciones = coeficientes.sustitucionInversa();
            System.out.println("\nSoluciones:");
            System.out.println( coeficientes.formatSoluciones(soluciones) );
        }
    }

}
