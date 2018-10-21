/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1final;

import Funciones.Funciones;
import clases.Objeto;
import clases.Prestamo;
import clases.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author santi
 */
public class Practica1Final {
    
    private static Funciones star = new Funciones();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion = 0;
        Scanner lectura = new Scanner(System.in);
        
        do{
            //mostramos las opciones del menu
            menuPrincipal();
            
            //leemos la opcion elegida por el usuario
            System.out.print("\n\nIngresa la opcion que desees  : ");
            opcion = Integer.parseInt(lectura.nextLine());
            
            switch(opcion){
                case 1:
                   menuOpcion1();
                break;
                case 2:
                    menuOpcion2();
                break;
                case 3:
                    menuOpcion3();
                break;
                case 4:
                    menuOpcion4();
                break;
                case 5:
                    menuOpcion5();
                break;
                case 6:
                    menuOpcion6();
                break;
            }
        }while(opcion != 7); 
    } 
    
     /**
     * menu con la opcion 6 
     */
    public static void menuOpcion6(){
        System.out.println("");
        star.mostrarDatosUsuariosConPrestamos();
        System.out.println("\n");
    }
    
    
    /**
     * menu con la opcion 5
     */
    public static void menuOpcion5(){
        int codigo;
        Scanner lectura = new Scanner(System.in);
        
        System.out.println("\n\tObjetos disponibles :");
        System.out.println("");
        star.mostrarObjetosDisponibles();
        
        
        System.out.print("\n\tIngresa el codigo del objeto : ");
        codigo = Integer.parseInt(lectura.nextLine());
        
        if(star.comprobarIndice(2, codigo)){
            star.cambiarDisponibilidad(codigo);
            System.out.println("\n\tSe ha dado de baja correctamente el objeto....\n");
        }
    }
    
    /**
     * metodo que nos mostrar todos los datos en forma de arbol 
     */
    public static void menuOpcion4(){        
        System.out.println(""); 
        star.mostrarUsuarios();
        System.out.println("\n");
    }
    
    
    /**
     * Menu opcion 3
     * 
     */
    public static void menuOpcion3(){
        int codigoUsuario, codigoObjeto;
        Date fechaInicio, fechaFin;
        Objeto objeto;
        float coste = 0;
        int dias;
        Prestamo prestamo = new Prestamo();
        Usuario usuario = null;        
        
        System.out.println("");
        //leemos un usuario que este dado de alta
        codigoUsuario = leerUsuario();
        
        //obtenemos los datos del usuario
        usuario = star.obtenerUsuario(codigoUsuario);
        
        //leemos un objeto que este dado de alta
        codigoObjeto = leerObjeto();
                
        //leemos las fechas de inicio y fin
        fechaInicio = leerFecha("Inicio");
        fechaFin = leerFechaFin(fechaInicio);
        
        //obtenemos el objeto seleccionado para luego comprobar las fechas 
        objeto = star.recuperarObjeto(codigoObjeto);
        
        //comprobamos la disponibilidad del objeto
        //es decir que las fechas ingresadas enten entre las fechas 
        //disponibles del objeto
        if(star.comprobarFechaPosterior(objeto.getFechaInicio(), fechaInicio) && star.comprobarFechaPosterior(fechaFin, objeto.getFechaFin())){
            //calculamo el numero de dias
            dias = star.calcularDias(fechaInicio, fechaFin);
            
            //calculamos el coste
            coste = star.calcularPrecio(dias + 1, objeto);
                        
            //creamos un prestamo y lo almacenamos
            prestamo.setUsuario(usuario);
            prestamo.setFechaInicio(fechaInicio);
            prestamo.setFechaFin(fechaFin);
            prestamo.setImporteStartup((float) (coste*0.10));
            prestamo.setImporteCliente(coste);
            
            //almacenamos por cada prestamos, el correspondiente valor al dueño del objeto
            star.sumaValorUsuario((float)(coste*0.10), objeto.getCodigoUsuario());
            
            //almacenamos el prestamo 
            if(star.agregarPrestamo(objeto.getCodigoObjeto(), prestamo)){
                System.out.println("\n\tEl prestamo a sido almacenado correctamente ....\n");
                usuario = null;
                objeto = null;
            }else{
                System.out.println("\n\tERROR, EL PRESTAMO NO A SIDO ALMACENADO....\n");
            }            
        }else{
            System.out.println("\n\tERROR, LAS FECHAS INGRESADAS deben estar entre " + new SimpleDateFormat("dd-MM-yyyy").format(objeto.getFechaInicio())+ " - " + 
                        new SimpleDateFormat("dd-MM-yyyy").format(objeto.getFechaFin()) + "\n");
        }
    }
    
     
    /**
     *  metodo que nos permite leer un objeto dado de alta previamente
     * 
     * @return 
     */
    public static int leerObjeto(){
        int codigoObjeto = 0;
        Scanner lectura = new Scanner(System.in);
        boolean valido = false;
        
        //mostramos todos los objetos dados de alta
        System.out.println("\n\t");
        System.out.println("\tLos objetos dados de alta son : \n");
        star.mostrarObjetosDisponibles();
        System.out.println("");
        do{
            System.out.print("\tIntroduce el id del objeto : ");
            codigoObjeto = Integer.parseInt(lectura.nextLine());
            
            //comprobamos que el id sea el correcto
            if(star.comprobarIndice(2, codigoObjeto)){
                valido = true;
            }else{
                System.out.println("\tERROR, EL ID DEL OBJETO NO ESTA DISPONIBLE O NO ES EL CORRECTO....");
            }
            
        }while(valido != true);
        
        return codigoObjeto;
    }
    
    
    /**
     * Menu opcion 2
     * 
     */
    public static void menuOpcion2(){
        Scanner lectura = new Scanner(System.in);
        String descripcion;
        Date fechaInicio, fechaFin;
        int codigoUsuario, codigoObjeto;
        float coste;
        Objeto objeto;
                
        System.out.println("");
        //leemos un codigo de usuario correcto
        codigoUsuario = leerUsuario();        
        
        //generamos el codigo del objeto automaticamente
        codigoObjeto = star.generarIndice(2);
            
        //leemos los datos del objeto
        System.out.print("\tIntroduce una descripcion del objeto: ");
        descripcion = lectura.nextLine();
            
        //leemos las fechas de inicio y fin
        fechaInicio = leerFecha("Inicio");
        fechaFin = leerFechaFin(fechaInicio);
            
        //leemos un precio
        coste = leerPrecio();
           
        //guardamos todos los datos en un objeto
        objeto = new Objeto(codigoUsuario, codigoObjeto, descripcion, fechaInicio, fechaFin, coste);
        //agregamos la disponibilidad
        objeto.setDisponible(true);
        
        //lo guardamos en el vector de objetos, segun el cliente que se a seleccionado        
        if(star.agregarObjeto(codigoUsuario, objeto)){
            //almacenamos el objeto en una lista auxiliar, para luego poder utilizarla
            //para buscar algun objeto en concreto
            star.agregarObjetoListaAuxiliar(objeto);            
            //mostramos mensaje de que todo a sido almacenado correctamente
             System.out.println("\n\tEl objeto a sido almacenado correctamente .....!!!!\n");
        }else{
            System.out.println("\tERROR: El objeto no esta registrado....");
        }
    }
    
     /**
     *  Metodo que nos ayuda a leer un usuario previamente dado de alta
     * 
     * @return 
     */
    public static int leerUsuario(){
        int codigoUsuario;
        boolean valido = false;
        Scanner lectura = new Scanner(System.in);
        
        //mostramos todos los ususarios que estan dados de alta
        System.out.println("\tLos usuarios disponibles son : \n");
        star.mostrarUsuariosDisponibles();
        
        do{
            //leemos el codigo del propietario
            System.out.print("\n\tSelecciona el id del usuario : ");
            codigoUsuario = Integer.parseInt(lectura.nextLine());
            
            //comprobamos que el indice del usuario este dado de alta
            if(star.comprobarIndice(1, codigoUsuario)){
                valido = true;
            }else{
                System.out.println("\tERROR AL INGRESAR ID DE USUARIO, VUELVE A INGRESARLO...");
            }
        }while(valido != true);
        
        return codigoUsuario;
    }
    
    /**
     * metodo que nos lee un precio del objeto valido
     * 
     * @return 
     */
    public static float leerPrecio(){
        float precio;
        Scanner lectura = new Scanner(System.in);
        boolean valido = false;
        
        do{
            //leemos el coste
            System.out.print("\tIntroduce el coste por dia del objeto(debe ser mayor a 0): ");
            precio = Float.parseFloat(lectura.nextLine());
            
            if(star.comprobarPrecioCorrecto(precio)){
                valido = true;
            }else{
                System.out.println("\tERROR AL INGRESAR EL PRECIO....");
            }
            
        }while(valido != true);
        
        return precio;
    }
    
    /**
     * Metodo para leer una fecha
     * @param tipo
     * @return 
     */
    public static Date leerFecha(String tipo){
        Scanner lectura = new Scanner(System.in);
        boolean valido = false;
        String fecha;
        Date fechaDate = null;
        
        do{
            try{
                //leemos una fecha
                System.out.print("\tIntroduce una fecha de " + tipo + " en formato dia/mes/anio: ");
                fecha = lectura.nextLine();
                
                //comprobamo si la fecha ingresada es correcta
                if(star.comprobarFecha(fecha)){
                    valido = true;
                    //si es correcta generamos la fecha a guardar
                    fechaDate = star.stringToDate(fecha);
                }else{
                    System.out.println("\tHAS INGRESADO UNA FECHA INCORRECTA, VUELVE A INTRODUCIRLA...");
                }
            }catch(NumberFormatException e){
                System.out.println("ERROR DE EJECUCION ....");
            }
            
        }while(valido != true);
        
        return fechaDate;
    }
    
    
    /**
     * metodo que nos devuelve una fecha de fin correcta, es decir posterior a la de inicio
     * 
     * @param fechaInicio
     * @return 
     */
    public static Date leerFechaFin(Date fechaInicio){
        Date fechaFin = null;
        boolean valido = false;
        
        do{
            fechaFin = leerFecha("Fin");
            
            if(star.comprobarFechaPosterior(fechaInicio, fechaFin)){
                valido = true;
            }else{
                System.out.println("\tERROR, LA FECHA LEIDA DEBE SER POSTERIOR A LA DE INICIO ....");
            }
            
        }while(valido != true);
        
        return fechaFin;
    }
    
    /**
     * metodo con la opcion 1 del menu
     */
    public static void menuOpcion1(){
        Scanner lectura = new Scanner(System.in);
        String nombre, email;
        boolean validarNombre, validarEmail;
        Usuario usuario = null;
         
        validarEmail = false;
        validarNombre = false;
        
        /**
         * Controlamos que el nombre no este vacio
         */
        do{
            System.out.print("\n\tIngresa el nombre del usuario : ");
            nombre = lectura.nextLine();
            
            //controlamos que el usuario ingrese el nombre
            if(!nombre.equals("")){
                validarNombre = true;
            }else{
                System.out.println("\tERROR El nombre no puede ser vacio");
            } 
        }while(validarNombre != true);
        
        /**
         * Controlamos que el email sea correcto
         */
        do{
            System.out.print("\tIngresa un correo electronico valido en formato XXXX@XXXXX : ");
            email = lectura.nextLine();
            
            //controlamos que el usuario ingrese el nombre
            if(!email.equals("") && star.validarEmail(email)){
                validarEmail = true;
                
                //agregamos el usuario
                usuario = new Usuario(star.generarIndice(1), nombre, email);
                star.add(usuario);
                
                //mostramos mensaje de que todo a sido almacenado correctamente
                System.out.println("\n\tEl usuario a sido almacenado correctamente .....!!!!\n");
            }else{
                System.out.println("\tERROR El email no puede ser vacio o distinto al formato XXXX@XXXX");
            } 
        }while(validarEmail != true);
    }
    
    /**
     * Contenido del menu pricipal
     */
    public static void menuPrincipal(){        
        System.out.println("1. Alta de usuario");
        System.out.println("2. Alta de objeto");
        System.out.println("3. Alquiler de objeto");
        System.out.println("4. Listar todos los objetos");
        System.out.println("5. Baja de objetos");
        System.out.println("6. Mostrar Saldos");
        System.out.println("7. Salir");        
    } 
}
