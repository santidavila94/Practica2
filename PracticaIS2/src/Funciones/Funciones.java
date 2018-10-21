/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import clases.Objeto;
import clases.Prestamo;
import clases.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author santi
 */
public class Funciones {
    
    private ArrayList<Objeto> objetos;
    private Objeto objeto;
    private Usuario usuario;
    //variable para controlar los ultimos indices almacenados
    private int indiceUsuario, indiceObjeto;
    //Vector para almacenar todos los ucuarios dados de alta.
    private ArrayList<Usuario> usuarios;
    
    
    /**
     * Constructor de la clase por defecto
     */
    public Funciones(){
        usuarios = new ArrayList<>();
        objetos = new ArrayList<>();
    }
    
    
    
    /**
     * metodo que nos muestra todos los usuarios, sus objetos y los prestamos
     */
     public void mostrarDatosUsuariosConPrestamos(){
        int cantidad = 0;
        
        for(Usuario u : usuarios){
            if(u.comprobarNumeroPrestamos()){
                System.out.println(u.toString());
                u.mostrarObjetos(u.getCodigo());
                System.out.println("\tImporte total acumulado para la startup: " + u.getSumaPrestamo() + " euros\n");
            }
        }
    }
    
    /**
     * metodo que nos permite agregar un prestamo de un objeto
     * 
     * @param idObjeto
     * @param p
     * @return 
     */
    public boolean agregarPrestamo(int idObjeto, Prestamo p){
        boolean agregado = false;
        boolean agregadoFinal = false;
        
        for(int i = 0 ; i < usuarios.size() ; i++){
            usuario = usuarios.get(i);
            agregado = usuario.agregarPrestamo(idObjeto, p);
            if(agregado == true){
                agregadoFinal = true;
            }
        }
        return agregadoFinal;
    }
    
    
    
    /**
     * metodo que nos muestra todos los usuarios dados de alta
     */
    public void mostrarUsuarios(){
        
        if(usuarios.size() > 0){
            for(int i = 0 ; i < usuarios.size() ; i++){
                usuario = usuarios.get(i);
                System.out.println(usuario.toString());
                usuario.mostrarObjetos(usuario.getCodigo());
            }
        }else{
            System.out.println("\n\tNo existen propietarios\n");
        }
    }
    
    
     /**
     * cambia la disponibilidad del objeto
     * 
     * @param codigoObjeto
     */
    public void cambiarDisponibilidad(int codigoObjeto){
        for(Usuario u : usuarios){
            u.cambiarDisponibilidad(codigoObjeto);
        }
    }
    
    
    /**
     * metodo que calcula el precio
     * 
     * @param numDias
     * @param objeto
     * @return 
     */
    public float calcularPrecio(int numDias, Objeto objeto){
        return (numDias * objeto.getCoste());
    }
    
    /**
     * metodo que nos calcula el numero de dias entre 2 fechas
     * 
     * @param fechaInicio
     * @param fechaFin
     * @return 
     */
    public int calcularDias(Date fechaInicio, Date fechaFin){
        int dias = (int)((fechaFin.getTime() - fechaInicio.getTime()) / 86400000); 
        return dias;
    }
    
    
    /**
     * metodo que nos ayuda a añadir un objeto en una lista auxiliar de la clase
     * @param ob 
     */
    public void agregarObjetoListaAuxiliar(Objeto ob){
        this.objetos.add(ob);
    }
    
    
    /**
     * metodo para recuperar el objeto seleccionado
     * @param idObjeto
     * @return 
     */
    public Objeto recuperarObjeto(int idObjeto){                
        for(Objeto o : objetos){
            if(o.getCodigoObjeto() == idObjeto){
                return o;
            }
        }
        return objeto;
    }
    
    /**
     * metodo que nos devulve los datos de una usuario 
     * @param codigoUsuario
     * @return 
     */
    public Usuario obtenerUsuario(int codigoUsuario){
        
        for(Usuario u : usuarios){
            if(u.getCodigo() == codigoUsuario){
                usuario = u;
            }
        }
        return usuario;
    }
    
