package modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coeficientes {

    private int cantidadEcuaciones;
    private List< Vector > matriz;
    private Scanner lectura;

    public Coeficientes(){
        this.lectura = new Scanner(System.in);

        System.out.println("Ingrese la cantidad de Ecuaciones:");
        this.cantidadEcuaciones = lectura.nextInt();
        this.crearMatriz();
    }

    private void crearMatriz(){
        matriz = new ArrayList<>(cantidadEcuaciones);
        for(int i=0; i < cantidadEcuaciones; i++){
            this.matriz.add( new Vector(cantidadEcuaciones + 1) );
        }
    }

    public void llenarCoeficientes(){
        for(int i=0 ; i < cantidadEcuaciones ; i++ ){
            List<Double> fila = matriz.get(i).getVector();
            for(int j=0; j < (cantidadEcuaciones + 1); j++){
                System.out.println("Ingrese elemento ("+(i+1)+","+(j+1)+"):");
                fila.add(lectura.nextDouble());
            }
        }
    }

    private Double getValor(int fila, int columna){
        return matriz.get(fila).getVector().get(columna);
    }

    private void setValor(int fila, int columna, Double valor){
        matriz.get(fila).getVector().set(columna,valor);
    }

    public boolean isValida(){
        return isDiagonalCero(0);
    }
    private boolean isDiagonalCero(int posicion){
        if(getValor(posicion,posicion) != 0){
            if((posicion + 1) < cantidadEcuaciones){
                return isDiagonalCero(posicion + 1);
            }else{
                return true;
            }
        }else{
            System.out.println("Sistema invalido");
            return false;
        }
    }

    public void normalizar(){
        Vector coeficientesNormales = new Vector(cantidadEcuaciones + 1);
        coeficientesNormales.llenarConCeros();

        for(int filaPivote = 0; filaPivote < (cantidadEcuaciones -1) ; filaPivote++){
            normalizar(coeficientesNormales,filaPivote);
            for(int fila = filaPivote + 1 ; fila < cantidadEcuaciones; fila++){
                Double pivote = getValor(fila,filaPivote);
                for(int columna = 0; columna < coeficientesNormales.getCantidadValores(); columna++){
                    setValor(fila,columna,
                            getValor(fila,columna)
                                    - pivote*coeficientesNormales.getValor(columna));
                }
            }
        }
    }

    public Vector sustitucionInversa(){
        Vector soluciones = new Vector(cantidadEcuaciones);
        soluciones.llenarConCeros();

        int ultimaPosicion = cantidadEcuaciones -1;
        soluciones.setValor(ultimaPosicion,
                getValor(ultimaPosicion, ultimaPosicion + 1)
                        / getValor(ultimaPosicion,ultimaPosicion)
                );

        for(int fila = ultimaPosicion -1 ; fila >= 0 ; fila--){
            soluciones.setValor(fila, getValor(fila,cantidadEcuaciones) );
            for(int columna = 0; columna < cantidadEcuaciones; columna++){
                if(getValor(fila,columna) != 0.0 && fila != columna){
                    soluciones.setValor(fila,
                            soluciones.getValor(fila) -
                                    getValor(fila,columna)*soluciones.getValor(columna)
                            );
                }
            }
            soluciones.setValor(fila,
                    soluciones.getValor(fila)/getValor(fila,fila));
        }
        return soluciones;
    }

    private void normalizar(Vector coeficientesNormales, int fila){
        for(int columna=0; columna < coeficientesNormales.getCantidadValores(); columna++){
            coeficientesNormales.setValor(columna,
                    getValor(fila,columna) / getValor(fila,fila)
                    );
        }
    }

    @Override
    public String toString() {
        StringBuilder representacion = new StringBuilder();
        for(Vector fila: matriz){
            for(int i=0; i < fila.getCantidadValores(); i++){
                if((i+1) == fila.getCantidadValores())representacion.append("|");
                representacion.append(String.format("%.2f",fila.getValor(i)));
                representacion.append("\t");
            }
            representacion.append("\n");
        }
        return representacion.toString();
    }

    public String formatSoluciones(Vector soluciones){
        StringBuilder format = new StringBuilder();
        for(int i=0; i < soluciones.getCantidadValores(); i++){
            format.append("Solucion "+(i+1)+": ");
            format.append(String.format("%.2f",soluciones.getValor(i)));
            format.append("\n");
        }
        return format.toString();
    }

}
