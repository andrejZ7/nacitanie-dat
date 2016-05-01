/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrej.spracovanienacitanychdat;

import com.andrej.nacitaniedat.PersistenceManager;
import com.andrej.nacitaniedat.model.Kniha;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andre
 */
public class Test {
    
    private static String upravDatumNaCislo(String datum) {
        if (StringUtils.isNumeric(datum)) {
            return datum;
        }
        else {
            String pattern = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
            String[] dateArray = datum.split(pattern);
            for (int i=0 ; i<dateArray.length ; i++) {
                if ( StringUtils.isNumeric(dateArray[i]) ) {
                    return dateArray[i];
                }
            }
        }
        return datum;
    }
    
     public static void main(String [] args) {
        
      
       /* CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        Pouzivatel pouzivatel;
             
        query.where((critBld.equal(root.get("id"), 2556812)));
        Query qu = em.createQuery(query);        */
        
       /* CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        
        query.select(root);
        List<Pouzivatel> pouzivateliaList;
   
	Query qu = em.createQuery(query);
	try{
            pouzivateliaList = (List<Pouzivatel>) qu.getResultList();    
        }
        catch(javax.persistence.NoResultException e){			
            System.out.println("Chyba pri query Transakcie!!!!!!!!!!!!");			
        }
        
        
        em.close();
        PersistenceManager.INSTANCE.close();*/
               
        /*CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        Pouzivatel pouzivatel;

        query.where((critBld.equal(root.get("id"), "2546887")));
        Query qu = em.createQuery(query);        

        pouzivatel = (Pouzivatel) qu.getSingleResult();
        
        int sda = 78;
        */
        
        List<Kniha> vsetkyKnihy;
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Kniha> query = critBld.createQuery(Kniha.class);  
        Root<Kniha> root = query.from(Kniha.class);
        
        query.select(root);   
	Query qu = em.createQuery(query);
	
        vsetkyKnihy = (List<Kniha>) qu.getResultList();    
         
        
        
        String upravenyDatum;
        
        for (int i=0 ; i<vsetkyKnihy.size() ; i++) {
            if (vsetkyKnihy.get(i).getDatum() != null) {
                upravenyDatum = upravDatumNaCislo(vsetkyKnihy.get(i).getDatum());           
                vsetkyKnihy.get(i).setDatum(upravenyDatum);            
            }
        
        }
     
     
     
     }
    
}
