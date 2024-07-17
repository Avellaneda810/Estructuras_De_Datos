
package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

/**
 *
 * @author Angel
 */
public class ArbolBin {
    
    //atributos
    private NodoArbol raiz;
    
    /*Crea un árbol binario vacío*/
    public ArbolBin(){
        this.raiz = null;
    }
    
    /*Dado un elemento elem nuevo y un elemento elemPadre, inserta elemNuevo como hijo izquierdo o derecho de la primer aparicion de elemPadre,
    según lo indique el parámetro posición. Para que la operación termine con éxito debe existir un nodo en el árbol con elemento = elemPadre y
    ese nodo debe tener libre su hijo posicion. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.*/
    public boolean insertar(Object elemNuevo,Object elemPadre,char lugar){
        boolean exito = true;
        
        if(this.raiz==null){
            //si el arbol está vacío, pone el elemento nuevo en la raíz.
            this.raiz = new NodoArbol (elemNuevo, null, null);
        }else{
            //si el arbol no está vacío busca al padre.
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            
            //si padre existe y lugar no está ocupado lo pone, sino da error.
            if(nPadre!= null){
                if(lugar == 'I' && nPadre.getIzquierdo()==null){
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                }else if (lugar =='D' && nPadre.getDerecho()== null){
                    nPadre.setDerecho(new NodoArbol (elemNuevo, null, null));
                }else{
                    exito = false;
                }
            }else{
                exito= false;
            }
        }
        return exito;
    }
    
    //Método PRIVADO que busca un elemento y devuelve el nodo que lo contiene. Si no se encuentra buscado devuelve null.
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
        
        NodoArbol resultado = null;
        
