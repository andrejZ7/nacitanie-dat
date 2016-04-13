package com.andrej.nacitaniedat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;

/**
 *
 * @author andre
 */
public class Run {
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("usersFile.txt"))) {
            String line;
            int count = 0;
            String[] segmentLine;            
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
            
            while ((line = br.readLine()) != null) {
                
                if (count % 3 == 0){
                    segmentLine = line.split(" ");
                    Pouzivatel pouzivatel = new Pouzivatel();
                    pouzivatel.setKatalogoveId(Integer.parseInt(segmentLine[3]));
                    
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
