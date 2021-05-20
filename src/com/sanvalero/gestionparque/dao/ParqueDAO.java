package com.sanvalero.gestionparque.dao;

import com.sanvalero.gestionparque.domain.Parque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Carmen Jiménez Sampériz
 */
public class ParqueDAO {
    
    private Conexion conexion;
    //graciss a esto podemos hablar directamente con la base de datos
    public ParqueDAO (Conexion conexion){
        this.conexion = conexion;
    }
    
 //OPCION 1 _ VER LISTA DE PARQUE POR CIUDAD
public ArrayList<Parque> verParqueCiudad(String cadenaBusqueda)throws SQLException{
    String sql= "SELECT NOMBRE_PARQUE, EXTENSION, NOMBRE_CIUDAD, CCAA " +
                " FROM CIUDAD INNER JOIN PARQUE " +
                " ON CIUDAD.ID_CIUDAD = PARQUE.ID_CIUDAD " +
                " WHERE CIUDAD.NOMBRE_CIUDAD = ?";
        
        ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            //parque.setIdParque(resultado.getString(1));
            parque.setNombreParque(resultado.getString(1));
            //parque.setCiudad(resultado.getString(2));
            parque.setExtension(resultado.getInt(2));
            parque.setCiudad(resultado.getString(3));
            parque.setCcaa(resultado.getString(4));
            
            parques.add(parque);
        }
        return parques;
    
}

//OPCION 2 _ VER LISTA DE PARQUE POR CCAA
public ArrayList<Parque> verParqueCcaa(String cadenaBusqueda)throws SQLException{
    String sql= "SELECT NOMBRE_PARQUE, EXTENSION, NOMBRE_CIUDAD, CCAA " +
                " FROM CIUDAD INNER JOIN PARQUE " +
                " ON CIUDAD.ID_CIUDAD = PARQUE.ID_CIUDAD " +
                " WHERE CIUDAD.CCAA = ?";
        
        ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setNombreParque(resultado.getString(1));
            parque.setExtension(resultado.getInt(2));
            parque.setCiudad(resultado.getString(3));
            parque.setCcaa(resultado.getString(4));
            
            parques.add(parque);
        }
        return parques;
    
}


// OPCION 3 _ AÑADIR PARQUE A UNA DETERMINADA CIUDAD
public void anadirParque(Parque parque) throws SQLException{
 String sql = "INSERT INTO parque (nombre_Parque, extension, ID_CIUDAD) VALUES (?,?,(SELECT ID_CIUDAD FROM CIUDAD WHERE NOMBRE_CIUDAD=?))";
 
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, parque.getNombreParque());
        sentencia.setInt(2, parque.getExtension());
        sentencia.setString(3, parque.getCiudad());
        
        sentencia.executeUpdate();
}
// OPCION 4 _ BUSCAR PARQUE CON UN NOMBRE DADO POR EL USUSARIO
public ArrayList<Parque> buscarParque(String cadenaBusqueda)throws SQLException{
    String sql= "SELECT NOMBRE_PARQUE, EXTENSION, NOMBRE_CIUDAD, CCAA " +
                " FROM PARQUE INNER JOIN CIUDAD " +
                " ON PARQUE.ID_CIUDAD = CIUDAD.ID_CIUDAD " +
                " WHERE PARQUE.NOMBRE_PARQUE = ?";
        
        ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setNombreParque(resultado.getString(1));
            parque.setExtension(resultado.getInt(2));
            parque.setCiudad(resultado.getString(3));
            parque.setCcaa(resultado.getString(4));
            
            parques.add(parque);
        }
        return parques;
    
}  

// OPCION 4 _ ACTUALIZAR INFORMACIÓN EXTENSION PARQUE
public void actualizarParque(Parque parque) throws SQLException{
  String sql = "UPDATE parque SET EXTENSION = ? WHERE NOMBRE_PARQUE = ?";
 
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setInt(1, parque.getExtension());
        sentencia.setString(2, parque.getNombreParque());
        sentencia.executeUpdate(); 
}



// OPCION 5. SELECCIONAR PARQUE CON PALABRAS COMUNES
public ArrayList<Parque> seleccionarParque(String cadenaBusqueda)throws SQLException{
    String sql= "SELECT NOMBRE_PARQUE FROM PARQUE WHERE UPPER (NOMBRE_PARQUE) LIKE UPPER(?)";
        
        ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, "%" + cadenaBusqueda + "%");
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setNombreParque(resultado.getString(1));
            
            parques.add(parque);
        }
        return parques;
    
}



