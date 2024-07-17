package grafo;
/*
 * 
 * 
 * @author angel.avelaneda 
 * 
 */
public class NodoVert {
	private Object elem;
	private NodoVert sigVertice;
	private NodoAdy primerAdy;
	
	public NodoVert(Object elem, NodoVert sig, NodoAdy primer) {
		this.elem=elem;
		this.sigVertice = sig;
		this.primerAdy = primer;
	}
	
	public NodoVert(Object elem, NodoVert sig) {
		this.elem=elem;
		this.sigVertice=sig;
		this.primerAdy=null;
	}
	public Object getElem() {
		return this.elem;
	}
	
	public NodoVert getSigVertice() {
		return this.sigVertice;
	}
	
	public NodoAdy getPrimerAdy() {
		return this.primerAdy;
	}
	
	public void setElem(Object elem) {
		this.elem = elem;
	}
	
	public void setSigVertice(NodoVert sig) {
		this.sigVertice = sig;
	}
	
	public void setPrimerAdy(NodoAdy ady) {
		this.primerAdy= ady;
	}
	
	public String toString() {
		return elem.toString();
	}
	
	public boolean equals(Object otro) {
		return this.elem.equals(((NodoVert)otro).getElem());
	}
}
