package grafo;
/*
 * 
 * 
 * @author angel.avelaneda 
 * 
 */
import java.util.HashMap;
import lineales.dinamicas.Cola;
import lineales.dinamicas.ColaPrioridad;
import lineales.dinamicas.Lista;
//Grafo no dirigido, etiquetado
public class Grafo {
	private NodoVert inicio;
	private int cantVertices;
	
	public Grafo() {
		this.inicio=null;
		this.cantVertices=0;
	}
	
	public void vaciar() {
		this.inicio=null;
		this.cantVertices=0;
	}
	
	public boolean insertarVertice(Object elem) {
		boolean exito = false;
		NodoVert aux = this.ubicarVertice(elem);
		if(aux!= null) {
			this.inicio = new NodoVert(elem, this.inicio);
			exito = true;
			this.cantVertices++;
		}
		return exito;
	}
	
	public boolean insertarArco(Object origen, Object destino, int etiqueta) {
		boolean exito = false;
		NodoVert nodoOrigen= null;
		NodoVert nodoDestino = null;
		NodoVert aux = this.inicio;
		//busco los vertices de ambos objetos
		while(aux!= null &&(nodoOrigen == null || nodoDestino == null)) {
			if (aux.getElem().equals(origen)) {
				nodoOrigen = aux;
			}
			if(aux.getElem().equals(destino)) {
				nodoDestino = aux;
			}
			aux = aux.getSigVertice();
		}
		if(nodoOrigen != null && nodoDestino != null) {
			nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(),etiqueta));
			nodoDestino.setPrimerAdy(new NodoAdy(nodoOrigen, nodoDestino.getPrimerAdy(),etiqueta));
			exito= true;
		}
		return exito;
	}
	
	public boolean eliminarArco(Object origen, Object destino) {
		boolean exito = false;
		NodoVert nodoOrigen = null;
		NodoVert nodoDestino = null;
		NodoVert aux = this.inicio;
		//busco los vertices de ambos objetos
		while(aux!=null&&(nodoOrigen == null || nodoDestino == null)) {
			if(aux.getElem().equals(origen)) {
				nodoOrigen = aux;
			}
			if(aux.getElem().equals(destino)) {
				nodoDestino = aux;
			}
			aux = aux.getSigVertice();
		}
		//si los encuentro
		if(nodoOrigen!=null && nodoDestino != null) {
			exito = eliminarAdyacente(nodoOrigen, destino) && eliminarAdyacente(nodoDestino, origen);
		}
		return exito;
	}
	
	//busca entre los nodos adyacentes de un vertice, el que apunte a destino y lo borra.
	private boolean eliminarAdyacente(NodoVert vert, Object destino) {
		boolean exito = false;
		NodoAdy adyAux = vert.getPrimerAdy();
		//caso de que el adyacente buscado sea el primero adyacente
		if(adyAux.getVertice().getElem().equals(destino)) {
			vert.setPrimerAdy(adyAux.getSigAdyacente());
			exito = true;
		}
		while(!exito && adyAux.getSigAdyacente()!=null) {
			if(adyAux.getSigAdyacente().getVertice().getElem().equals(destino)) {
				adyAux.setSigAdyacente(adyAux.getSigAdyacente().getSigAdyacente());
				exito= true;
			}
			adyAux = adyAux.getSigAdyacente();
		}
		return exito;
	}
	
	public boolean cambiarEtiqueta(Object origen, Object destino, int valor) {
		boolean exito=false;
		NodoVert nodoOrigen=null;
		NodoVert nodoDestino=null;
		NodoVert aux=this.inicio;
		//busco los vertices de ambos objetos
		while(aux!=null &&(nodoOrigen==null || nodoDestino ==null)) {
			if(aux.getElem().equals(origen)) {
				nodoOrigen = aux;
			}
			if(aux.getElem().equals(destino)) {
				nodoDestino = aux;
			}
			aux=aux.getSigVertice();
		}
		//si existen
		if(nodoOrigen != null && nodoDestino != null) {
			NodoAdy ady = nodoOrigen.getPrimerAdy();
			//busco el nodo adyacente y le cambio la distancia
			while(!exito && ady !=null) {
				if(ady.getVertice().getElem().equals(destino)) {
					ady.setEtiqueta(valor);
					exito = true;
				}
				ady = ady.getSigAdyacente();
			}
			exito = false;
			//Se cambia la etiqueta en la otra direccion
			ady = nodoDestino.getPrimerAdy();
			while(!exito && ady != null) {
				if(ady.getVertice().getElem().equals(origen)) {
					ady.setEtiqueta(valor);
					exito = true;
				}
				ady = ady.getSigAdyacente();
			}
		}
		return exito;
	}
	
	public boolean existeVertice(Object obj) {
		boolean existe = false;
		NodoVert aux = this.inicio;
		while(!existe && aux != null) {
			if(aux.getElem().equals(obj)) {
				existe = true;
			}else {
				aux=aux.getSigVertice();
			}
		}
		return existe;
	}
	
	/*Elimina el vertice y los arcos que apuntan al mismo, retorna true si se elimina, falso si el vertice no existia.
	 * Recorre todos los vertices buscando ambos arcos sin verificar si el vertice a eliminar existe previamente, 
	 * por lo que es menos eficiente al eliminar vertices que no existen, pero mas agil si existe*/
	public boolean eliminarVertice(Object obj) {
		boolean exito = false;
		NodoVert prev=null;
		NodoVert aux = this.inicio;
		while (aux != null) {
			if(!exito && aux.getElem().equals(obj)) {
				exito = true;
				this.cantVertices--;
				if(prev==null) {
					this.inicio.setSigVertice(aux.getSigVertice());
				}else {
					prev.setSigVertice(aux.getSigVertice());
				}
			}else {
				NodoAdy ady = aux.getPrimerAdy();
				NodoAdy adyPrev = null;
				while(ady!= null) {
					if(ady.getVertice().getElem().equals(obj)) {
						if(adyPrev == null) {
							aux.setPrimerAdy(ady.getSigAdyacente());
						}else {
							adyPrev.setSigAdyacente(ady.getSigAdyacente());
						}
					}
					adyPrev = ady;
					ady = ady.getSigAdyacente();
				}
			}
			prev = aux;
			aux = aux.getSigVertice();
		}
		return exito;
	}
	
	public boolean existeArco(Object origen, Object destino) {
		boolean existe = false;
		NodoVert nodoOrigen = ubicarVertice(origen);
		if(nodoOrigen != null) {
			NodoAdy aux= nodoOrigen.getPrimerAdy();
			while(!existe && aux != null) {
				if(aux.getVertice().getElem().equals(destino)) {
					existe = true;
				}else {
					aux = aux.getSigAdyacente();
				}
			}
		}
		return existe;
	}
	
	public boolean existeCamino(Object origen, Object destino) {
		boolean exito = false;
		NodoVert auxO= null;
		NodoVert auxD=null;
		NodoVert aux = this.inicio;
		while((auxO ==null || auxD == null) && aux!= null) {
			if(aux.getElem().equals(origen)) {
				auxO = aux;
			}
			if (aux.getElem().equals(destino)) {
				auxD = aux;
			}
			aux = aux.getSigVertice();
		}
		if(auxO != null && auxD != null) {
			Lista visitados = new Lista();
			exito = existeCaminoAux(auxO, destino, visitados);
		}
		return exito;
	}
	
	private boolean existeCaminoAux(NodoVert nodo, Object destino, Lista visitados) {
		boolean exito = false;
		if(nodo!= null) {
			if(nodo.getElem().equals(destino)) {
				exito = true;
			}else {
				visitados.insertar(nodo.getElem(), visitados.longitud()+1);
				NodoAdy ady = nodo.getPrimerAdy();
				while (!exito && ady != null) {
					if(visitados.localizar(ady.getVertice().getElem()) < 0) {
						exito = existeCaminoAux(ady.getVertice(), destino, visitados);
					}
					ady = ady.getSigAdyacente();
				}
			}
		}
		return exito;
	}
	
	private NodoVert ubicarVertice(Object buscado) {
		NodoVert aux = this.inicio;
		while(aux!= null && !aux.getElem().equals(buscado)) {
			aux = aux.getSigVertice();
		}
		return aux;
	}
	
	public Lista listarEnAnchura() {
		Lista visitados = new Lista();
		Lista lista = new Lista();
		NodoVert aux = this.inicio;
		while(aux!=null) {
			if(visitados.localizar(aux)==-1) {
				lista.insertar(aux, lista.longitud()+1);
				listarEnAnchuraDesde(aux, lista, visitados);
			}
			aux= aux.getSigVertice();
		}
		return lista;
	}
	
	private void listarEnAnchuraDesde(NodoVert vert, Lista lista, Lista visitados) {
		Cola cola = new Cola();
		visitados.insertar(vert, 1);
		cola.poner(vert);
		while(!cola.esVacia()) {
			NodoAdy ady = ((NodoVert) cola.obtenerFrente()).getPrimerAdy();
			cola.sacar();
			while(ady != null) {
				NodoVert adyVert = ady.getVertice();
				if(visitados.localizar(adyVert)== -1) {
					visitados.insertar(adyVert, 1);
					cola.poner(adyVert);
					lista.insertar(adyVert, lista.longitud()+1);
				}
				ady = ady.getSigAdyacente();
			}
		}
	}
	
	//Precondicion el camino debe existir
	public Lista caminoMenorVertices(Object origen, Object destino) {
		boolean encontrado = false;
		HashMap visitados = new HashMap();
		Lista camino = new Lista();
		Cola cola = new Cola();
		NodoVert vert = ubicarVertice(origen);
		cola.poner(vert);
		visitados.put(origen, origen);
		while(!cola.esVacia() && !encontrado) {
			vert = (NodoVert)cola.obtenerFrente();
			NodoAdy ady = vert.getPrimerAdy();
			cola.sacar();
			while(ady != null && !encontrado) {
				NodoVert vertSig = ady.getVertice();
				if(visitados.putIfAbsent(vertSig.getElem(), vert.getElem())==null) {
					if(vertSig.getElem().equals(destino)) {
						encontrado = true;
					}else {
						cola.poner(ady.getVertice());
					}
				}
				ady = ady.getSigAdyacente();
			}
		}
		Object object = destino;
		do {
			camino.insertar(object, 1);
			object = visitados.get(object);
		}
		while(!object.equals(origen));
		camino.insertar(object, 1);
		return camino;
	}
	
	//Algoritmo dijkstra
	//precondicion el camino debe existir
	public Lista caminoMenorDistancia(Object origen, Object destino) {
		boolean exito = false;
		ColaPrioridad cp = new ColaPrioridad(cantVertices*2);
		HashMap visitados = new HashMap();
		Lista camino = new Lista();
		NodoVert vertOrigen = ubicarVertice(origen);
		cp.insertar(new Dijkstra (0, vertOrigen, vertOrigen), 0);
		while(!cp.esVacio() && !exito ) {
			Dijkstra aux = (Dijkstra) cp.recuperarFrente();
			cp.eliminarFrente();
			if(!visitados.containsKey(aux.getNodo().getElem())) {
				double distanciaActual = aux.getDistancia();
				NodoVert vert = aux.getNodo();
				NodoAdy ady = vert.getPrimerAdy();
				visitados.put(vert.getElem(), aux.getNodoPrev().getElem());
				if(vert.getElem().equals(destino)) {
					exito = true;
				}else {
					while(ady!=null) {
						Dijkstra aux2 = new Dijkstra (distanciaActual + ady.getEtiqueta(), ady.getVertice(), vert);
						if(!visitados.containsKey(aux2.getNodo().getElem())) {
							cp.insertar(aux2,(int) aux2.getDistancia());
						}
						ady = ady.getSigAdyacente();
					}
				}
			}
		}
		Object obj = destino;
		do {
			camino.insertar(obj, 1);
			obj = visitados.get(obj);
		}while(!obj.equals(origen));
		camino.insertar(obj, 1);
		return camino;
	}
	
	public String toString() {
		String stringVertices ="";
		String stringArcos = "";
		if(this.inicio!= null) {
			stringVertices ="Vertices: ";
			stringArcos = "\nArcos: ";
			NodoVert aux = this.inicio;
			while(aux!=null) {
				String origen ="["+aux.getElem().toString()+"]";
				stringVertices = stringVertices.concat(origen);
				NodoAdy ady = aux.getPrimerAdy();
				while (ady != null) {
					String destino = "["+ady.getVertice().getElem().toString()+"]";
					stringArcos= stringArcos.concat("\t"+origen+"-"+ady.getEtiqueta()+"->"+destino+"\n");
					ady = ady.getSigAdyacente();
				}
				aux=aux.getSigVertice();
			}
		}
		return stringVertices + stringArcos;
	}
}