/**OPCION 6. DEVOLVER EL NÚMERO DE PARQUES DE UNA DETERMINADA CIUDAD QUE 
TENGAN EXTENSION INDIVIDUAL MAYOR QUE LA QUE DESEE EL USUARIO **/
public ArrayList<Parque> sumaParque(String cadenaBusqueda)throws SQLException{
    String sql= "SELECT COUNT(PARQUE.ID_PARQUE) FROM PARQUE INNER JOIN CIUDAD " +
        " ON PARQUE.ID_CIUDAD=CIUDAD.ID_CIUDAD WHERE CIUDAD.NOMBRE_CIUDAD = ? AND "
            + "PARQUE.EXTENSION > ? GROUP BY PARQUE.ID_CIUDAD";
    
        ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        sentencia.setString(2, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        resultado.next();
        
   
        return (ArrayList<Parque>) resultado;
        
}

/*
//OPCION 6 DEVOLVER NUMERO DE PARQUES
public int contar_num_parque() throws SQLException{ 
     String sql= "SELECT COUNT(PARQUE.ID_PARQUE) FROM PARQUE INNER JOIN CIUDAD " +
        " ON PARQUE.ID_CIUDAD=CIUDAD.ID_CIUDAD WHERE CIUDAD.NOMBRE_CIUDAD = ? AND "
            + " PARQUE.EXTENSION > ? GROUP BY PARQUE.ID_CIUDAD";
    
        ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        sentencia.setString(1, ciudad.getNombreCiudad());
        sentencia.setInt(2, parque.getExtension());
        ResultSet resultado = sentencia.executeQuery();
       
        
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setExtension(resultado.getInt(1));
            
            parques.add(parque);
        }
        
        return resultado.getInt(1);
}
        
*/










// OPCION 7. BORRAR PARQUES DE UNA DETERMINA CIUDAD POR NOMBRE CIUDAD
public void borrarParque(String ciudad)throws SQLException{
     String sql = "DELETE FROM PARQUE WHERE PARQUE.ID_CIUDAD =(SELECT ID_CIUDAD "
             + "FROM CIUDAD WHERE CIUDAD.NOMBRE_CIUDAD = ?)";
 
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, ciudad);
        sentencia.executeUpdate(); 
}













// OPCION 8. BUSCAR NOMBRE CIUDADES PARQUE SUMA EXTENSION MAYOR A....
public ArrayList<Parque> extensionParque()throws SQLException{
    String sql= "SELECT CIUDAD.NOMBRE_CIUDAD FROM PARQUE INNER JOIN CIUDAD " +
                " ON CIUDAD.ID_CIUDAD = PARQUE.ID_CIUDAD " +
                " GROUP BY CIUDAD.NOMBRE_CIUDAD, CIUDAD.ID_CIUDAD " +
                " HAVING SUM(PARQUE.EXTENSION)> ? ";
    
    ArrayList<Parque> parques = new ArrayList<>();
        int suma = 0;
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setInt(1,suma);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setCiudad(resultado.getString(1));
            
            parques.add(parque);
        }
        return parques;
}


/*NO CONSEGUIDO
// OPCION 8. BUSCAR NOMBRE CIUDADES PARQUE SUMA EXTENSION MAYOR A....
public ArrayList<Parque> extensionParque(String cadenaBusqueda)throws SQLException{
    String sql= "SELECT CIUDAD.NOMBRE_CIUDAD FROM PARQUE INNER JOIN CIUDAD " +
                " ON CIUDAD.ID_CIUDAD = PARQUE.ID_CIUDAD " +
                " GROUP BY CIUDAD.NOMBRE_CIUDAD, CIUDAD.ID_CIUDAD " +
                " HAVING SUM(PARQUE.EXTENSION)> ? ";
    
    ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setCiudad(resultado.getString(1));
            
            parques.add(parque);
        }
        return parques;
}

*/

// OPCION 9. LISTADO DE TODOS LOS PARQUES
public ArrayList<Parque> verTodosParque()throws SQLException{
    String sql= "SELECT PARQUE.NOMBRE_PARQUE, PARQUE.EXTENSION, CIUDAD.NOMBRE_CIUDAD , CIUDAD.CCAA " +
                "FROM PARQUE INNER JOIN CIUDAD " +
                "ON CIUDAD.ID_CIUDAD = PARQUE.ID_CIUDAD " +
                "GROUP BY PARQUE.NOMBRE_PARQUE, PARQUE.EXTENSION, CIUDAD.NOMBRE_CIUDAD, CIUDAD.CCAA " +
                "ORDER BY CIUDAD.NOMBRE_CIUDAD";
    
    
    ArrayList<Parque> parques = new ArrayList<>();
        
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            //parque.setIdParque(resultado.getInt(1));
            parque.setNombreParque(resultado.getString(1));
            parque.setExtension(resultado.getInt(2));
            //parque.setIdCiudad(resultado.getInt(3));
            parque.setCiudad(resultado.getString(3));
            parque.setCcaa(resultado.getString(4));
            
            
            parques.add(parque);
        }
        return parques;
}





}