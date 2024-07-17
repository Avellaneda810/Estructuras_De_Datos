/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

/**
 *
 * @author Angel
 */
class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    //Constructor vacio.
    public NodoArbol(Object e, NodoArbol izq, NodoArbol der){
        
    }
    
    //Visualizadores
    public Object getElemento(){
        return elem;
    }
    
    public NodoArbol getIzquierdo(){
        return izquierdo;
    }
    
    public NodoArbol getDerecho(){
        return derecho;
    }
    
    //Modificadores
    
    public void setElemento(Object e){
        this.elem = e;
    }
    
    public void setIzquierdo(NodoArbol nodoIzq){
        this.izquierdo=nodoIzq;
    }
    
    public void setDerecho(NodoArbol nodoDer){
        this.derecho=nodoDer;
    }
}
