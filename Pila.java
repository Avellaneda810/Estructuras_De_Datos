package lineales.dinamicas;
/************* Autores **********
- Kevin Manuel Quintero Martinez, Legajo FAI-2771
- Benjamín Morales, Legajo FAI-3370
- Angel Gabriel Avellaneda, Legajo FAI-3342
*/
public class Pila {
    private Nodo tope;
    
    //Crea y devuelve la pila vacia.
    public Pila (){
        this.tope = null;
    }
        
    /*Pone el elemento nuevoElem en el tope de la pila. Devuelve verdadero si el elemento se pudo apilar y falso en caso contrario.*/
    public boolean apilar(Object nuevoElem){
         
         //crea un nuevo nodo delante de la antigua cabecera
         Nodo nuevo = new Nodo(nuevoElem, this.tope);
         
         //actualiza el tope para que apunte al nodo nuevo
         this.tope = nuevo;
         
         //nunca hay error de pila llena, entonces devuelve true
         return true;
    }
     /*Saca el elemento del tope de la pila. Devuelve verdadero si la pila no estaba vacia al momento de desapilar y falso en caso ccontrario.*/
    public boolean desapilar(){
        boolean exito = false;
        
        if (this.tope!=null){
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;
    }
    
    /*Devuelve el elemento en el tope de la pila. Precondicion; la pila no está vacia.*/
    public Object obtenerTope(){
    // Devuelve el valor que se encuentra en el tope de la pila    
        Object elemento;
        if(this.tope != null){ // verifica que la pila no este vacia
            elemento = this.tope.getElem(); // obtiene el valor guardado en el nodo tope
        }else{
            elemento = null; // si esta vacia el elemento es nulo
        }
        
        return elemento; 
    } 
    
    /*Devuelve verdadero si la pila no tiene elementos y falso en caso contrario.*/
    public boolean esVacia(){
        return (this.tope==null);
    }
    
    /*Saca todos los elementos de la pila.*/
    public void vaciar(){
        this.tope=null;
    }
    
    /*Devuelve una copia exacta de los datos en la estructura original, y repetando el orden de los mismos, en otra estructura del mismo tipo.*/
    public Pila clone() {
        Pila nuevaPila = new Pila();
        nuevaPila.tope = nodoRecursivo(this.tope);
        return nuevaPila;
    }
    
    public Nodo nodoRecursivo(Nodo nodo){
        if(nodo == null){
            return null;
        }
        
        Nodo nodoNuevo = new Nodo(nodo.getElem(), null);
        
        nodoNuevo.setEnlace(nodoRecursivo(nodo.getEnlace()));
        return nodoNuevo;
    }

    /*Devuelve una cadena de caracteres formada por todos los elementos de la pila para poder mostrarla por pantalla. Es recomendable utilizar
     este método únicamente en la etapa de prueba.*/
    @Override
    public String toString(){
        String s = "";
        if(this.tope==null){
            s = "Pila vacia";
        }else{
            //se ubica para recorrer la pila
            Nodo aux = this.tope;
            s = "[";
            
            while(aux != null){
                //agrega el texto del elem y avanza
                s += aux.getElem();
                aux = aux.getEnlace();
                if(aux != null){
                    s += " , ";
                }
            }
            s += "]";
        }
        return s;
    }
    
    
}
