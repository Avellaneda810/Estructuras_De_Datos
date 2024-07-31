package jerarquicas.dinamicas;
/************* Autors ***********
- Angel Gabriel Avellaneda
*/
public class NodoGen {
    
    //atributos.
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    //constructor.
    public NodoGen(Object elemento, NodoGen hijoIzq, NodoGen hermanoDer){
        this.elem=elemento;
        this.hijoIzquierdo=hijoIzq;
        this.hermanoDerecho=hermanoDer;
    }
    
    //visualizador de elemento.
    public Object getElem(){
        return this.elem;
    }
    
    //modificador de elemento.
    public void setElem(Object elemento){
        this.elem=elemento;
    }
    
    //visualizador de hijo izquierdo.
    public NodoGen getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    
    //modificador de hijo izquierdo.
    public void setHijoIzquierdo(NodoGen hijoIzq){
        this.hijoIzquierdo = hijoIzq;
    }
    
    //visualizador de hermano derecho.
    public NodoGen getHermanoDerecho(){
        return this.hermanoDerecho;
    }
    
    //modificador de hermano derecho.
    public void setHermanoDerecho(NodoGen hermanoDer){
        this.hermanoDerecho = hermanoDer;
    }
}
