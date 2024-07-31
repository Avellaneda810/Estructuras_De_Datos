package conjuntistas;

import mudanzas.Cliente;
import mudanzas.Pedidos;
import mudanzas.Solicitud;

/*
 * 
 * 
 * @author Angel.Avelaneda 
 * 
 */
public class HashAbierto {
	private int TAMANIO;
	private NodoHash tabla[];
	private int cant = 0;
	
	public HashAbierto(int tam) {
		this.TAMANIO = tam;
		this.tabla= new NodoHash[tam];
	}
	
	public boolean pertenece (String clave) {
		int pos = Math.abs(clave.hashCode()) % TAMANIO;
		NodoHash actual = this.tabla[pos];
		boolean exito = false;
		while(actual != null && !exito) {
			if(actual.getClave().equals(clave)) {
				exito = true;
			}
			actual = actual.getEnlace();
		}
		return exito;
	}

	public boolean insertar(String clave, Object dato) {
		int pos = Math.abs(clave.hashCode()) % TAMANIO;
		NodoHash aux = this.tabla[pos];
		boolean encontrado = false;
		while(aux != null && !encontrado) {
			encontrado = aux.getClave().equals(clave);
			aux = aux.getEnlace();
		}
		if(!encontrado) {
			this.tabla[pos] = new NodoHash(clave, dato, this.tabla[pos]);
			this.cant++;
			encontrado = true;
		}
		return encontrado;
	}
	
	public boolean eliminar (String clave) {
		int pos = Math.abs(clave.hashCode()) % TAMANIO;
		NodoHash actual = this.tabla[pos];
		NodoHash anterior = null;
		boolean exito = false;
		while(actual != null && !exito) {
			if(actual.getClave().equals(clave)) {
				if (anterior == null) {//El nodo a eliminar es el primero de la lista enlazada.
					this.tabla[pos]= actual.getEnlace();
				}else {	//El nodo a eliminar se encuentra en medio o final de la lista enlazada.
					anterior.setEnlace(actual.getEnlace());
				}
				this.cant--;
				exito = true;
			}
			anterior = actual;
			actual = actual.getEnlace(); 
		}
		return exito;
	}
	
	public boolean modificar(String clave, Object nuevoDato) {
		int pos = Math.abs(clave.hashCode()) % TAMANIO;
		NodoHash actual = this.tabla[pos];
		boolean exito = false;
		while(actual != null && !exito) {
			if(actual.getClave().equals(clave)) {
				actual.setDato(nuevoDato);
				exito = true;
			}
			actual = actual.getEnlace();
		}
		return exito;
	}
	
	public NodoHash obtener (String clave) {
		int pos = Math.abs(clave.hashCode()) % TAMANIO;
		NodoHash actual = this.tabla[pos];
		boolean encontrado = false;
		while(actual != null && !encontrado) {
			if(actual.getClave().equals(clave)) {
				encontrado = true;
			}else {
				actual = actual.getEnlace();
			}
		}
		if(!encontrado) {
			actual = null;
		}
		return actual;
	}
	
	public String toString() {
		String resultado = "";
		for(int i = 0; i< TAMANIO; i++) {
			NodoHash actual = this.tabla[i];
			resultado += "Posicion "+i+ ": ";
			while(actual != null) {
				String clave = actual.getClave();
				Cliente cliente = (Cliente) actual.getDato();
				String tipoDoc = cliente.getTipoDocumento();
				int nroDoc = cliente.getNroDocumento();
				resultado += '"'+clave+" "+'"';
				actual = actual.getEnlace();
			}
			resultado += "\n";
		}
		return resultado;
	}
	
	public String toStringPedidos() {
		String resultado="";
		for(int i = 0; i < TAMANIO; i++) {
			NodoHash actual = this.tabla[i];
			resultado += "Posicion "+i+": ";
			while(actual != null) {
				Pedidos pedido = (Pedidos) actual.getDato();
				int origen = pedido.getCiudadOrigen();
				int destino = pedido.getCiudadDestino();
				Comparable clave = pedido.getClave();
				resultado+= '"'+origen+"-"+destino+": "+pedido.toString();
				actual = actual.getEnlace();
			}
			resultado+="\n";
		}
		return resultado;
	}
	
	public Diccionario mostrarPedidos(int origen, int destino) {
		Diccionario arbol = null;
		String llave = "" +origen +destino;
		NodoHash nodo = obtener(llave);
		if(nodo!= null) {
			Pedidos pedidos = (Pedidos) nodo.getDato();
			arbol = pedidos.getSolicitudes();
		}
		return arbol;
	}
	
	public boolean insertarPedido(int origen, int destino, Solicitud solicitud) {
		boolean exito = false;
		String llave =""+origen+destino;
		NodoHash nodo = obtener(llave);
		if(nodo != null) {
			Pedidos pedidos = (Pedidos) nodo.getDato();
			exito = pedidos.agregarSolicitud(solicitud);
		}
		return exito;
	}
	
	public boolean eliminarPedido(int origen, int destino, String claveCliente) {
		boolean exito= false;
		String llave =""+origen+destino;
		NodoHash nodo = obtener(llave);
		if(nodo != null) {
			Pedidos pedidos = (Pedidos) nodo.getDato();
			exito = pedidos.eliminarSolicitud(claveCliente);
			Diccionario solicitudes = pedidos.getSolicitudes();
			if(solicitudes.esVacio()) {
				eliminar(llave);
			}
		}
		return exito;
	}
	
	public Solicitud obtenerPedido(int origen, int destino, String claveCliente) {
		Diccionario pedidos = mostrarPedidos(origen, destino);
		NodoAVLDicc nodo = (NodoAVLDicc) pedidos.obtener(claveCliente);
		Solicitud solicitud = (Solicitud) nodo.getDato();
		return solicitud;
	}
	
	public boolean borrarTodosLosPedidosDeCiudad(int ciudad) {
		boolean exito = false;
		for(int i =0; i < TAMANIO; i++) {
			NodoHash nodo = this.tabla[i];
			while(nodo != null) {
				Pedidos pedidos = (Pedidos) nodo.getDato();
				int origen = pedidos.getCiudadOrigen();
				int destino = pedidos.getCiudadDestino();
				if(origen == ciudad || destino == ciudad) {
					String llave = ""+origen+destino;
					eliminar(llave);
					exito = true;
				}
				nodo = nodo.getEnlace();
			}
		}
		return exito;
	}
}
