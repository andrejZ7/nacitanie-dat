package com.andrej.nacitaniedat.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pouzivatel")
public class Pouzivatel implements Serializable {
  
@ManyToMany(mappedBy = "pouzivatel", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
private List<Kniha> kniha;

@ManyToMany(mappedBy = "pouzivatel", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
private List<KnihaPomocna> knihaPomocna;
    
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
     * @return the kniha
     */
    public List<Kniha> getKniha() {
        return kniha;
    }

    /**
     * @param kniha the kniha to set
     */
    public void setKniha(List<Kniha> kniha) {
        this.kniha = kniha;
    }

    /**
     * @return the knihaPomocna
     */
    public List<KnihaPomocna> getKnihaPomocna() {
        return knihaPomocna;
    }

    /**
     * @param knihaPomocna the knihaPomocna to set
     */
    public void setKnihaPomocna(List<KnihaPomocna> knihaPomocna) {
        this.knihaPomocna = knihaPomocna;
    }
          
  
 
}