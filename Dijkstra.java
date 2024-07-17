package grafo;
/*
 * 
 * 
 * @author angel.avelaneda 
 * 
 */
public class Dijkstra {
	//Es un algoritmo para la determinacion del camino mas corto, dado un vertice origen, hacia el resto de los vertices en un grafo.
	 private double distancia;
	 private NodoVert nodoPrev;
	 private NodoVert nodo;
	 
	 public Dijkstra ( double dist, NodoVert nodo, NodoVert nodoPrev) {
		 this.distancia=dist;
		 this.nodo=nodo;
		 this.nodoPrev=nodoPrev;
	 }
	 
	 public double getDistancia() {
		 return this.distancia;
	 }
	 
	 public NodoVert getNodo() {
		 return this.nodo;
	 }
	 
	 public NodoVert getNodoPrev() {
		 return this.nodoPrev;
	 }
	 
	 public void setDistancia(int dist) {
		 this.distancia=dist;
	 }
	 
	 public void setPrev(NodoVert prev) {
		 this.nodoPrev = prev;
	 }
	 
	 public boolean equals(Object otro) {
		 return this.nodo.equals(((Dijkstra)otro).getNodo());
	 }
	 
	 public int compareTo(Object otro) {
		 return Double.compare(this.distancia, (((Dijkstra)otro).getDistancia()));
	 }
	 
	 public String toString() {
		 return String.format("[%s - %d - %s]", this.nodo, this.distancia, this.nodoPrev);
	 }
}
