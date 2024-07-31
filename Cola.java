package lineales.dinamicas;
/************* Autors ***********
- Angel Gabriel Avellaneda
*/
public class Cola {
  private Nodo frente;
  private Nodo fin;
  
    //Crea y devuelve una cola vacía.
    public Cola(){
        this.frente = null;
        this.fin = null;
    }
    
    /*Pone el elemento al final de la cola. Devuelve verdadero si el elemento se pudo agregar en la estructura y falso en caso contrario.*/
    public boolean poner (Object elem){
        boolean exito = true;
        Nodo nuevoNodo = new Nodo(elem, null);
         if(this.frente==null){
             this.frente = nuevoNodo;
             this.fin = nuevoNodo;
             exito = true;
         }else{
             this.fin.setEnlace(nuevoNodo);
             this.fin=nuevoNodo;
             exito = true;
         }
         return exito;
    }
    
    /*Saca el elemento que está en el frente de la cola. Devuelve verdadero si el elemento se pudo sacar y falso en caso contrario.*/
    public boolean sacar(){
        boolean exito = true;
        
        if (this.frente == null){
            //la cola está vacia, reporta error.
            exito = false;
        }else{
            //al menos hay un elemento:
            //quita el primer elemento y actualiza frente (y fin si queda vacía)
            this.frente = this.frente.getEnlace();
            if(this.frente == null){
                this.fin=null;
            }
        }
        return exito;
    }
    
    /*Devuelve el elemento que está en el frente. Precondicion: la cola no está vacía.*/
    public Object obtenerFrente(){
        Object elementoFrente = null;
        if(this.frente!=null){
            elementoFrente = this.frente.getElem();
        }
        return elementoFrente;
    }
    
    /*Devuelve verdadero si la cola no tiene elementos y falso en caso contrario.*/
    public boolean esVacia(){
        return this.frente == null;
    }
    
    /*Saca todos los elementos de la estructura.*/
    public void vaciar(){
        this.frente =null;
        this.fin = null;
    }
    
    /*Devuelve una copia exacta de los datos en la estructura original, y respetando el orden de los mismos, en otras estructuras del mismo tipo.*/
     @Override
    public Cola clone() {
        Cola nuevaCola = new Cola();
        Nodo nodoActual = this.frente;

        while (nodoActual != null) {
            nuevaCola.poner(nodoActual.getElem());
            nodoActual = nodoActual.getEnlace();
        }

        return nuevaCola;
    }
    
    /*Crea y devuelve una cadena de caracteres formada por todos los elementos de la cola para poder mostrarla por pantalla. Es recomendable
     utilizar este método únicamente en la etapa de prueba.*/
  @Override
     public String toString(){
        String cadena= "[";
        Nodo iterador=this.frente;
        while(iterador!=null){
            cadena+= iterador.getElem();
            iterador=iterador.getEnlace();
            if(iterador!=null){
                cadena+=", ";
            }
        }
        cadena+="]";
        return cadena;
    }
}