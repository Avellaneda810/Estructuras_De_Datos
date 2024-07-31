package lineales.dinamicas;
/************* Autores ***********
- Angel Gabriel Avellaneda
*/
public class Lista {
    
    private Nodo cabecera;
    
    public Lista(){
        this.cabecera = null;
    }
    
    public boolean insertar(Object nuevoElem, int pos){
        // inserta el elemento nuevo en la posicion pos
        // detecta y reporta error en la posicion invalida
        boolean exito = true;
        
        if(pos < 1 || pos > this.longitud() + 1){
            exito = false;
        }else{
            if(pos == 1){
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            }else{ // avanza hasta el elemento en posicion pos - 1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1){
                    aux = aux.getEnlace();
                    i++;
                }
                //crea el nuevo nodo y lo enlaza
                Nodo nuevoNodo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevoNodo);
            }
        }
        
        //nunca hay error de lista llena
        return exito;
    }
    
    public boolean eliminar(int pos) {
    boolean exito;
    
    if (this.cabecera == null) {
        exito = false;
    } else {
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            if (pos == 1) {               
                this.cabecera = this.cabecera.getEnlace();            
                exito = true;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                exito = true;
            }
        }
    }
    
    return exito;           
}

    
   public Object recuperar(int pos) {
    Object elemento = null;

    if (!esVacia() && pos >= 1 && pos <= longitud()) {
        Nodo nodoActual = cabecera;
        int contador = 1;

        while (contador < pos) {
            nodoActual = nodoActual.getEnlace();
            contador++;
        }

        elemento = nodoActual.getElem();
    }

    return elemento;
}


    
    public int localizar(Object elem){
        int posicionElemento = -1;
        boolean encontrado = false;
        Nodo aux = this.cabecera; 
        int i = 1;
        while(i <= this.longitud() && !encontrado){
            if(aux.getElem() == elem){
                posicionElemento = i;
                encontrado = true;
            }else{
                aux = aux.getEnlace();
            }                       
            i++;
        }
        return posicionElemento;
  
    }
    
    public int longitud(){
        int cantidadElementos = 0;
        Nodo aux = cabecera;
        while(aux != null){
            aux = aux.getEnlace();
            cantidadElementos++;
        }
        
        return cantidadElementos;  
    }
    
    public boolean esVacia(){
        return this.cabecera == null;
    }
    
    public void vaciar(){
        cabecera = null;
    }
    
   public Lista clone() {
    Lista listaClonada = new Lista();

    if (cabecera == null) {
        return listaClonada; // La lista original está vacía, devuelve la lista clonada vacía
    }

    // Clonar el primer nodo de la lista original
    Nodo nodoActual = cabecera;
    Nodo nuevoNodo = new Nodo(nodoActual.getElem(), null);
    listaClonada.cabecera = nuevoNodo;

    // Clonar los nodos restantes de la lista original
    while (nodoActual.getEnlace() != null) {
        nodoActual = nodoActual.getEnlace();
        nuevoNodo.setEnlace(new Nodo(nodoActual.getElem(), null));
        nuevoNodo = nuevoNodo.getEnlace();
    }

    return listaClonada;
}

    
    public String toString(){
        String cadena = "[";
        Nodo aux = cabecera;
        while(aux != null){
            cadena += aux.getElem();
            if(aux.getEnlace() != null){
                cadena += ",";
            }
            aux = aux.getEnlace();
        }
        cadena +="]";
        
        return cadena;
    }
    
    public Lista obtenerMultiplos(int num) {
    Lista listaMultiplos = new Lista();
    int contador = 1;
    Nodo actual = this.cabecera;
    while (actual != null) {
        if (contador % num == 0) {
            Nodo nuevo = new Nodo(actual.getElem(), null);
            if (listaMultiplos.esVacia()) {
                listaMultiplos.cabecera = nuevo;
            } else {
                Nodo ultimo = listaMultiplos.cabecera;
                while (ultimo.getEnlace() != null) {
                    ultimo = ultimo.getEnlace();
                }
                ultimo.setEnlace(nuevo);
            }
        }
        contador++;
        actual = actual.getEnlace();
    }
        return listaMultiplos;
    }
    
    
    public void eliminarApariciones(Object elem){
        Nodo aux = this.cabecera;
        Nodo anterior = null;
        
        while(aux != null){
            if(aux.getElem().equals(elem)){
                if(anterior == null){
                    this.cabecera = aux.getEnlace();
                }else{                   
                    anterior.setEnlace(aux.getEnlace());
                }
                aux = aux.getEnlace();
            }else{
                anterior = aux;
                aux = aux.getEnlace();
            }
        }
    }
    
}
