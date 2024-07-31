package jerarquicas.dinamicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
/************* Autors ***********
- Angel Gabriel Avellaneda
*/
public class ArbolGen {
    
    //atributos.
    private NodoGen raiz;
    
    //contructor.
    public ArbolGen(){
        this.raiz=null;
    }
    
    /*Dado un elemento elemNuevo y un elemento elemPadre, agrega elemNuevo como hijo de la primer aparición de
    elemPadre. Para que la operación termine con éxio debe existir un nodo en el árbol con elemento = elemPadre.
    No se establece ninguna preferencia respecto a la posición del hijo respecto a sus posibles hermanos. Está
    operación devuelve verdadero cuando se pudo agregar elemNuevo a la estructura y falso en caso contrario.*/ 
    
    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = false;

        if (raiz == null) {
            raiz = new NodoGen(elemNuevo, null, null); 
            exito = true;
        } else {
            NodoGen nodoPadre = obtenerNodo(raiz, elemPadre);
            if (nodoPadre == null) {
                exito = false; // No se encontró el elemento padre
            } else{
                if (nodoPadre.getHijoIzquierdo() == null) {
                    NodoGen nodo = new NodoGen(elemNuevo, null, null);         
                    nodoPadre.setHijoIzquierdo(nodo);
                    exito = true;
                } else {
                    NodoGen aux = nodoPadre.getHijoIzquierdo();

                    while (aux.getHermanoDerecho() != null) {
                    aux = aux.getHermanoDerecho();
                    }

                NodoGen nodo = new NodoGen(elemNuevo, null, null);
                aux.setHermanoDerecho(nodo);
                exito = true;
                }
            } 
        }
    
