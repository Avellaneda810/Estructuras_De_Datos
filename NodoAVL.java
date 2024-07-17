package conjuntistas;
/*
 * 
 * 
 * @author angel.avelaneda 
 * 
 */
public class NodoAVL {
	private Object elem;
	private int altura;
	private NodoAVL izquierdo;
	private NodoAVL derecho;
	
	public NodoAVL (Object elem, NodoAVL izq, NodoAVL der) {
		this.elem = elem;
		this.izquierdo = izq;
		this.derecho = der;
		this.altura = Math.max(this.izquierdo == null? 0 : this.izquierdo.getAltura() + 1, this.derecho == null ? 0 : this.derecho.getAltura() + 1);
	}
	
	public void recalcularAltura() {
		this.altura = Math.max(this.izquierdo == null? 0 : this.izquierdo.getAltura() + 1, this.derecho == null ? 0 : this.derecho.getAltura() + 1);
	}
	
	public void setDerecho(NodoAVL der) {
		this.derecho = der;
	}
	
	public void setElem(Object elem) {
		this.elem = elem;
	}
	
	public void setIzquierdo( NodoAVL izq) {
		this.izquierdo = izq;
	}
	
	public Object getElem() {
		return this.elem;
	}
	
	public NodoAVL getIzquierdo() {
		return this.izquierdo;
	}
	
	public NodoAVL getDerecho() {
		return this.derecho;
	}
	
	public int getAltura() {
		return this.altura;
	}
	
}
