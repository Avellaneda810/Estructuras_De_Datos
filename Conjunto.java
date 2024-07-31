package conjuntistas;
/*
 * 
 * 
 * @author Angel.Avelaneda 
 * 
 */
import lineales.dinamicas.Lista;

public class Conjunto {
	private final ArbolAVL arbol;
	public Conjunto() {
		this.arbol = new ArbolAVL();
	}
	
	public boolean agregar(Comparable obj) {
		return arbol.insertar(obj);
	}
	
	public boolean quitar(Comparable obj) {
		return arbol.eliminar(obj);
	}
	
	public boolean pertenece(Comparable obj) {
		return arbol.pertenece(obj);
	}
	
	public boolean esVacia() {
		return arbol.esVacio();
	}
	
	public Lista listar() {
		return arbol.listarInorden();
	}
	
	public String toString() {
		return arbol.toStringSimple();
	}
}
