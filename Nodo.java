package lineales.dinamicas;
/************* Autores ***********
- Kevin Manuel Quintero Martinez, Legajo FAI-2771
- Benjamín Morales, Legajo FAI-3370
- Angel Gabriel Avellaneda, Legajo FAI-3242
*/
public class Nodo {
    
    private Object elem;
    private Nodo enlace;
    
        //Constructor
        public Nodo(Object elem, Nodo enlace){
            this.elem = elem;
            this.enlace = enlace;
        }
        
        //Modificadoras
        public void setElem(Object elem){
            this.elem = elem;
        }
        
        public void setEnlace(Nodo enlace){
            this.enlace = enlace;
        }
        
        //Observadoras
        public Object getElem(){
            return elem;
        }
        
        public Nodo getEnlace(){
            return enlace;
        }
}
