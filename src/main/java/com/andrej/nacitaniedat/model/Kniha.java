
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

/**
 *
 * @author andrej
 */

@Entity
@Table(name = "kniha")
public class Kniha implements Serializable {
    
    @OneToMany(mappedBy="kniha", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Mdt> MdtList;
    
    @OneToMany(mappedBy="kniha", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Transakcia> transakciaList;  
    
    @Id
    @GeneratedValue
    private Integer id;
    private String katalogoveId;
    private String isbn;
    private String autor;
    private String vydavatelstvo;
    private String datum;
    private String klucoveSlova;
     
    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the vydavatelstvo
     */
    public String getVydavatelstvo() {
        return vydavatelstvo;
    }

    /**
     * @param vydavatelstvo the vydavatelstvo to set
     */
    public void setVydavatelstvo(String vydavatelstvo) {
        this.vydavatelstvo = vydavatelstvo;
    }

    /**
     * @return the MdtList
     */
    public List<Mdt> getMdtList() {
        return MdtList;
    }

    /**
     * @param MdtList the MdtList to set
     */
    public void setMdtList(List<Mdt> MdtList) {
        this.MdtList = MdtList;
    }

    /**
     * @return the datum
     */
    public String getDatum() {
        return datum;
    }

    /**
     * @param datum the datum to set
     */
    public void setDatum(String datum) {
        this.datum = datum;
    }

    /**
     * @return the klucoveSlova
     */
    public String getKlucoveSlova() {
        return klucoveSlova;
    }

    /**
     * @param klucoveSlova the klucoveSlova to set
     */
    public void setKlucoveSlova(String klucoveSlova) {
        this.klucoveSlova = klucoveSlova;
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
