package com.andrej.nacitaniedat;

import com.andrej.nacitaniedat.model.KnihaPomocna;
import com.andrej.nacitaniedat.model.Pouzivatel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author andrej
 */
public class NacitanieTransakcii {
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        /*try (BufferedReader br = new BufferedReader(new FileReader("KlTrxVN_example.txt"))) {
            String line;
            int count = 0;
            String[] segmentLine;            
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

            while ((line = br.readLine()) != null) {

            }
            em.close();
            PersistenceManager.INSTANCE.close();
        }*/
        String idPouzivatela = "0000038";
        String isbnKniha = "80-202-0067-3";
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        CriteriaQuery<KnihaPomocna> queryKniha = critBld.createQuery(KnihaPomocna.class);  
        Root<KnihaPomocna> rootKniha = queryKniha.from(KnihaPomocna.class);
        
        queryKniha.where((critBld.equal(rootKniha.get("isbn"), isbnKniha)));        
        query.where((critBld.equal(root.get("katalogoveId"), idPouzivatela)));
        Query qu = em.createQuery(query);
        Query quKniha = em.createQuery(queryKniha);
        Pouzivatel pouzivatel = (Pouzivatel) qu.getSingleResult();
        System.out.println(pouzivatel.getKatalogoveId());
        
       
        KnihaPomocna knihaPomocna = (KnihaPomocna)  quKniha.getSingleResult();
        
        System.out.println(knihaPomocna.getIsbn());
        
        List<KnihaPomocna> knihaPomocnaList = pouzivatel.getKnihaPomocna();
        knihaPomocnaList.add(knihaPomocna);
        pouzivatel.setKnihaPomocna(knihaPomocnaList);
       
        
        em.getTransaction()
            .begin();
        em.persist(pouzivatel);
        em.getTransaction()
            .commit();

        
        
        qu = em.createQuery(query);
        Pouzivatel pouzivatelNovy = (Pouzivatel) qu.getSingleResult();
        System.out.println(pouzivatelNovy.getKnihaPomocna().get(0).getIsbn());
        
        em.close();
        PersistenceManager.INSTANCE.close();
    }
    
}