        return exito;
    }
    
    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
    // Método PRIVADO que busca un elemento y devuelve el nodo que lo contiene. Si no lo encuentra, devuelve null.
    NodoGen resultado = null;

    if (nodo != null) {
        if (nodo.getElem().equals(buscado)) {
            resultado = nodo;
        } else {
            resultado = obtenerNodo(nodo.getHijoIzquierdo(), buscado);
            if (resultado == null) {
                resultado = obtenerNodo(nodo.getHermanoDerecho(), buscado);
            }
        }
    }

    return resultado;
}

    
    /*Devuelve verdadero si el elemento pasado por parámetro está en el árbol, y falso en caso contrario.*/
    
    public boolean pertenece(Object elemento) {
        return perteneceAux(elemento, raiz);
    }

    private boolean perteneceAux(Object elem, NodoGen nodo) {
        boolean encontrado = false;

        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                encontrado = true;
            } else if (nodo.getHijoIzquierdo() != null) {
                encontrado = perteneceAux(elem, nodo.getHijoIzquierdo());

                if (!encontrado) {
                    NodoGen aux = nodo.getHijoIzquierdo().getHermanoDerecho();
                    while (aux != null && !encontrado) {
                        encontrado = perteneceAux(elem, aux);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }

        return encontrado;
    }       
    
    /*Si el elemento se encuentra en el árbol, devuelve una lista con el elemento, devuelve una lista con el camino
    desde la raíz hasta dicho elemento (es decir, con los ancestros del elemento). Si el elemento no está en el 
    árbol devuelve la lista vacia.*/
    public Lista ancestros (Object elemento){
        Lista camino = new Lista();
        ancestrosAux(elemento, raiz, camino);
        return camino;
    
    }
    
    private boolean ancestrosAux(Object elemento, NodoGen nodo, Lista lis) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                encontrado = true;
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    encontrado = ancestrosAux(elemento, nodo.getHijoIzquierdo(), lis);
                    if (encontrado) {
                        lis.insertar(nodo.getElem(), 1);
                    }
                }
                if (!encontrado) {
                    NodoGen aux = nodo.getHijoIzquierdo();
                    while (aux != null && !encontrado) {
                        encontrado = ancestrosAux(elemento, aux, lis);
                        aux = aux.getHermanoDerecho();
                        if (encontrado) {
                            lis.insertar(nodo.getElem(), 1);
                        }
                    }
                }
            }
        }
        return encontrado;
    }


    /*Devuelve falso si hay almenos un elemento cargado en el árbol y verdadero en caso contrario.*/
    public boolean esVacio(){ 
        return raiz == null;
    }
      
    /*Devuelve la altura del árbol, es decir la longitud del camino más largo desde la raíz hasta una hoja*/
    public int altura() {
        return alturaAux(raiz);
    }

    private int alturaAux(NodoGen nodo) {
        if (nodo == null) {
            return 0;
        }

        int alturaMaxima = -1;

        // Verificar la altura de los hijos del nodo
        NodoGen hijo = nodo.getHijoIzquierdo();
        while (hijo != null) {
            int alturaHijo = alturaAux(hijo);
            if (alturaHijo > alturaMaxima) {
                alturaMaxima = alturaHijo;
            }
            hijo = hijo.getHermanoDerecho();
        }

        // Sumar 1 a la altura máxima para incluir al nodo actual
        return alturaMaxima + 1;
    }    
    
    /*Devuelve el nivel de un elemento en el árbol. Si el elemento no existe  en el árbol devuelve -1*/
    public int nivel(Object elemento){    
        return obtenerNivelAux(elemento, raiz, 0);
    }
    
    private int obtenerNivelAux(Object elemento, NodoGen nodo, int nivel) {
        int resultado = -1;

        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                resultado = nivel;
            } else {
                resultado = obtenerNivelAux(elemento, nodo.getHijoIzquierdo(), nivel + 1);
                if (resultado == -1) {
                    resultado = obtenerNivelAux(elemento, nodo.getHermanoDerecho(), nivel);
                }
            }
        }
        return resultado;
    }

      
    /*Dado un elemento devuelve el valor almacenado en su nodo padre (busca la primera aparción de elemento).*/
    
    public Object padre(Object elemento) {
        return padreAux(elemento, raiz, null);
    }

    private Object padreAux(Object elemento, NodoGen nodo, NodoGen padre) {
    Object valorPadre = null;

    if (nodo != null) {
        if (nodo.getElem().equals(elemento)) {
            if(padre != null){
                valorPadre = padre.getElem();
            }else{
                valorPadre = null;
            }
        } else {
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null && valorPadre == null) {
                valorPadre = padreAux(elemento, aux, nodo);
                aux = aux.getHermanoDerecho();
            }
        }
    }

    return valorPadre;
}

    /*Devuelve una lista con los elementos del árbol en el recorrido en preorden*/
    public Lista listarPreorden(){ 
        Lista salida = new Lista();
        listarPreordenAux(this.raiz,salida);
        return salida;
    }
    
        private void listarPreordenAux(NodoGen n, Lista lis){
            if(n!=null){
                //visita del nodo n.
                lis.insertar(n.getElem(), lis.longitud()+1);
                
                //llamado recursivo con primer hijo n.
                if(n.getHijoIzquierdo()!= null){
                    listarPreordenAux(n.getHijoIzquierdo(), lis);
                }
                
                //llamados recursivos con los otros hijos dde n.
                if(n.getHijoIzquierdo()!=null){
                    NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                    while(hijo!= null){
                        listarPreordenAux(hijo, lis);
                        hijo= hijo.getHermanoDerecho();
                    }
                }
            }
        }
    
    /*Devuelve una lista con los elementos del árbol en el recorrido en inorden*/
        public Lista listarInorden(){
            Lista salida = new Lista();
            listarInordenAux(this.raiz, salida);
            return salida;
        }
            
        private void listarInordenAux(NodoGen n, Lista lis){
            if(n!=null){

                //llamado recursivo con primer hijo de n.
                if(n.getHijoIzquierdo()!= null){
                    listarInordenAux(n.getHijoIzquierdo(), lis);
                }

                //visita del nodo n.
                lis.insertar(n.getElem(), lis.longitud()+1);

                //llamados recursivos con los otros hijos de n.
                if(n.getHijoIzquierdo()!= null){
                    NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                    while(hijo!=null){
                        listarInordenAux(hijo, lis);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
    
    /*Devuelve una lista con los elementos del árbol en el recorrido en posorden*/
    public Lista listarPosorden(){ 
        Lista salida = new Lista();
            listarPosordenAux(this.raiz, salida);
            return salida;
    }
    private void listarPosordenAux(NodoGen n, Lista lis){
        if(n!=null){

            //llamado recursivo con primer hijo de n.
            if(n.getHijoIzquierdo()!= null){
                listarInordenAux(n.getHijoIzquierdo(), lis);

                 //llamados recursivos con los otros hijos de n.
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while(hijo!=null){
                    listarInordenAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }

            //visita del nodo n.
            lis.insertar(n.getElem(), lis.longitud()+1);
        }
     }
    
    /*Devuelve una lista con los elementos del árbol en el recorrido por niveles*/     
    public Lista listarPorNiveles() {
        Lista listaPorNiveles = new Lista();
        Cola cola = new Cola();

        if (raiz != null) {
            cola.poner(raiz);

            while (!cola.esVacia()) {
                NodoGen nodoActual = (NodoGen) cola.obtenerFrente();
                cola.sacar();

                if (nodoActual != null) {
                    listaPorNiveles.insertar(nodoActual.getElem(), listaPorNiveles.longitud() + 1);

                    if (nodoActual.getHijoIzquierdo() != null) {
                        cola.poner(nodoActual.getHijoIzquierdo());

                        NodoGen hermanoDerecho = nodoActual.getHijoIzquierdo().getHermanoDerecho();

                        while (hermanoDerecho != null) {
                            cola.poner(hermanoDerecho);
                            hermanoDerecho = hermanoDerecho.getHermanoDerecho();
                        }
                    }
                }
            }
        }

        return listaPorNiveles;
    }

    /*Genera y devuelve un árbol genérico que es equivalente (igual estructura y contenido de los nodos) que el
    árbol original.*/
    public ArbolGen clone(){
        ArbolGen nuevoArbol = new ArbolGen();
        
        if(this.raiz != null){
            nuevoArbol.raiz = cloneAux(this.raiz);
        }
        
        return nuevoArbol;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevoNodo = new NodoGen(nodo.getElem(), null, null);
        //System.out.println("se creo: " + nodo.getElem());

        if (nodo.getHijoIzquierdo() != null) {
            nuevoNodo.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo())); 
        }

        if (nodo.getHijoIzquierdo() != null && nodo.getHijoIzquierdo().getHermanoDerecho() != null) {
            NodoGen aux = nodo.getHijoIzquierdo().getHermanoDerecho();
            NodoGen nuevoAux = nuevoNodo.getHijoIzquierdo();

            while (aux != null) {
                nuevoAux.setHermanoDerecho(cloneAux(aux));
                aux = aux.getHermanoDerecho();
                nuevoAux = nuevoAux.getHermanoDerecho();
            }
        }   

        return nuevoNodo;
    }
    
    /*Quita todos los elemento de la estructura*/
    public void vaciar(){
        this.raiz = null;
    }
    
    /*Genera y devuelve una cadena de caracteres que indica cual es la raíz del árbol y quienes son ls hijos de cada nodo.*/
    @Override
    public String toString() { 
        return toStringAux(this.raiz);
    }
    
    private String toStringAux(NodoGen n){
        String s = "";
        if(n != null){

            //visita el nodo n
            s += n.getElem().toString() + "->";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null){
                s += hijo.getElem().toString() +", ";
                hijo = hijo.getHermanoDerecho();
            }

            //comienza el recorrido de los hijos de n llamando recursivamente para que cada hijo agregue su subcadena general.
            hijo = n.getHijoIzquierdo();
            while (hijo != null){
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    //Grado arbol (el nodo con mas hijos en el arbol).
    public int grado(){       
        int gradoIzquierda = -1;
        int hermanosDerecho = -1;
        int gradoMaximoHermanos = -1;
        int gradoMayor = -1;
        if(raiz != null){
            if(raiz.getHijoIzquierdo() != null){
                gradoIzquierda = gradoSubarbol(raiz.getHijoIzquierdo().getElem());
            }
            if(raiz.getHijoIzquierdo().getHermanoDerecho() != null){
                NodoGen aux = raiz.getHijoIzquierdo().getHermanoDerecho();
                while(aux != null){
                    hermanosDerecho = gradoSubarbol(aux.getElem());
                    if(hermanosDerecho > gradoMaximoHermanos){
                        gradoMaximoHermanos = hermanosDerecho;
                    }
                    aux = aux.getHermanoDerecho();
                }              
            } 
            if(gradoIzquierda > gradoMaximoHermanos){
                gradoMayor = gradoIzquierda;
            }else{
                gradoMayor = gradoMaximoHermanos;
            }
        }          
        return gradoMayor;

    }
        
      
        
    //Grado del nodo (cantidad de hijos del nodo).
        public int gradoSubarbol (Object elem){
            NodoGen nodoElem = obtenerNodo(this.raiz, elem);
            int grado = -1;
            if(nodoElem != null){
                grado+=1;
                if(nodoElem.getHijoIzquierdo()!=null){
                     grado+=1;
                     NodoGen hijoHermano = nodoElem.getHijoIzquierdo().getHermanoDerecho();
                     while(hijoHermano != null){
                         grado+=1;
                         hijoHermano = hijoHermano.getHermanoDerecho();
                     }
                }
            }
            return grado;
        }
        
        
    /*equals que recibe un árbol genérico y debe comparar si éste es igual al árbol this. La signatura del método debe
    ser public boolean equals(ArbolGen unArbol). El método debe ser eficiente y no recorrer de más*/
        
    public boolean equals(ArbolGen otroArbol) {
        return equalsAux(this.raiz, otroArbol.raiz);
    }

    private boolean equalsAux(NodoGen nodo1, NodoGen nodo2) {
        boolean sonIguales = true;

        if (nodo1 != null && nodo2 != null) {
            if (!nodo1.getElem().equals(nodo2.getElem())) {
                sonIguales = false;
            } else {
                NodoGen hijo1 = nodo1.getHijoIzquierdo();
                NodoGen hijo2 = nodo2.getHijoIzquierdo();

                while (hijo1 != null && hijo2 != null && sonIguales) {
                    sonIguales = equalsAux(hijo1, hijo2);
                    hijo1 = hijo1.getHermanoDerecho();
                    hijo2 = hijo2.getHermanoDerecho();
                }

                if ((hijo1 != null && hijo2 == null) || (hijo1 == null && hijo2 != null)) {
                    sonIguales = false;
                }
            }
        } else if (nodo1 != null || nodo2 != null) {
            sonIguales = false;
        }

        return sonIguales;
}
    
    /*sonFrontera que recibe una lista de elementos almacenada en una estructura del tipo TDA Lista visto en la materia,
    y debe verificar en forma eficiente si la lista contiene los elementos de la frontera del árbol, sin importar el orden
    en que aparezcan los elementos en la lista. La signatura del método debe ser public boolean sonFrontera(Lista unaLista)
    ADVERTENCIA: el árbol genérico puede tener elementos repetidos. Tomar como precondición del método sonFrontera que la
    lista no tenga elementos repetidos.*/
    
    public boolean sonFrontera(Lista unaLista) {
        if(unaLista.elementosRepetidos()){
            return false;
        }else{
            return sonFronteraAux(this.raiz, unaLista);
        }
        
    }

    private boolean sonFronteraAux(NodoGen nodo, Lista unaLista) {
        boolean esFrontera = true;

        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null && nodo.getHermanoDerecho() == null) {
                // Si el nodo es una hoja, verificar si su elemento está en la lista
                esFrontera = unaLista.localizar(nodo.getElem()) != -1;
            } else {
                // Verificar recursivamente los hijos izquierdos y los hermanos derechos
                esFrontera = sonFronteraAux(nodo.getHijoIzquierdo(), unaLista) && sonFronteraAux(nodo.getHermanoDerecho(), unaLista);
            }
        }

        return esFrontera;
    }


}
