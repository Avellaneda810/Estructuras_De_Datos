package lineales.dinamicas;
/*
 * 
 * 
 * @author angel.avelaneda 
 * 
 */
public class CeldaCP {
	private final int prioridad;
	private final int ordenLlegada;
	private final Object elemento;
	
	public CeldaCP(Object elem, int prioridad, int ordenLlegada) {
		this.elemento= elem;
		this.prioridad= prioridad;
		this.ordenLlegada= ordenLlegada;
	}
	
	public int compareTo(Object obj) {
		int comparacion = Integer.compare(this.prioridad, ((CeldaCP)obj).prioridad);
		if(comparacion == 0) {
			comparacion = Integer.compare(this.ordenLlegada, ((CeldaCP)obj).ordenLlegada);
		}
		return comparacion;
	}
	
	public Object getElem() {
		return this.elemento;
	}
}