        if (n!= null){
            if(n.getElemento().equals(buscado)){
                //si el buscado es n lo devuelve
                resultado = n;
            }else{
                //no es el buscado. busca primero en el HI.
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                //si no lo encontro en HI, busca en HD.
                if(resultado == null){
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
    
    /*Devuelve falso si hay al menos un elemento cargado en el árbol y verdaero en caso contrario.*/
    public boolean esVacio(){
        return this.raiz == null;
    }
    
    /*Devuelve la altura del árbol, es decir la longitud del camino más largo desde la raís hasta una hoja 
    (Nota: un árbol vacío tiene altura -1 y una hoja tiene altura 0)*/
    public int altura(){
        return alturaAux(this.raiz);
    }
    private int alturaAux(NodoArbol nodo){
        int altura, alturaDer, alturaIzq;
        
        if(nodo==null){
            altura = -1;
        }else{
            alturaDer = alturaAux(nodo.getDerecho());
            alturaIzq = alturaAux (nodo.getIzquierdo());
            altura= Math.max(alturaDer, alturaIzq);
        }
        return altura;
    }
    
    /*Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1.*/
    public int nivel(Object elemento){
         int nivel=-1;
        if(elemento.equals(this.raiz.getElemento())){
          nivel =0;  
        }else{
            if(this.raiz.getIzquierdo().getElemento().equals(elemento)){
                nivel = 1;
            }else if(this.raiz.getDerecho().getElemento().equals(elemento)){
                     nivel = 1;
                }
            }
        if(nivel==-1){
            nivel=nivelAux(this.raiz.getIzquierdo(), elemento, 2);
        }else{
            nivel=nivelAux(this.raiz.getDerecho(), elemento, 2);
        }
        return nivel;
    }
    private int nivelAux(NodoArbol nodo, Object elemento, int nivelActual){
        int nivel=-1; //se inicializa como no encontrado.
        if(elemento.equals(nodo.getElemento())){//verifica si es el elemento en el nodo actual.
            nivel=nivelActual;
        }else{
            if(nodo.getIzquierdo()!=null){//sino busca por hijo izquierdo.
            nivel = nivelAux(nodo.getIzquierdo(),elemento,nivelActual+1);
            }else if((nivel == -1) &&nodo.getDerecho()!=null){//sino busca por hijo derecho.
            nivel = nivelAux(nodo.getDerecho(),elemento,nivelActual+1);
            }
        }
        return nivel;
    }
    
    /*Dado un elemento devuelve el valor almacenado en su nodo padre( busca la primera aparición de el elemento).*/
    public Object padre(Object elemento){
        return padreAux(null,this.raiz,elemento);
    }
    private Object padreAux(NodoArbol padreActual, NodoArbol nodoActual, Object elem){
        Object padre = null;
        Object padreIzq, padreDer;
        if(nodoActual!=null){// sino es nulo
            if(nodoActual.getElemento().equals(elem)){
                padre = padreActual.getElemento();
            }else{
                padreIzq = padreAux(nodoActual, nodoActual.getIzquierdo(), elem);
                padreDer = padreAux(nodoActual, nodoActual.getDerecho(), elem);
                if(padreIzq != null){
                    padre = padreIzq;
                }else{
                    padre = padreDer;
                }
            }
        }
        return padre;
    }
    
    /*Devuelve una lista con los elementos del arbol binario en el reorrido en preordem.*/
    public Lista listarPreorden(){
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }
    
    //Método recursivo PRIVADO por que su parametro es tipo NodoArbol.
    private void listarPreordenAux(NodoArbol nodo, Lista lis){
        
        if (nodo != null){
            //visita el elemento en el nodo.
            lis.insertar(nodo.getElemento(), lis.longitud()+1); //(1)
            
            //recorre a sus hijos en preorden.
            listarPreordenAux(nodo.getIzquierdo(), lis);//(2)
            listarPreordenAux(nodo.getDerecho(), lis);//(3)
        }
    }
    
    /*Devuelve una lista con los elementos del árbol binario en el reorrido en inorden.*/
     public Lista listarInorden(){
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }
     //Método recursivo PRIVADO por que su parametro es tipo NodoArbol.
    private void listarInordenAux(NodoArbol nodo, Lista lis){
        if(nodo!= null){
            //lista el hijo izquierdo
           listarInordenAux(nodo.getIzquierdo(),lis);//(1)
           //visita la raiz
           lis.insertar(nodo.getElemento(), lis.longitud()+1);//(2)
           //lista el derecho.
           listarInordenAux(nodo.getDerecho(),lis);//(3)
        }
    }    
     
     /*Devuelve una lista con los elementos del árbol binario en el recorrido en posorden.*/
     public Lista listarPosorden(){
        Lista lis = new Lista ();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }
       //Método recursivo PRIVADO por que su parametro es tipo NodoArbol.
    private void listarPosordenAux(NodoArbol nodo, Lista lis){
        if(nodo!= null){
            //lista el hijo izquierdo
           listarPosordenAux(nodo.getIzquierdo(),lis);//(1)
           //lista el derecho.
           listarPosordenAux(nodo.getDerecho(),lis);//(2)
           //visita la raiz
           lis.insertar(nodo.getElemento(), lis.longitud()+1);//(3)
        }
    }  
    
    /*Devuelve una lista con los elementos del arbol binario en el recorrido por niveles.*/
  //1
    public Lista listarPorNiveles(){
        Lista niveles = new Lista();
        NodoArbol aux = this.raiz;
        int pos=1;
        if(aux!=null){
            niveles.insertar(aux.getElemento(),pos);
            listarNivelesAux(niveles, aux, pos);
        }
        return niveles;
    }
    private void listarNivelesAux(Lista niveles, NodoArbol aux, int pos){
        if(aux.getIzquierdo()!=null){
            pos += 1;
            niveles.insertar(aux.getIzquierdo().getElemento(), pos);
        }
        if(aux.getDerecho()!=null){
            pos += 1;
            niveles.insertar(aux.getDerecho().getElemento(), pos);
        }
        listarNivelesAux(niveles, aux.getIzquierdo(),pos);
        listarNivelesAux(niveles, aux.getDerecho(),pos);
    }
    
    /*Genera y devuelve un arbol binario que es equivalente(igual estructura y contenido de los nodos) que el arbol original.*/
    @Override
    public ArbolBin clone(){
       ArbolBin arbolClone = new ArbolBin();
       if(this.raiz != null){
           arbolClone.raiz=this.raiz;
           cloneSubArbol(this.raiz, arbolClone);
       }
       return arbolClone;
    }
    private ArbolBin cloneSubArbol(NodoArbol nodo, ArbolBin arbol){
        if(nodo.getIzquierdo()!=null){
            arbol.insertar(nodo.getIzquierdo(),nodo,'I');
            arbol.cloneSubArbol(nodo.getIzquierdo(), arbol);
        }
        if(nodo.getDerecho()!=null){
            arbol.insertar(nodo.getDerecho(),nodo,'D');
            arbol.cloneSubArbol(nodo.getDerecho(), arbol);
        }
        return arbol;
    }
    
    /*Quita todos los elementos de la estrutura. El manejo de memoria es similar al explicado anteriormente para estructuras lineales dinamicas.*/
    public void vaciar(){
        //si el arbol no esta vacio, se asigna una nueva raiz null con hijos I y D en null.
        if(this.raiz != null){
            this.raiz = null;
        }
    }
    
    /*Genera y devuelve una cadena de caracteres que indica cual es la raiz del arbol y quienes son los hijos de cada nodo.*/
    @Override
    public String toString(){
        String cadena="";
        cadena=toStringAux(this.raiz);
        return cadena;
    }
    private String toStringAux(NodoArbol m){
        String cadena="";
        //si m no es nulo.
        if(m!=null){
            //visita el elemento del nodo m.
            cadena+= m.getElemento();
            //visita el elemento del Hijo Izquierdo.
            cadena+=toStringAux(m.getIzquierdo());
            //visita el elemento del Hijo Derecho.
            cadena+=toStringAux(m.getDerecho());
        }
        return cadena;
    }
    
    //Devuelve una lista con todos los elementos almacenados en las hojas del árbol listadas de izquierda a derecha.
    public Lista frontera(){
        Lista lis = new Lista();
        int altura = this.altura();
        if(altura==0){
            lis.insertar(this.raiz, 1);
        }else{
            lis=fronteraAux(this.raiz.getIzquierdo(),altura);
            lis=fronteraAux(this.raiz.getDerecho(),altura);
        }
        return lis;
    }
    private Lista fronteraAux(NodoArbol nodo, int altura){
        Lista lis = new Lista();
        if(nodo.getIzquierdo()==null){
            lis.insertar(nodo.getElemento(), altura+1);
        }else{
            lis.insertar(nodo.getIzquierdo(),altura+1);
        }
        if (nodo.getDerecho()==null){
            lis.insertar(nodo.getElemento(), altura+1);
        }else{
            lis.insertar(nodo.getDerecho(),altura+1);
        }
        return lis;
    }
    
    //Devuelve en una lista todos los ancestros del elemento pasado por parámetro (si el elemento no está, devuelve la lista vacía).
    public Pila obtenerAncestros(Object elem){
        NodoArbol buscado = this.obtenerNodo(this.raiz, elem);
        int nivel;
        Pila ancestros = new Pila();
        if(buscado!=null){
            nivel=this.nivel(buscado.getElemento());
            if(nivel==1){  
                ancestros.apilar(this.padre(buscado.getElemento()));
            }else if (nivel>1){
                ancestros = obtenerAncestrosAux(nivel, buscado.getElemento());
            }
        }
        return ancestros;
    }
    private Pila obtenerAncestrosAux(int niv, Object busc){
        Pila aux = new Pila();
        while(niv>1){
            aux.apilar(this.padre(busc));
            busc=this.padre(busc);
            niv-=1;
        }
        return aux;
    }
    
    //Devuelve en una lista todos los descendientes del elemento pasado por parámetro (si el elemento no está, devuelve la lista vacía).
    public Cola obtenerDescendientes(Object elem){
        Cola descendientes = new Cola();
        NodoArbol izq=null, der= null;
        NodoArbol nodo= this.obtenerNodo(this.raiz,elem);
        if(nodo!=null){
            if(nodo.getIzquierdo() != null){
                izq = nodo.getIzquierdo();
            }
            if(nodo.getDerecho()!=null){
                der = nodo.getDerecho();
            }
            descendientes = obtenerDescendientesAux(izq, der);
        }
        return descendientes;
    }
    private Cola obtenerDescendientesAux(NodoArbol izquierdo, NodoArbol derecho ){
       Cola aux = new Cola();
       if(izquierdo!=null){
       aux.poner(izquierdo.getElemento());
       }
       if(derecho!=null){
            aux.poner(derecho.getElemento());
       }
       if(izquierdo.getIzquierdo()!=null|| izquierdo.getDerecho()!=null){
           obtenerDescendientesAux(izquierdo.getIzquierdo(), izquierdo.getDerecho());
       }
       if(derecho.getIzquierdo()!=null || derecho.getIzquierdo()!=null){
           obtenerDescendientesAux(derecho.getIzquierdo(),derecho.getDerecho());
       }
       return aux;
    }
}
