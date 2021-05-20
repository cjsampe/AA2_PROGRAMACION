package com.sanvalero.gestionparque;

import com.sanvalero.gestionparque.dao.Conexion;
import com.sanvalero.gestionparque.dao.ParqueDAO;
import com.sanvalero.gestionparque.dao.CiudadDAO;
import com.sanvalero.gestionparque.domain.Ciudad;
import com.sanvalero.gestionparque.domain.Parque;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Carmen Jiménez Sampériz
 */
public class Gestion {
   
    private boolean salir;
    private Scanner teclado;
    private Conexion conexion;
    private ParqueDAO parqueDAO;
    
    public Gestion(){
        salir = false;
        teclado = new Scanner (System.in);
        conexion = new Conexion();
        conexion.conectar();
        parqueDAO = new ParqueDAO(conexion);
    }
    
    
    public void ejecutar() throws SQLException{
        do{
            System.out.println("Aplicación GestionParques v0.1");
            System.out.println("1. Ver parques de una ciudad");
            System.out.println("2. Ver todos los parques de una CCAA");
            System.out.println("3. Añadir parque a una determinada ciudad");
            System.out.println("4. Actualizar información de un parque");
            System.out.println("5. Seleccionar todos los parques cuyo "
                    + "nombre contenga una determinada cadena ");
            System.out.println("6. Número de parques con una extension mayor a...");
            System.out.println("7. Borrar parques de una determinada ciudad");
            System.out.println("8. Listar el nombre de las ciudades que contengan parques "
                    + "cuya suma total de extension sea mayor a...");
            System.out.println("x. Salir");
            System.out.println("Selecciona la opción que desees");
            String opcion = teclado.nextLine();
            
            switch (opcion){
                //hecho
                case"1":
                    verParqueCiudad();
                    break;
                //hecho
                case"2":
                    verParqueCcaa();
                    break;
                case"3":
                    anadirParque();
                    break;
                case"4":
                    buscarParque();
                    actualizarParque();
                    break;
                case"5":
                    seleccionarParque();
                    break;
               /* case"6":
                    sumaParque();
                    break;*/
                case"7":
                    borrarParque();
                    break;
                case"8":
                    extensionParque();
                    break; 
                case"x":
                    salir();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }while(!salir);
    }
    private void salir(){
        salir = true;
        System.out.println("Ha decido salir de la Aplicación. Hasta la próxima");
    }
    
 
   
    //OPCION 1 _ VER LISTA DE PARQUE POR CIUDAD
    private void verParqueCiudad(){
        System.out.println("Nombre de la ciudad: ");
        String ciudad = teclado.nextLine();
    
       
         try {
            ArrayList<Parque> parques = parqueDAO.verParqueCiudad(ciudad);
            for (Parque parque : parques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un error");
            sqle.printStackTrace();
        }
       
    }
    
        
    // OPCION 2 _ VER LISTA DE PARQUE POR CCAA 
    private void verParqueCcaa(){
        System.out.println("Para ver los parques por nombre, escriba una CCAA: ");
        String ccaa = teclado.nextLine();
    
       
         try {
            ArrayList<Parque> parques = parqueDAO.verParqueCcaa(ccaa);
            for (Parque parque : parques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un error");
            sqle.printStackTrace();
        }
        
    }
    
  
   
    // OPCION 3 _ AÑADIR PARQUE A UNA DETERMINADA CIUDAD 
    private void anadirParque(){
        System.out.println("Nombre del parque: ");
        String nombreParque = teclado.nextLine();
        System.out.println("Extensión parque en hectareas: ");
        String hectarea = teclado.nextLine();
        System.out.println("Introduce nombre ciudad");
        String ciudad = teclado.nextLine();
        
        
        Parque parque = new Parque();
        parque.setNombreParque(nombreParque);
        int extension = Integer.parseInt(hectarea);
        parque.setExtension(extension);
        parque.setCiudad(ciudad);
        
        
        
        
        
        try{
        parqueDAO.anadirParque(parque);
        System.out.println("El parque se ha registrado correctamente.");
        }catch (SQLException sqle){
            System.out.println("Se ha producido un error");
            sqle.printStackTrace();
        } 
    }
    
    
    // OPCIÓN 4.1 _ BUSCAR PARQUE POR NOMBRE DE PARQUE
    private void buscarParque() {
        System.out.println("Escribe el nombre del parque que quieres modificar: ");
        String nombreParque = teclado.nextLine();
        
         try {
            ArrayList<Parque> parques = parqueDAO.buscarParque(nombreParque);
            for (Parque parque : parques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
         
}
    
    // OPCION 4.2 _ ACTUALIZAR INFORMACIÓN EXTENSION PARQUE 
        private void actualizarParque(){
        System.out.println("Nombre del parque al que le quieres cambiar la extensión"
                + "(Asegurate de no dejarte ninguna letra): ");
        String nombreParque = teclado.nextLine();
        System.out.println("Extensión parque en hectareas definitiva: ");
        String hectarea = teclado.nextLine();
        
        Parque parque = new Parque();
       
        int extension = Integer.parseInt(hectarea);
        parque.setExtension(extension);
        parque.setNombreParque(nombreParque);
        
        
        
        
        try{
        parqueDAO.actualizarParque(parque);
        System.out.println("El parque se ha modificado correctamente.");
        }catch (SQLException sqle){
            System.out.println("Se ha producido un error");
            sqle.printStackTrace();
        } 
        
    }
        
    // OPCION 5 _ SELECCIONAR PARQUE CON PALABRAS COMUNES
    private void seleccionarParque(){
        System.out.println("Indica la cadena que debe contener nuestro parque: ");
        String NombreParque = teclado.nextLine();
    
       
          try {
            ArrayList<Parque> parques = parqueDAO.seleccionarParque(NombreParque);
            for (Parque parque : parques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
        
    }
    
    /* NO HE CONSEGUIDO QUE DEVUELVA EL NÚMERO 
    OPCION 6.1 DEVOLVER EL NÚMERO DE PARQUES DE UNA DETERMINADA CIUDAD QUE 
    TENGAN EXTENSION INDIVIDUAL MAYOR QUE LA QUE DESEE EL USUARIO 
    private void contar_num_parque(){
        int n=0;
        System.out.println("Escribe el nombre la ciudad que quieres consultar:");
        String ciudad = teclado.nextLine();
        System.out.println("¿Qué extensión debe superar el parque que te mostremos?: ");
        String extension = teclado.nextLine();
      
        //Parque parque = new Parque();
        
       try{
          
        parqueDAO.contar_num_parque();
        System.out.println("El número de parques son " + n);
        }catch (SQLException sqle){
            System.out.println("Se ha producido un error. Inténtelo de nuevo");
            sqle.printStackTrace(); 
        }
        
    }*/
    
     /* NO HE CONSEGUIDO QUE DEVUELVA EL NÚMERO 
    OPCION 6.2 DEVOLVER EL NÚMERO DE PARQUES DE UNA DETERMINADA CIUDAD QUE 
    TENGAN EXTENSION INDIVIDUAL MAYOR QUE LA QUE DESEE EL USUARIO 
    private void sumaParque(){
        System.out.println("Escribe el nombre la ciudad que quieres consultar:");
        String ciudad = teclado.nextLine();
        System.out.println("¿Qué extensión debe superar el parque que te mostremos?: ");
        String extension = teclado.nextLine();
      
        Parque parque = new Parque();
        
       try{
          
        
         System.out.println("numero de renglones: "+resultado.getString(1));
        }catch (SQLException sqle){
            System.out.println("Se ha producido un error. Inténtelo de nuevo");
            sqle.printStackTrace(); 
        }
        
    }*/
    
    // OPCION 7. BORRAR PARQUES DE UNA DETERMINA CIUDAD
    private void borrarParque() throws SQLException{
        System.out.println("Introduce nombre de la ciudad:");;
        String ciudad = teclado.nextLine();
       
        try{
        parqueDAO.borrarParque(ciudad);
        System.out.println("Los parques se han borrado correctamente.");
        }catch (SQLException sqle){
            System.out.println("Se ha producido un error. Inténtelo de nuevo");
            sqle.printStackTrace(); 
        }
    } 
    
     // OPCION 8. BUSCAR NOMBRE CIUDADES PARQUE SUMA EXTENSION MAYOR A FALTAAAAAAA
    private void extensionParque(){
        System.out.println("Quieres ver las ciudades que tengan la suma de extensión más grande que..."
                + " (escribe la extensión en hectareas):");
        String extension= teclado.nextLine();
        
    
       
         try {
            ArrayList<Parque> parques = parqueDAO.extensionParque();
            for (Parque parque : parques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
        
    }
    
    /*OPCION 9. LISTADO DE TODOS LOS PARQUES
    private void verTodosParque(){
        try {
            ArrayList<Parque> parques = parqueDAO.verTodosParque();
            for (Parque parque : parques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            System.out.println("Se ha producido un problema leyendo los datos");
            sqle.printStackTrace();
        }
    }*/
    


    
    
    
    

   
    

    

}
    
    
  
    
   

