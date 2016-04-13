
package com.andrej.nacitaniedat;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "pouzivatel")
public class Pouzivatel implements Serializable{
  @Id
  @GeneratedValue
  private Integer id;
  private Integer katalogoveId;

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
    public Integer getKatalogoveId() {
        return katalogoveId;
    }

    /**
     * @param katalogoveId the katalogoveId to set
     */
    public void setKatalogoveId(Integer katalogoveId) {
        this.katalogoveId = katalogoveId;
    }
          
  
 
}