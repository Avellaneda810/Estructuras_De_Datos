package conjuntistas;
/*
 * 
 * 
 * @author angel.avelaneda 
 * 
 */
import lineales.dinamicas.Lista;

public class ArbolAVL {
	 private NodoAVL raiz;
	 
	 public ArbolAVL() {
		 this.raiz = null;
	 }
	 
	 public boolean insertar(Comparable obj) {
		 boolean exito = true;
		 if(this.raiz == null) {
			 this.raiz = new NodoAVL(obj, null, null);
		 }else {
			 exito = insertar(obj, this.raiz, null, 'U');
		 }
		 return exito;
	 }
	 
	 private boolean insertar (Comparable obj, NodoAVL nodo, NodoAVL padre, char ubicacion) {
		 boolean exito = true;
		 int comparacion = obj.compareTo(nodo.getElem());
		 if(comparacion > 0) {
			 if(nodo.getDerecho() == null) {
				 nodo.setDerecho(new NodoAVL(obj, null, null));
			 }else {
				 exito = insertar(obj, nodo.getDerecho(), nodo, 'D');
			 }
		 }else if(comparacion < 0) {
			 if(nodo.getIzquierdo() == null) {
				 nodo.setIzquierdo(new NodoAVL(obj, null, null));
			 }else {
				 exito = insertar(obj, nodo.getIzquierdo(), nodo, 'I');
			 }
		 }else {
			 exito = false;
		 }
		 if(exito) {
			 balancear(nodo, padre, ubicacion);
			 nodo.recalcularAltura();
		 }
		 return exito;
	 }
	 
	 public boolean eliminar(Comparable obj) {
		 boolean exito = false;
		 if(this.raiz != null) {
			 exito = eliminar(obj, this.raiz, null, 'U');
		 }
		 return exito;
	 }
	 
	 private boolean eliminar(Comparable obj, NodoAVL nodo, NodoAVL padre, char ubicacion) {
		 boolean exito = false;
		 NodoAVL reemplazo = null;
		 int comparacion = obj.compareTo(nodo.getElem());
		 
		 //Busca el nodo a eliminar recursivamente
		 
		if(comparacion > 0 && nodo.getDerecho() != null) {
			exito = eliminar(obj, nodo.getDerecho(), nodo, 'D');
		}else if (comparacion < 0 && nodo.getIzquierdo() != null) {
			exito = eliminar(obj, nodo.getIzquierdo(), nodo, 'I');
		}else if(comparacion == 0) {//cuando lo encuentra
			exito = true;//primer caso, ambos hijos nulos
			//segundo caso
			if(nodo.getIzquierdo()!= null && nodo.getDerecho() == null) {
				reemplazo = nodo.getIzquierdo();
			}else if(nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
				reemplazo = nodo.getDerecho();
			//tercer caso
			}else if(nodo.getIzquierdo()!= null && nodo.getDerecho()!= null) {
				reemplazo = extraerMayorDelMenor(nodo.getIzquierdo(), nodo);
				reemplazo.setIzquierdo(nodo.getIzquierdo());
				reemplazo.setDerecho(nodo.getDerecho());
			}
			if(reemplazo != null) {
				reemplazo.recalcularAltura();
				nodo = reemplazo;//se balancea el nodo
			}
			//cambiar los nodos, eliminando el reemplazo
			switch(ubicacion) {
			case 'U' -> this.raiz = reemplazo;
			case 'D' -> padre.setDerecho(reemplazo);
			case 'I' -> padre.setIzquierdo(reemplazo);
			}
		}
		if(exito) {
			nodo.recalcularAltura();
			balancear(nodo, padre, ubicacion);
			nodo.recalcularAltura();
		}
		return exito;
	 }
	 
	 private NodoAVL extraerMayorDelMenor(NodoAVL nodo, NodoAVL padre) {
		 NodoAVL mayor;
		 if(nodo.getDerecho()==null) {
			 mayor = nodo;
			 padre.setIzquierdo(null);
		 }else {
			 mayor = extraerMayorDelMenorR(nodo.getDerecho(), nodo);
		 }
		 nodo.recalcularAltura();
		 return mayor;
	 }
	 
