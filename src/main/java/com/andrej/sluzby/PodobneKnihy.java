package com.andrej.sluzby;

import com.andrej.nacitaniedat.model.Kniha;

/**
 *
 * @author andrej
 */
public class PodobneKnihy {
    
    private Kniha knihaUserA;
    private Kniha knihaUserB;
    private double podobnost;

    /**
     * @return the knihaUserA
     */
    public Kniha getKnihaUserA() {
        return knihaUserA;
    }

    /**
     * @param knihaUserA the knihaUserA to set
     */
    public void setKnihaUserA(Kniha knihaUserA) {
        this.knihaUserA = knihaUserA;
    }

    /**
     * @return the knihaUserB
     */
    public Kniha getKnihaUserB() {
        return knihaUserB;
    }

    /**
     * @param knihaUserB the knihaUserB to set
     */
    public void setKnihaUserB(Kniha knihaUserB) {
        this.knihaUserB = knihaUserB;
    }

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
}
