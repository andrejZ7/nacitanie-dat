package com.andrej.nacitaniedat.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pouzivatel")
public class Pouzivatel implements Serializable { 

    
@Id
@GeneratedValue
private Integer id;
private String katalogoveId;

@OneToMany(mappedBy="pouzivatel", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
private List<Transakcia> transakciaList;

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
     * @return the transakciaList
     */
    public List<Transakcia> getTransakciaList() {
        return transakciaList;
    }

    /**
     * @param transakciaList the transakciaList to set
     */
    public void setTransakciaList(List<Transakcia> transakciaList) {
        this.transakciaList = transakciaList;
    }
    
}