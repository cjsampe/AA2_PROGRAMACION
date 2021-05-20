package com.sanvalero.gestionparque.domain;

/**
 * @author Carmen Jiménez Sampériz
 */
public class Ciudad {
    private int idCiudad;
    private String ciudad;
    private String ccaa;


public Ciudad() {
        
    
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCcaa() {
        return ccaa;
    }

    public void setCcaa(String ccaa) {
        this.ccaa = ccaa;
    }

    



}