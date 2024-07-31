package conjuntistas;
/*
 * 
 * 
 * @author Angel.Avelaneda 
 * 
 */
public class NodoAVLDicc {
	private Object dato;
	private Object clave;
	private int altura;
	private NodoAVLDicc izquierdo;
	private NodoAVLDicc derecho;
	
	public NodoAVLDicc(Object clave, Object dato, NodoAVLDicc izq, NodoAVLDicc der) {
		this.clave = clave;
		this.dato=dato;
		this.izquierdo = izq;
		this.derecho=der;
		this.altura = Math.max(this.izquierdo==null? 0 : this.izquierdo.getAltura() + 1, this.derecho == null? 0 : this.derecho.getAltura() + 1);
	}
	
	public Object getDato() {
		return this.dato;
	}
	
	public Object getClave() {
		return this.clave;
	}
	
	public NodoAVLDicc getIzquierdo() {
		return this.izquierdo;
	}
	
	public NodoAVLDicc getDerecho() {
		return this.derecho;
	}
	
	public int getAltura() {
		return this.altura;
	}
	
	public void setDato(Object dato) {
		this.dato=dato;
	}
	
	public void setIzquierdo(NodoAVLDicc izq) {
		this.izquierdo=izq;
	}
	
	public void setDerecho(NodoAVLDicc der) {
		this.derecho=der;
	}
	
	public void recalcularAltura() {
		this.altura= Math.max(this.izquierdo == null? 0 : this.izquierdo.getAltura() + 1, this.derecho == null? 0 : this.derecho.getAltura() + 1);
	}
}
