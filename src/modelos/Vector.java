package modelos;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

public class Vector {

    private int cantidadElementos;
    private List<Double> fila;

    public Vector(int cantidadElementos){
        this.cantidadElementos = cantidadElementos;
        this.fila = new ArrayList<>(cantidadElementos);
    }

    protected List<Double> getVector(){
        return fila;
    }

    public Double getValor(int posicion){
        return fila.get(posicion);
    }

    public void setValor(int posicion, Double valor){
        fila.set(posicion,valor);
    }

    public int getCantidadValores(){
        return fila.size();
    }

    public void llenarConCeros(){
        for(int i=0; i < cantidadElementos; i++){
            fila.add(0.0);
        }
    }

    @Override
    public String toString() {
        return fila.toString();
    }
}
