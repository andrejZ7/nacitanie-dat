package com.andrej.nacitaniedat.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transakcia")
public class Transakcia implements Serializable { 

    
@Id
@GeneratedValue
private Integer id;

@ManyToOne
private Pouzivatel pouzivatel;

@ManyToOne
private Kniha kniha;

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