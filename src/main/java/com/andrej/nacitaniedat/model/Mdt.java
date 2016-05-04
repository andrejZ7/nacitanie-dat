
package com.andrej.nacitaniedat.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author andrej
 */

@Entity
@Table(name = "mdt")
public class Mdt implements Serializable {
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Kniha kniha;
    /*@ManyToOne//////////////////////////////////////////
    private KnihaPomocna knihaPomocna;////////////////////////////////*/
    
    @Id
    @GeneratedValue
    private Integer id;
    private String mdt;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the mdt
     */
    public String getMdt() {
        return mdt;
    }

    /**
     * @param mdt the mdt to set
     */
    public void setMdt(String mdt) {
        this.mdt = mdt;
    }

    /**
     * @return the kniha
     */
    public Kniha getKniha() {
        return kniha;
    }

    /**
     * @param kniha the kniha to set
     */
    public void setKniha(Kniha kniha) {
        this.kniha = kniha;
    }
   
    
}