    /**
     * metodo que nos muestra todos los objetos dados de alta
     *  
     */
    public void mostrarObjetosDisponibles(){
        for(Usuario u : usuarios){
            u.mostrarObjetosDisponibles();
        }       
    }
    
    
    /**
     * metodo en el que se añade un objeto al usuario correspondiente
     * 
     * @param codigoUsuario
     * @param objeto 
     */
    public boolean agregarObjeto(int codigoUsuario, Objeto objeto){
        boolean correcto = false;
        
        for(Usuario u : usuarios){
            if(u.getCodigo() == codigoUsuario){
                u.agregarObjeto(objeto);
                correcto = true;
            }
        }
        return correcto;
    }
    
    
    /**
     * metodo que comprueba que el precio no sea inferior a 0
     * 
     * @param precio
     * @return 
     */
    public boolean comprobarPrecioCorrecto(float precio){
        if(precio > 0){
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * metodo que transforma una cadena a un tipo date(fecha)
     * 
     * @param fecha
     * @return 
     */
    public Date stringToDate(String fecha){
        Date fech = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        try{
            fech = formato.parse(fecha);
        }catch(ParseException ex){
            System.out.println("ERROR AL TRANSFORMAR UN STRING EN FECHA ....\n");
        }
        return fech;
    }
    
    
    /**
     * Metodo que comprueba que la fecha sea correcta 
     * 
     * @param date
     * @return 
     */
    public boolean comprobarFecha(String date){
        boolean valido = false;
        
        try{
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            formato.parse(date);
            valido = true;
        }catch(ParseException e){
            valido = false;
        }
        return valido;
    }
    
    
    /**
     * metodo que comprueba que la fecha de fin es posterior a la de inicio
     * 
     * @param inicio
     * @param fin
     * @return 
     */
    public boolean comprobarFechaPosterior(Date inicio, Date fin){
        boolean valido = false;
        
        if(inicio.before(fin) || inicio.equals(fin)){
            valido = true;
        }
        return valido;
    }
    
    
    
    /**
     * metodo que nos muestra todos los usuarios dados de alta
     *  
     */
    public void mostrarUsuariosDisponibles(){
        String cadenaId;        
        
        for(int i = 0 ; i < usuarios.size() ; i++){
            usuario = usuarios.get(i);
            cadenaId = String.format("%03d", usuario.getCodigo());
            System.out.println("\t" + cadenaId + "\t\t" + usuario.getNombre() + "\t\t" + usuario.getEmail());
        }
    }
    
    
    //metodo para comprobar que el identificador exista
    public boolean comprobarIndice(int tipo, int id){
        boolean valido = false;
        
        if(tipo == 1){
            for(int i = 0 ; i < usuarios.size() ; i++){
                usuario = usuarios.get(i);
                if(usuario.getCodigo() == id){
                    valido = true;
                }               
            }
        }else if(tipo == 2){
            for(Usuario u : usuarios){
                if(u.comprobarIndice(id)){
                    valido = true;
                }
            }
        }
        return valido;
    }
    
    //metodo para crear un identificador para el usuario/objeto
    public int generarIndice(int tipo){
        
        int indice = 0;
        
        if(tipo == 1){
            indice = this.indiceUsuario + 1;
            this.indiceUsuario = indice;
        }else if(tipo == 2){
            indice = this.indiceObjeto + 1;
            this.indiceObjeto = indice;
        }    
        
        return indice;
    }
    
     //metodo para validar un email
    public boolean validarEmail(String email){
        boolean valido = false;
        
        /**
         * comprobamos que el email tenga el formato
         * xxxx@xxx.xxx
         */
        for(int i = 0 ; i < email.length() ; i++){
            if(email.charAt(i) == '@'){
                valido = true;
            }
        }
        return valido;
    }
    
    //metodo para añadir un usuario dado de alta
    public boolean add(Usuario usuario){
        return(usuarios.add(usuario));
    }
    
    /**
     * metodo que nos permite sumar el 10 % de cada alquiler que se realize
     * de un objeto de su propiedad
     * 
     * @param cantidad
     * @param idUsuario 
     */
    public void sumaValorUsuario(float cantidad, int idUsuario){
        for(Usuario u : usuarios){
            if(u.getCodigo() == idUsuario){
                u.setSumaPrestamo(cantidad);
            }
        }
    }
}
