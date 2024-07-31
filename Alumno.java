/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructura_de_datos;

/**
 *
 * @author Angel.Avellaneda
 */
public class Alumno {
    //Atributos
    private String legajo;
    private String nombre;
    private String apellido;
    private String tipoDni;
    private int numDni;
    private String calleDomicilio;
    private int numDomicilio;
    private String ciudadDomicilio;
    private int telefono;
    private String usuario;
    private String claveSiu;
    
    //Constructores
    public Alumno(String leg){
        this.legajo=leg;
    }
    
    public Alumno(String leg, String nom, String apel, String tDni, int nDni, String calleDom, 
                                int numDom, String ciudadDom, int tel, String usua, String clSiu){
         this.legajo=leg;
         this.nombre=nom;
         this.apellido=apel;
         this.tipoDni=tDni;
         this.numDni=nDni;
         this.calleDomicilio=calleDom;
         this.ciudadDomicilio=ciudadDom;
         this.numDomicilio=numDom;
         this.telefono=tel;
         this.usuario=usua;
         this.claveSiu=clSiu;
    }
    
    //Metodos GET
     public String getLegajo(){
         return this.legajo;
     }
     
     public String getNombre(){
         return this.nombre;
     }
    
     public String getApellido(){
         return this.apellido;
     }
     
     public String getTipoDni(){
         return this.tipoDni;
     }
     
     public int getNumDni(){
         return this.numDni;
     }
     
     public String getCalleDom(){
         return this.calleDomicilio;
     }
     
     public int getNumDom(){
         return this.numDomicilio;
     }
     
     public String getCiudadDom(){
         return this.ciudadDomicilio;
     }
     
     public int getTelefono(){
         return this.telefono;
     }
     
     public String getUsuario(){
         return this.usuario;
     }
     
     //Metodos SET
     public void setNombre(String nom){
         this.nombre=nom;
     }
     
     public void setApellido(String apel){
         this.apellido=apel;
     }
     
     public void setNumDom(int numDom){
         this.numDomicilio=numDom;
     }
     
     public void setCalleDom(String calleDom){
         this.calleDomicilio=calleDom;
     }
     
     public void setCiudadDom(String ciudadDom){
         this.ciudadDomicilio=ciudadDom;
     }
     
     public void setTelefono(int tel){
         this.telefono=tel;
     }
     
     public void setClaveSiu(String clave){
         this.claveSiu=clave;
     }
     
     
}
