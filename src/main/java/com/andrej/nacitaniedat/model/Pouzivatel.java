package com.andrej.nacitaniedat.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pouzivatel")
public class Pouzivatel implements Serializable { 

@ManyToMany(mappedBy = "pouzivateliaList")
private List<Kniha> knihyList;  

@ManyToOne
private Pouzivatel najblizsiPouzivatel;

@OneToMany(mappedBy="najblizsiPouzivatel")
private List<Pouzivatel> podobniPouzivatelia;

    
@Id
@GeneratedValue
private Integer id;
private String katalogoveId;

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
     * @return the katalogoveId
     */
    public String getKatalogoveId() {
        return katalogoveId;
    }

    /**
     * @param katalogoveId the katalogoveId to set
     */
    public void setKatalogoveId(String katalogoveId) {
        this.katalogoveId = katalogoveId;
    }

    /**
     * @return the KnihyList
     */
    public List<Kniha> getKnihyList() {
        return knihyList;
    }

    /**
     * @param knihyList the knihyList to set
     */
    public void setKnihyList(List<Kniha> knihyList) {
        this.knihyList = knihyList;
    }

    /**
     * @return the najblizsiPouzivatel
     */
    public Pouzivatel getNajblizsiPouzivatel() {
        return najblizsiPouzivatel;
    }

    /**
     * @param najblizsiPouzivatel the najblizsiPouzivatel to set
     */
    public void setNajblizsiPouzivatel(Pouzivatel najblizsiPouzivatel) {
        this.najblizsiPouzivatel = najblizsiPouzivatel;
    }

    /**
     * @return the podobniPouzivatelia
     */
    public List<Pouzivatel> getPodobniPouzivatelia() {
        return podobniPouzivatelia;
    }

    /**
     * @param podobniPouzivatelia the podobniPouzivatelia to set
     */
    public void setPodobniPouzivatelia(List<Pouzivatel> podobniPouzivatelia) {
        this.podobniPouzivatelia = podobniPouzivatelia;
    }
 
    
}