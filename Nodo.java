package lineales.dinamicas;
/************* Autores ***********
- Angel Gabriel Avellaneda
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
