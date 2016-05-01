package com.andrej.spracovaniedat;

import com.andrej.nacitaniedat.model.Pouzivatel;

/**
 *
 * @author andrej
 */
public class PodobnyPouzivatel {
    
    private Pouzivatel pouzivatel;
    private double podobnost;

    

    /**
     * @return the podobnost
     */
    public double getPodobnost() {
        return podobnost;
    }

    /**
     * @param podobnost the podobnost to set
     */
    public void setPodobnost(double podobnost) {
        this.podobnost = podobnost;
    }

    /**
     * @return the pouzivatel
     */
    public Pouzivatel getPouzivatel() {
        return pouzivatel;
    }

    /**
     * @param pouzivatel the pouzivatel to set
     */
    public void setPouzivatel(Pouzivatel pouzivatel) {
        this.pouzivatel = pouzivatel;
    }
    
}
