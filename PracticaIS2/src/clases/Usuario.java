/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author santi
 */
public class Usuario {
    
    //vector para almacenar los objetos
    private ArrayList<Objeto> objetos;
    private int codigo;
    private String nombre;
    private String email;
    private float sumaPrestamo;
    private Objeto objeto;
    private String direccion;
    private String poblacion;
    private String provincia;
    
    /**
     * Constructor de la clase
     * 
     * @param codigo
     * @param nombre
     * @param email 
     */
    public Usuario(int codigo, String nombre, String email){
        this.codigo = codigo;
        this.email = email;
        this.nombre = nombre;
        objetos = new ArrayList<>();
        this.sumaPrestamo = 0;
        this.poblacion = "";
        this.provincia = "";
        this.direccion = "";
    }

    public Usuario() {}
    
    
    /**
     * metodo que nos devuelve un string con todos los objetos 
     * y sus respectivos prestamos
     * 
     * @param var
     * @return 
     */
    public String devolverObjetos(int var){
        String cadena = "";
        
        cadena += "\r\n\t\tOBJETOS DEL PROPIETARIO " + var + "\r\n\r\n";
        
        if(objetos.size() > 0){
            for(Objeto o : objetos){
                cadena += o.toString();
                cadena += o.devolverPrestamos(o.getCodigoObjeto());
            }
        }else{
            cadena = "\r\n\t\tEl propietario " + var + " no tiene objetos asociados\r\n\r\n";
        }
        
        return cadena;
    }
    
    /**
     * metodo que modifica el importe del objeto requerido
     * 
     * @param idObjeto
     * @param importeNuevo
     * @return 
     */
    public boolean modificarImporte(int idObjeto, float importeNuevo){
        boolean correcto = false;
        
        //primero modificamos el importe del objeto
        for(Objeto o : objetos){
            if(o.getCodigoObjeto() == idObjeto){
                o.setCoste(importeNuevo);
                correcto = true;
            }
        }
        return correcto;
    }
    
    /**
     * metodo que cambia la disponibilidad de un objeto
     * @param codigoObjeto 
     */
    public void cambiarDisponibilidad(int codigoObjeto){
        for(Objeto o : objetos){
            if(o.getCodigoObjeto() == codigoObjeto){
                o.setDisponible(false);
            }
        }
    }
    
    
    /**
     * metodo que nos comprueba si un objeto tiene prestamos o no
     * 
     * @return 
     */
    public boolean comprobarNumeroPrestamos(){
        boolean elementos = false;
        
        for(Objeto o : objetos){
            if(o.getTamPrestamos() > 0){
                elementos = true;
            }
        }
        return elementos;
    }
    
    
    /**
     * metodo que nos muestra todos los objetos
     * 
     * @param var 
     */
    public void mostrarObjetos(int var){
        
        System.out.println("\n\t\tOBJETOS DEL PROPIETARIO " + var + "\n") ;
        
        if(objetos.size() > 0){
                        
            for(int i = 0 ; i < objetos.size() ; i++){
                objeto = objetos.get(i);
                System.out.println(objeto.toString());
                objeto.mostrarPrestamos(objeto.getCodigoObjeto());
            }
        }else{
            System.out.println("\n\t\tEl propietario " + var + " no tiene objetos asociados\n");
        }                
    }
    
    
    /**
     * metodo que añade un prestamo de un objeto
     * 
     * @param idObjeto
     * @param p
     * @return 
     */
    public boolean agregarPrestamo(int idObjeto, Prestamo p){
        boolean agregado = false;
        for(int i = 0 ; i < objetos.size() ; i++){
            objeto = objetos.get(i);
            if(objeto.getCodigoObjeto() == idObjeto){
                agregado = objeto.add(p);
                agregado = true;
            }
        }
        return agregado;
    }
    
    
    /**
     * metodo que nos comprueba si el indice leido existe o no
     * 
     * @param idObjeto
     * @return 
     */
    public boolean comprobarIndice(int idObjeto){
        boolean valido = false;
        
        for(Objeto o : objetos){
            if(o.getCodigoObjeto() == idObjeto && o.isDisponible() == true){
                valido = true;
            }
        }
        return valido;
    }  
    
    
    /**
     * metodo con el cual agregamo un objeto
     * 
     * @param o 
     */
    public void agregarObjeto(Objeto o){
        objetos.add(o);
    }
    
    /**
     * metodo que nos muestra todos los objetos disponibles 
     */
    public void mostrarObjetosDisponibles(){
        String cadenaId;        
        for(int i = 0 ; i < objetos.size() ; i++){
            if(objetos.get(i).isDisponible()){
                cadenaId = String.format("%03d", objetos.get(i).getCodigoObjeto());
                System.out.println("\t" + cadenaId + "\t\t" + objetos.get(i).getDescripcion() + "\t\t" + objetos.get(i).getCoste());
            }  
        }
    }
    
    /**
     * metodo que añade un objeto
     * 
     * @param o
     * @return 
     */
    public boolean add(Objeto o){
        return(objetos.add(o));
    }
    
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    /**
     * metodo toString de la clase
     * @return 
     */
    @Override
    public String toString(){
        String cadena = "\r\n\tPROPIETARIO " + this.codigo + "\r\n\tNombre del propietario : " + this.nombre + " \r\n\tEmail : " + this.email + "\r\n";
        return cadena; 
    }

    /**
     * @return the sumaPrestamo
     */
    public float getSumaPrestamo() {
        return sumaPrestamo;
    }

    /**
     * @param sumaPrestamo the sumaPrestamo to set
     */
    public void setSumaPrestamo(float sumaPrestamo) {
        this.sumaPrestamo += sumaPrestamo;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the poblacion
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    
    
}