	 private NodoAVL extraerMayorDelMenorR( NodoAVL nodo, NodoAVL padre) {
		 NodoAVL mayor;
		 if(nodo.getDerecho()== null) {
			 mayor = nodo;
			 padre.setDerecho(null);
		 }else {
			 mayor = extraerMayorDelMenorR(nodo.getDerecho(), nodo);
		 }
		 nodo.recalcularAltura();
		 return mayor;
	 }
	 
	 private void balancear (NodoAVL nodo, NodoAVL padre, char ubicacion) {
		 int balance;
		 NodoAVL tmp = null;
		 balance = obtenerBalance(nodo);
		 if(balance == -2) {
			 if(obtenerBalance(nodo.getDerecho())==1) {
				 nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
			 }
			 tmp = rotarIzquierda(nodo);
		 }else if(balance == 2) {
			 if(obtenerBalance(nodo.getIzquierdo()) == -1) {
				 nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
			 }
			 tmp = rotarDerecha(nodo);
		 }
		 if (tmp != null) {
			 switch ( ubicacion) {
			 case 'U' -> this.raiz = tmp;
			 case 'I' -> padre.setIzquierdo(tmp);
			 case 'D' -> padre.setDerecho(tmp);
			 }
		 }
	 }
	 
	 private int obtenerBalance(NodoAVL nodo) {
		 int balance = (nodo.getIzquierdo() == null ? -1 : nodo.getIzquierdo().getAltura()) - (nodo.getDerecho() == null ? -1 : nodo.getDerecho().getAltura());
		 return balance;
	 }
	 
	 private NodoAVL rotarIzquierda(NodoAVL r) {
		 NodoAVL h = r.getDerecho();
		 NodoAVL tmp = h.getIzquierdo();
		 h.setIzquierdo(r);
		 r.setDerecho(tmp);
		 r.recalcularAltura();
		 h.recalcularAltura();
		 return h;
	 }
	 
	 private NodoAVL rotarDerecha(NodoAVL r) {
		 NodoAVL h= r.getIzquierdo();
		 NodoAVL tmp = h.getDerecho();
		 h.setDerecho(r);
		 r.setIzquierdo(tmp);
		 r.recalcularAltura();
		 h.recalcularAltura();
		 return h;
	 }
	 
	 public boolean pertenece (Comparable obj) {
		 boolean pertenece = false;
		 if(this.raiz != null) {
			 pertenece = perteneceR(obj, this.raiz);
		 }
		 return pertenece;
	 }
	 
	 private boolean perteneceR(Comparable obj, NodoAVL nodo) {
		 boolean pertenece = false;
		 int comparacion = obj.compareTo(nodo.getElem());
		 if(comparacion == 0) {
			 pertenece = true;
		 }else if(comparacion > 0 && nodo.getDerecho()!= null) {
			 pertenece = perteneceR(obj, nodo.getDerecho());
		 }else if(comparacion < 0 && nodo.getIzquierdo()!= null) {
			 pertenece = perteneceR(obj, nodo.getIzquierdo());
		 }
		 return pertenece;
	 }
	 
	 public Object obtener(Comparable obj) {
		 Object buscado = null;
		 if(this.raiz != null) {
			 buscado = obtenerR(obj, this.raiz);
		 }
		 return buscado;
	 }
	 
	 private Object obtenerR(Comparable obj, NodoAVL nodo) {
		 Object buscado = null;
		 int comparacion = obj.compareTo(nodo.getElem());
		 if (comparacion == 0) {
			 buscado = nodo.getElem();
		 }else if(comparacion > 0 && nodo.getDerecho() != null) {
			 buscado = obtenerR(obj, nodo.getDerecho());
		 }else if(comparacion < 0 && nodo.getIzquierdo() != null) {
			 buscado = obtenerR(obj, nodo.getIzquierdo());
		 }
		 return buscado;
	 }
	 
