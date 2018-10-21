/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author santi
 */
public class Prestamo {
    
    private Date fechaInicio, fechaFin;
    private float importeStartup, importeCliente;
    private Usuario usuario;
    
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
     * @return the importeStartup
     */
    public float getImporteStartup() {
        return importeStartup;
    }

    /**
     * @param importeStartup the importeStartup to set
     */
    public void setImporteStartup(float importeStartup) {
        this.importeStartup = importeStartup;
    }

    /**
     * @return the idUsuario
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /**
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the importeCliente
     */
    public float getImporteCliente() {
        return importeCliente;
    }

    /**
     * @param importeCliente the importeCliente to set
     */
    public void setImporteCliente(float importeCliente) {
        this.importeCliente = importeCliente;
    }
    
    /**
     * metodo to string de la clase
     * 
     * @return 
     */
    @Override
    public String toString(){
        return  "\t\t\tNombre del cliente : " + this.usuario.getNombre() + "\r\n " +
                "\t\t\tFechas del prestamo : " + new SimpleDateFormat("dd-MM-yyyy").format(this.fechaInicio) + " - " +  new SimpleDateFormat("dd-MM-yyyy").format(this.fechaFin) + "\r\n" +
                "\t\t\tImporte para el propietario : " + this.importeCliente + "\r\n " +
                "\t\t\tImporte para la startup : " + this.importeStartup + "\r\n\r\n";        
    }
    
}
