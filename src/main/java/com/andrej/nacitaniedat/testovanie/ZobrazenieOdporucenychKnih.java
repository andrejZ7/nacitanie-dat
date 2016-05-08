package com.andrej.nacitaniedat.testovanie;

import com.andrej.databaza.PersistenceManager;
import com.andrej.nacitaniedat.model.Pouzivatel;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author andrej
 */
public class ZobrazenieOdporucenychKnih {
    
    public static void main(String [] args) throws URISyntaxException, IOException {
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        
        CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        Pouzivatel pouzivatel;
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Zadajte id pouzivatela: ");
        String idPouzivatela = scanner.next();
        //idPouzivatela = "2541648";

        try {
            query.where((critBld.equal(root.get("id"), idPouzivatela)));
            Query qu = em.createQuery(query);
            try {
                pouzivatel = (Pouzivatel) qu.getSingleResult();
                for (int i=0 ; i<pouzivatel.getOdporuceneKnihy().size() ; i++) {
                    String Isbn = pouzivatel.getOdporuceneKnihy().get(i).getIsbn();
                    String myUrl = "https://www.obalkyknih.cz/view?isbn=";
                    myUrl += Isbn;        
                    URI myURI = new URI(myUrl);        
                    java.awt.Desktop.getDesktop().browse(myURI);
                    myUrl = "";
                }
            }
            catch(javax.persistence.NoResultException e){			
                System.out.println("Pouzivatel sa nenasiel!");			
            }
        }
        catch(java.lang.NumberFormatException e) {
            System.out.println("Zly format id pouzivatela!");
        }
        
        em.close();
        PersistenceManager.INSTANCE.close();
        
    }
    
}
