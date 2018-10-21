/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author santi
 */
public class Objeto {
    
    private int codigoUsuario;
    private int codigoObjeto;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private float coste;
    private boolean disponible;
    private ArrayList<Prestamo> prestamos; 
    private float total;
    private Prestamo prestamo = null;
    
    
    /**
     * Constructor de la clase
     * 
     * @param codigoUsuario
     * @param codigoObjeto
     * @param des
     * @param inicio
     * @param fin
     * @param precio 
     */
    public Objeto(int codigoUsuario, int codigoObjeto, String des, Date inicio, Date fin, float precio){
        this.codigoUsuario = codigoUsuario;
        this.codigoObjeto = codigoObjeto;
        this.descripcion = des;
        this.fechaInicio = inicio;
        this.fechaFin = fin;
        this.coste = precio;
        disponible = true;
        prestamos = new ArrayList<Prestamo>();
    }

    
    /**
     * Constructor por defecto
     */
    public Objeto() {
        this.codigoUsuario = 0;
        this.codigoUsuario =0;
        this.descripcion = "";
        this.coste =0;
        this.disponible = true;
        this.fechaInicio = null;
        this.fechaFin = null;
        this.total = 0;
    }

    /**
     * metodo que nos devuelve un string con todos los prestamos
     * 
     * @param var
     * @return 
     */
    public String devolverPrestamos(int var){
        String cadena = "";
        
        cadena += "\r\n\t\t\tPRESTAMOS DEL OBJETO " + var + "\r\n\r\n";        
        if(prestamos.size() > 0){
            for(Prestamo p : prestamos){
                cadena += p.toString();
            }
        }else{
            cadena = "\r\n\t\t\tEL objeto " + var + " no tiene prestamos asociados\r\n\r\n";
        }
        
        return cadena;
    }
    /**
     * metodo que nos muestra todos los objetos los prestamos
     * 
     * @param var 
     */
    public void mostrarPrestamos(int var){        
        System.out.println("\n\t\t\tPRESTAMOS DEL OBJETO " + var + "\n");
        
        if(prestamos.size() > 0){
            for(int i = 0 ; i < prestamos.size() ; i++){
                prestamo = prestamos.get(i);
                System.out.println(prestamo.toString());
            }
        }else{
            System.out.println("\n\t\t\tEL objeto " + var + " no tiene prestamos asociados\n");
        }
    }
    
    
    /**
     * @return the codigoUsuario
     */
    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * @param codigoUsuario the codigoUsuario to set
     */
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * @return the codigoObjeto
     */
    public int getCodigoObjeto() {
        return codigoObjeto;
    }

    /**
     * @param codigoObjeto the codigoObjeto to set
     */
    public void setCodigoObjeto(int codigoObjeto) {
        this.codigoObjeto = codigoObjeto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the coste
     */
    public float getCoste() {
        return coste;
    }

    /**
     * @param coste the coste to set
     */
    public void setCoste(float coste) {
        this.coste = coste;
    }

    /**
     * @return the disponible
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * @param disponible the disponible to set
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * @return the prestamos
     */
    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    /**
     * @param prestamos the prestamos to set
     */
    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
    public boolean add(Prestamo p){
        return prestamos.add(p);
    }
    
    /**
     * metodo to string de un objeto
     * @return 
     */
    @Override
    public String toString(){        
        return "\t\tCodigo del objeto : " + this.codigoObjeto + " \r\n" +
                "\t\tDescripcion : " + this.descripcion + "\r\n" +
                "\t\tFecha disponibilidad : " + new SimpleDateFormat("dd-MM-yyyy").format(this.fechaInicio) + " - " +  new SimpleDateFormat("dd-MM-yyyy").format(this.fechaFin) + "\r\n" +
                "\t\tCoste prestamo por dia : " + this.coste + "\r\n" ; 
                //"\t " + prestamos.toString() + "\n ";
    }
    
    /**
     * metodo que devulve el tamaño de los prestamos
     * 
     * @return 
     */
    public int getTamPrestamos(){
        return prestamos.size();
    }
}