	 public Object minimoElem() {
		 NodoAVL min = this.raiz;
		 Object obj = null;
		 if(min != null) {
			 while(min.getIzquierdo()!= null) {
				 min = min.getIzquierdo();
			 }
			 obj = min.getElem();
		 }
		 return obj;
	 }
	 
	 public Object maximoElem() {
		 NodoAVL max = this.raiz;
		 Object obj = null;
		 if(max!=null) {
			 while(max.getDerecho()!= null) {
				 max = max.getDerecho();
			 }
			 obj = max.getElem();
		 }
		 return obj;
	 }
	 
	 public Lista listaPreOrden() {
		 Lista lista = new Lista();
		 if(!this.esVacio()) {
			 listarPreOrdenR(lista, this.raiz);
		 }
		 return lista;
	 }
	 
	 private void listarPreOrdenR(Lista lista, NodoAVL nodo) {
		 lista.insertar(nodo.getElem(), lista.longitud() +1);
		 if(nodo.getIzquierdo()!=null) {
			 listarPreOrdenR(lista, nodo.getIzquierdo());
		 }
		 if(nodo.getDerecho()!= null) {
			 listarPreOrdenR(lista, nodo.getDerecho());
		 }
	 }
	 
	 public Lista listaPosOrden() {
		 Lista lista = new Lista();
		 if(!this.esVacio()) {
			 listarPosOrdenR(lista, this.raiz);
		 }
		 return lista;
	 }
	
	 private void listarPosOrdenR(Lista lista, NodoAVL nodo) {
		 if(nodo.getIzquierdo()!= null) {
			 listarPosOrdenR(lista, nodo.getIzquierdo());
		 }
		 if(nodo.getDerecho()!= null) {
			 listarPosOrdenR(lista, nodo.getDerecho());
		 }
		 lista.insertar(nodo.getElem(), lista.longitud()+1);
	 }
	 
	 public Lista listarInorden() {
		 Lista lista = new Lista();
		 if(!this.esVacio()) {
			 listarInOrdenR(lista, this.raiz);
		 }
		 return lista;
	 }
	 
	 private void listarInOrdenR( Lista lista, NodoAVL nodo) {
		 if(nodo.getIzquierdo()!= null) {
			 listarInOrdenR(lista, nodo.getIzquierdo());
		 }
		 lista.insertar(nodo.getElem(), lista.longitud()+1);
		 if(nodo.getDerecho()!= null) {
			 listarInOrdenR(lista, nodo.getDerecho());
		 }
	 }
	 
	 public boolean esVacio() {
		 return this.raiz == null;
	 }
	 
	 public void vaciar() {
		 this.raiz = null;
	 }
	 
	 public String toString() {
		 return this.raiz == null ? "" : toString(this.raiz);
	 }
	 
	 private String toString (NodoAVL nodo) {
		 String left = nodo.getIzquierdo() == null ? "-" : nodo.getIzquierdo().getElem().toString();
		 String right = nodo.getDerecho() == null ? "-" : nodo.getDerecho().getElem().toString();
		 return nodo.getAltura()+" ["+nodo.getElem().toString()+"]: [ "+left+" | "+right+" ]"+(nodo.getIzquierdo() == null? "" : "\n"+toString(nodo.getIzquierdo()))
				 + (nodo.getDerecho()== null? "" : "\n"+ toString(nodo.getDerecho()));
	 }
	 
	 public String toStringSimple() {
		 return this.raiz==null? "": toStringSimpleR(this.raiz);
	 }
	 
	 private String toStringSimpleR(NodoAVL nodo) {
		 String string ="";
		 if(nodo.getIzquierdo()!=null) {
			 string += toStringSimpleR(nodo.getIzquierdo());
		 }
		 string += string.format("[%s]", nodo.getElem().toString());
		 if(nodo.getDerecho()!= null) {
			 string += toStringSimpleR(nodo.getDerecho());
		 }
		 return string;
	 } 
}