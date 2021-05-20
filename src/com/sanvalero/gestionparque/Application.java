
package com.sanvalero.gestionparque;

import java.sql.SQLException;

/**
 * @author Carmen Jiménez Sampériz
 */
public class Application {
    public static void main(String[] args) throws SQLException {
        Gestion gestion = new Gestion();
        gestion.ejecutar();
        
    }
    
}
