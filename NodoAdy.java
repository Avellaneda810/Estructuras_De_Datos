package grafo;
/*
 * 
 * 
 * @author Angel.Avelaneda 
 * 
 */
public class NodoAdy {
	
	private NodoVert vertice;
	private NodoAdy sigAdyacente;
	private double etiqueta;
	
	public NodoAdy (NodoVert vert, NodoAdy ady) {
		this.vertice=vert;
		this.sigAdyacente=ady;
		this.etiqueta=1;
	}
	public NodoAdy(NodoVert vert, NodoAdy sig, double etiq) {
		this.vertice = vert;
		this.sigAdyacente = sig;
		this.etiqueta = etiq;
	}
	
	public NodoVert getVertice() {
		return this.vertice;
	}
	
	public NodoAdy getSigAdyacente() {
		return this.sigAdyacente;
	}
	
	public double getEtiqueta() {
		return this.etiqueta;
	}
	
	public void setVertice(NodoVert vert) {
		this.vertice=vert;
	}
	
	public void setSigAdyacente(NodoAdy ady) {
		this.sigAdyacente = ady;
	}
	
	public void setEtiqueta(double etiq) {
		this.etiqueta=etiq;
	}
}
