/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import clases.Objeto;
import clases.Prestamo;
import clases.Usuario;
import clases.UsuariosAsiduos;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author santi
 */
public class Funciones {
    
    private ArrayList<Objeto> objetos;
    private Objeto objeto;
    private Usuario usuario;
    private Prestamo prestamo;
    private UsuariosAsiduos asiduo;
    private ArrayList<UsuariosAsiduos> asiduos;
    //variable para controlar los ultimos indices almacenados
    private int indiceUsuario, indiceObjeto;
    //Vector para almacenar todos los ucuarios dados de alta.
    private ArrayList<Usuario> usuarios;
    //vector para almacenar los prestamos
    private ArrayList<Prestamo> prestamos;
    //vector para almacenar lo ids de lo clientes con prestamos
    private ArrayList<Integer> ids;
    
    /**
     * Constructor de la clase por defecto
     */
    public Funciones(){
        usuarios = new ArrayList<>();
        objetos = new ArrayList<>();
        prestamos = new ArrayList<>();
        ids = new ArrayList<>();
        asiduos = new ArrayList<>();
    }
    
    /**
     * metodo que muestra de mayor a menor los usuarios que ha realizado prestamos
     * 
     * @return 
     */
    public boolean mostarUsuariosAsiduos(){
        Comparator<Float> comparador = Collections.reverseOrder();
        ArrayList <Float> cantidades = new ArrayList<>();
        boolean ok = false;
        
        //extraemos los id de los clientes
        this.extraerIds();
        
        if(this.ids.size() > 0){
            ok = true;
            // calculamos el importe total para cada usuario
            for(int i = 0 ; i < this.ids.size() ; i++){
                asiduo = new UsuariosAsiduos();
                for(Prestamo p : prestamos){
                    if(this.ids.get(i) == p.getUsuario().getCodigo()){
                        asiduo.setUsuario(p.getUsuario());
                        asiduo.sumarImporte(p.getImporteCliente());
                    }
                }
                //añadimos a la lista
                asiduos.add(asiduo);
            }
            
            //copiamos solo los valores totales de cada cliente en otro array de float
            for(int i = 0 ; i < this.asiduos.size() ; i++){
                cantidades.add(asiduos.get(i).getTotal());
            }
            
            //ordenamos de mayor a menor
            Collections.sort(cantidades, comparador);
            
            //comparamos para cada cantidad existente al usuario al que le corresponde
            //y mostramos los datos por pantalla
            for(int i = 0 ; i < cantidades.size() ; i++){
                for(int j = 0 ; j < asiduos.size() ; j++){
                    if(cantidades.get(i) == asiduos.get(j).getTotal()){
                        this.mostrarInformacion(asiduos.get(j));
                        asiduos.remove(j);
                    }
                }
            }
            //limpiamos los arrays
            cantidades.clear();
            this.ids.clear();
            this.asiduos.clear();
        }
        return ok;
    }
    
    /**
     * metodo que muestra la informacion deseada en la funcion 10
     * @param as 
     */
    public void mostrarInformacion(UsuariosAsiduos as){
        System.out.println("");
        System.out.println("\tCodigo : \t" + as.getUsuario().getCodigo());
        System.out.println("\tNombre : \t" + as.getUsuario().getNombre()); 
        System.out.println("\tDireccion : \t" + as.getUsuario().getDireccion());
        System.out.println("\tPoblacion : \t" + as.getUsuario().getPoblacion());
        System.out.println("\tProvincia : \t" + as.getUsuario().getProvincia());
        System.out.println("\tTotal : \t" + as.getTotal());
        System.out.println("");
    }
    
    /**
     * metodo que nos ayuda a extraer todos los ids de los clientes 
     * que tiene prestamos.
     */
    public void extraerIds(){        
        
        for(Prestamo p : prestamos){
            if(!this.comprobarArray(p.getUsuario().getCodigo())){
                ids.add(p.getUsuario().getCodigo());
            }
        }
    }
    
    /**
     * metodo que comprueba si el id del cliente esta o no 
     * en el array
     * 
     * @param id
     * @return 
     */
    public boolean comprobarArray(int id){
        boolean esta = false;
        
        for(int i : this.ids){
            if(id == i){
                esta = true;
            }
        }        
        return esta;
    }
    
    /**
     * metodo que nos permite eliminar el prestamo de un usuario 
     * 
     * @param idUsuario
     * @return 
     */
    public boolean eliminarPrestamo(int idUsuario){
        boolean eliminado = false;
        
        for(int i = 0 ; i < prestamos.size() ; i++){
            prestamo = prestamos.get(i);
            if(prestamo.getUsuario().getCodigo() == idUsuario){
                prestamos.remove(i);
                eliminado = true;
            }
        }        
        return eliminado;
    }
    
    /**
     * metodo que elimina un usuario
     * 
     * @param idUsuario
     * @return 
     */
    public boolean eliminarUsuario(int idUsuario){
        boolean eliminado = false;
        int id = 0;
        
        for(int i =  0; i < usuarios.size() ; i++){
            usuario = usuarios.get(i);            
            if(idUsuario == usuario.getCodigo()){
                usuarios.remove(id);
                eliminado = true;
            }
        }
        
        return eliminado;
    }
    
    /**
     * metodo que nos permite guardar los datos de la opcion 6
     * en un fichero
     * 
     * @param nombreFichero
     * @return 
     */
    public boolean guardarDatosEnFichero(String nombreFichero){
        boolean correcto = false;
        DecimalFormat formato = new DecimalFormat("#.00");
        String cadena = "";
        FileWriter escribir = null;
        
        try{           
            File archivo = new File(nombreFichero);
            
            escribir = new FileWriter(archivo);
            
            for(Usuario u : usuarios){
                if(u.comprobarNumeroPrestamos()){
                    cadena += u.toString();
                    cadena += u.devolverObjetos(u.getCodigo());
                    cadena += "\r\n\tImporte total acumulado para la startup: " + formato.format(u.getSumaPrestamo()) + " euros\r\n";
                    
                    if(cadena != ""){
                        correcto = true;
                    }
                    escribir.write(cadena);
                } 
            }  
            escribir.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return correcto;
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
        
        //primero modificamos el importe del objeto auxiliar que esta en esta clase
        for(Objeto o : objetos){
            if(o.getCodigoObjeto() == idObjeto){
                o.setCoste(importeNuevo);
                correcto = true;
            }
        }
        
        //modificamos el valor del objeto del cliente
        for(Usuario u : usuarios){
            //recorremos cada usuario y sus objetos, hasta que encuentre el objeto que busca
            //y cambie su importe
            if(u.modificarImporte(idObjeto, importeNuevo)){
                correcto = true;
            }
        }
        return correcto;
    }
    
    /**
     * metodo que comprueba si tenemos objetos dados de alta o no
     * 
     * @return 
     */
    public int comprobarNumeroObjetos(){
        return objetos.size();
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
     * metodo que añade un prestamo
     * 
     * @param p
     * @return 
     */
    public boolean add(Prestamo p){
        return (prestamos.add(p));
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
