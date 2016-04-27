/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrej.spracovanienacitanychdat;

import com.andrej.nacitaniedat.PersistenceManager;
import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author andre
 */
public class Test {
    
     public static void main(String [] args) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        Integer id = 0011333;
        
        CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        Pouzivatel pouzivatel;
             
        query.where((critBld.equal(root.get("id"), 2545419)));
        Query qu = em.createQuery(query);        
        try{
            pouzivatel = (Pouzivatel) qu.getSingleResult();           
        }
        catch(javax.persistence.NoResultException e){			
            System.out.println("Chyba pri query Transakcie!!!!!!!!!!!!");			
        }
        
        
        em.close();
        PersistenceManager.INSTANCE.close();
        
     }
    
}
