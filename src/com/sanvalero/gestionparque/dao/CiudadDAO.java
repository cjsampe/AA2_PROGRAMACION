package com.sanvalero.gestionparque.dao;

import com.sanvalero.gestionparque.domain.Parque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Carmen Jiménez Sampériz
 */
public class CiudadDAO {
    
    private Conexion conexion;
    //graciss a esto podemos hablar directamente con la base de datos
    public CiudadDAO (Conexion conexion){
        this.conexion = conexion;
    }
  
}


