package conjuntistas;
/*
 * 
 * 
 * @author Angel.Avelaneda 
 * 
 */
public class NodoHash {
	private String clave;
	private Object dato;
	private NodoHash enlace;
	
	public NodoHash (String llave, Object elem, NodoHash sigNodo) {
		this.clave = llave;
		this.dato = elem;
		this.enlace= sigNodo;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public Object getDato() {
		return this.dato;
	}
	
	public NodoHash getEnlace() {
		return this.enlace;
	}
	
	public void setDato(Object elem) {
		this.dato = elem;
	}
	
	public void setEnlace(NodoHash sigNodo) {
		this.enlace = sigNodo;
	}
}
