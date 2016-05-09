package com.andrej.nacitaniedat;

import com.andrej.databaza.PersistenceManager;
import com.andrej.nacitaniedat.model.Pouzivatel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;

/**
 *
 * @author andre
 */
public class NacitaniePouzivatelov {
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\andre\\Desktop\\ARL_data\\ex_kl\\KlIsUserEx.txt"))) {
            String line;
            int count = 0;
            String[] segmentLine;            
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
            
            while ((line = br.readLine()) != null) {
                
                if (count % 3 == 0){
                    segmentLine = line.split(" ");
                    Pouzivatel pouzivatel = new Pouzivatel();
                    pouzivatel.setKatalogoveId(segmentLine[3]);
                    
                    em.getTransaction()
                        .begin();
                    em.persist(pouzivatel);
                    em.getTransaction()
                        .commit();
                }                
                count++;
            }
            em.close();
            PersistenceManager.INSTANCE.close();
        }
        
        
        

    
    }
}
