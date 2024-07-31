package conjuntistas;

import lineales.dinamicas.Lista;
import mudanzas.Solicitud;

/*
 * 
 * 
 * @author Angel.Avelaneda 
 * 
 */
public class Diccionario {
	NodoAVLDicc raiz;
	
	public Diccionario() {
		this.raiz = null;
	}
	public boolean insertar(Comparable clave, Object dato) {
		boolean exito = true;
		if(this.raiz == null) {
			this.raiz = new NodoAVLDicc(clave,dato,null,null);
		}else {
			exito = insertar(clave, dato, this.raiz, null, 'U');
		}
		return exito;
	}
	
	private boolean insertar(Comparable clave, Object dato, NodoAVLDicc nodo, NodoAVLDicc padre, char ubicacion) {
		boolean exito = true;
		int comparacion = clave.compareTo(nodo.getClave());
		if(comparacion > 0) {
			if(nodo.getDerecho()==null) {
				nodo.setDerecho(new NodoAVLDicc(clave, dato, null, null));
			}else {
				exito = insertar(clave,dato, nodo.getDerecho(), nodo, 'D');
			}
		}else if(comparacion <0) {
			if(nodo.getIzquierdo()==null) {
				nodo.setIzquierdo(new NodoAVLDicc(clave, dato, null, null));
			}else {
				exito= insertar(clave, dato, nodo.getIzquierdo(), nodo, 'I');
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
	
	public boolean eliminar(Comparable clave) {
		boolean exito = false;
		if(this.raiz != null) {
			exito = eliminar(clave, this.raiz, null, 'U');
		}
		return exito;
	}
	
	private boolean eliminar(Comparable clave, NodoAVLDicc nodo, NodoAVLDicc padre, char ubicacion) {
		boolean exito = false;
		NodoAVLDicc reemplazo = null;
		int comparacion = clave.compareTo(nodo.getClave());
		//Busca el nodo a eliminar recursivamente
		if(comparacion > 0 && nodo.getDerecho()!= null) {
			exito= eliminar(clave, nodo.getDerecho(), nodo, 'D');
		}else if(comparacion < 0 && nodo.getIzquierdo()!= null) {
			exito = eliminar (clave, nodo.getIzquierdo(), nodo, 'I');
		}else if (comparacion == 0){
			//Cuando lo encuentra
			exito = true; //Por defecto
			//segundo caso
			if(nodo.getIzquierdo()!= null && nodo.getDerecho()== null) {
				reemplazo = nodo.getIzquierdo();
			}else if(nodo.getIzquierdo()==null && nodo.getDerecho()!= null) {
				reemplazo = nodo.getDerecho();
			}else if(nodo.getIzquierdo() != null && nodo.getDerecho()!= null) {
				//tercer caso
				reemplazo = extraerMayorDelMenor(nodo.getIzquierdo(), nodo);
				reemplazo.setIzquierdo(nodo.getIzquierdo());
				reemplazo.setDerecho(nodo.getDerecho());
			}
			if(reemplazo != null) {
				reemplazo.recalcularAltura();
				nodo= reemplazo; //luego se balancea el nodo
			}
			//cambia los nodos, el eliminado por su reemplazo
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
	
	private NodoAVLDicc extraerMayorDelMenor(NodoAVLDicc nodo, NodoAVLDicc padre) {
		NodoAVLDicc mayor;
		if(nodo.getDerecho() == null) {
			mayor = nodo;
			padre.setIzquierdo(null);
		}else {
			mayor = extraerMayorDelMenorR(nodo.getDerecho(),nodo);
		}
		nodo.recalcularAltura();
		return mayor;
	}
	
	private NodoAVLDicc extraerMayorDelMenorR(NodoAVLDicc nodo, NodoAVLDicc padre) {
		NodoAVLDicc mayor;
		if(nodo.getDerecho()== null) {
			mayor = nodo;
			padre.setDerecho(null);
		}else {
			mayor = extraerMayorDelMenorR(nodo.getDerecho(), nodo);
		}
		nodo.recalcularAltura();
		return mayor;
	}
	
	private void balancear (NodoAVLDicc nodo, NodoAVLDicc padre, char ubicacion) {
		int balance;
		NodoAVLDicc tmp= null;
		balance = obtenerBalance(nodo);
		if(balance == -2) {
			if(obtenerBalance(nodo.getDerecho()) == 1) {
				nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
			}
			tmp = rotarIzquierda(nodo);
		}else if(balance == 2) {
			if(obtenerBalance(nodo.getIzquierdo())==-1) {
				nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
			}
			tmp = rotarDerecha(nodo);
		}
		if(tmp != null) {
			switch(ubicacion) {
			case 'U' -> this.raiz = tmp;
			case 'I' -> padre.setIzquierdo(tmp);
			case 'D' -> padre.setDerecho(tmp);
			}
		}
	}
	
	private int obtenerBalance(NodoAVLDicc nodo) {
		int balance = (nodo.getIzquierdo()==null? -1 : nodo.getIzquierdo().getAltura()+1) - (nodo.getDerecho()==null? -1 : nodo.getDerecho().getAltura()+1);
		return balance;
	}
	
	private NodoAVLDicc rotarIzquierda(NodoAVLDicc r) {
		NodoAVLDicc h = r.getDerecho();
		NodoAVLDicc tmp = h.getIzquierdo();
		h.setIzquierdo(r);
		r.setDerecho(tmp);
		r.recalcularAltura();
		h.recalcularAltura();
		return h;
	}
	
	private NodoAVLDicc rotarDerecha(NodoAVLDicc r) {
		NodoAVLDicc h = r.getIzquierdo();
		NodoAVLDicc tmp = h.getDerecho();
		h.setDerecho(r);
		r.setIzquierdo(tmp);
		r.recalcularAltura();
		h.recalcularAltura();
		return h;
	}
	
	public boolean pertenece (Comparable clave) {
		boolean pertenece = false;
		if(this.raiz!=null) {
			pertenece = perteneceR(clave, this.raiz);
		}
		return pertenece;
	}
	
	private boolean perteneceR (Comparable clave, NodoAVLDicc nodo) {
		boolean pertenece = false;
		int comparacion = clave.compareTo(nodo.getClave());
		if(comparacion==0) {
			pertenece= true;
		}else if(comparacion > 0 && nodo.getDerecho()!= null) {
			pertenece = perteneceR(clave, nodo.getDerecho());
		}else if(comparacion <0 && nodo.getIzquierdo()!= null) {
			pertenece = perteneceR(clave, nodo.getIzquierdo());
		}
		return pertenece;
	}
	
	public Object obtener(Comparable clave) {
		Object buscado = null;
		if(this.raiz != null) {
			buscado = obtenerR(clave, this.raiz);
		}
		return buscado;
	}
	
	private Object obtenerR(Comparable clave, NodoAVLDicc nodo) {
		Object buscado = null;
		int comparacion = clave.compareTo(nodo.getClave());
		if(comparacion == 0) {
			buscado = nodo.getDato();
		}else if(comparacion > 0 && nodo.getDerecho()!= null) {
			buscado = obtenerR(clave, nodo.getDerecho());
		}else if(comparacion < 0 && nodo.getIzquierdo()!= null) {
			buscado = obtenerR(clave, nodo.getIzquierdo());
		}
		return buscado;
	}
	
	//Retorna una lista con las claves ordenadas en Inorden
	public Lista listarClaves() {
		Lista lista = new Lista();
		if(!this.esVacio()) {
			listarClavesR(lista, this.raiz);
		}
		return lista;
	}
	
	private void listarClavesR(Lista lista, NodoAVLDicc nodo) {
		if(nodo.getIzquierdo()!= null) {
			listarClavesR(lista, nodo.getIzquierdo());
		}
		lista.insertar(nodo.getClave(), lista.longitud()+1);
		if(nodo.getDerecho()!=null) {
			listarClavesR(lista, nodo.getDerecho());
		}
	}
	
	public Object minimoElem() {
		NodoAVLDicc min = this.raiz;
		Object obj = null;
		if(min != null) {
			while(min.getIzquierdo()!=null) {
				min = min.getIzquierdo();
			}
			obj = min.getDato();
		}
		return obj;
	}
	
	public Object maximoElem() {
		NodoAVLDicc max = this.raiz;
		Object obj = null;
		if(max!= null) {
			while(max.getDerecho()!= null) {
				max = max.getDerecho();
			}
			obj = max.getDato();
		}
		return obj;
	}
	
	public Lista listarRango(Comparable min, Comparable max) {
		Lista lista = new Lista();
		if(this.raiz!=null) {
			listarRangoR(min, max, this.raiz, lista);
		}
		return lista;
	}
	
	private void listarRangoR(Comparable min, Comparable max, NodoAVLDicc nodo, Lista lista) {
		int comparacionIzq = min.compareTo(nodo.getClave());
		int comparacionDer = max.compareTo(nodo.getClave());
		if(nodo.getDerecho()!=null && comparacionDer >= 0) {
			lista.insertar(nodo.getClave(), 1);
		}
		if(nodo.getIzquierdo()!= null && comparacionIzq <=0) {
			listarRangoR(min,max, nodo.getIzquierdo(), lista);
		}
	}
	
	public boolean esVacio() {
		return this.raiz== null;
	}
	
	public void vaciar() {
		this.raiz = null;
	}
	
	public String toString() {
		return this.raiz == null? "" : toString(this.raiz);
	}
	
	private String toString(NodoAVLDicc nodo) {
		String left = nodo.getIzquierdo()==null? "-" : nodo.getIzquierdo().getClave().toString();
		String right = nodo.getDerecho()==null? "-" : nodo.getDerecho().getClave().toString();
		return nodo.getAltura()+" ["+nodo.getClave().toString()+"]: [ "+left+" | "+right+" ]"
				+(nodo.getIzquierdo()==null?"" : "\n"+ toString(nodo.getIzquierdo()))
				+ (nodo.getDerecho()==null? "" : "\n"+toString(nodo.getDerecho()));
	}
	
	public String toStringSimple() {
		return this.raiz==null? "" : toStringSimpleR(this.raiz);
	}
	
	public String toStringSimpleR(NodoAVLDicc nodo) {
		String string = "";
		if(nodo.getIzquierdo()!=null) {
			string+= toStringSimpleR(nodo.getIzquierdo());
		}
		string+=String.format("[%s]", nodo.getClave().toString());
		if(nodo.getDerecho()!=null) {
			string+= toStringSimpleR(nodo.getDerecho());
		}
		return string;
	}
	
	public Solicitud encontrarMenorSolicitud() {
		Solicitud solicitud = null;
		if(this.raiz != null) {
			solicitud = encontrarMenorSolicitud(this.raiz);
		}
		return solicitud;
	}
	
	private Solicitud encontrarMenorSolicitud(NodoAVLDicc nodo) {
		Solicitud solicitud = null;
		if(nodo != null) {
			Solicitud menorIzquierda = encontrarMenorSolicitud(nodo.getIzquierdo());
			Solicitud menorDerecha = encontrarMenorSolicitud(nodo.getDerecho());
			Solicitud solicitudActual = (Solicitud) nodo.getDato();
			Solicitud menorActual = solicitudActual;
			if(menorIzquierda != null && menorIzquierda.getCantMetrosCubicos() < menorActual.getCantMetrosCubicos()) {
				menorActual = menorIzquierda;
			}
			if(menorDerecha != null && menorDerecha.getCantMetrosCubicos() < menorActual.getCantMetrosCubicos()) {
				menorActual = menorDerecha;
			}
			solicitud = menorActual;
		}
		return solicitud;
	}
}
