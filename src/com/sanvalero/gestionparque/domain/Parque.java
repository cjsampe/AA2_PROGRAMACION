package com.sanvalero.gestionparque.domain;

/**
 *
 * @author Carmen Jiménez Sampériz
 */
public class Parque {
    private int idParque;
    private String nombreParque;
    private int extension;
    private String modextension;
    private int idCiudad;
    private String ciudad;
    private String ccaa;
    private String numeroParques;

    
    public Parque(){
        
    }
    
    
    public String getNumeroParques() {
        return numeroParques;
    }

    public void setNumeroParques(String numeroParques) {
        this.numeroParques = numeroParques;
    }

    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    
    public int getIdParque() {
        return idParque;
    }

    public void setIdParque(int idParque) {
        this.idParque = idParque;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String getNombreParque() {
        return nombreParque;
    }

    public void setNombreParque(String nombreParque) {
        this.nombreParque = nombreParque;
    }

    public String getModextension() {
        return modextension;
    }

    public void setModextension(String modextension) {
        this.modextension = modextension;
    }

    public String getCcaa() {
        return ccaa;
    }

    public void setCcaa(String ccaa) {
        this.ccaa = ccaa;
    }



    
    @Override
    public String toString() {
        return "Parque{" + "nombreParque=" + nombreParque + ", extension=" + extension + ", ciudad=" + ciudad + ", ccaa=" + ccaa +   '}';
    }

    
   
    



    
}
