package com.andrej.nacitaniedat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.spracovaniedat.DataLoader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;

/**
 *
 * @author andre
 */
public class NacitanieKnih {
    
    private static String END_BOOK = "###";
    private static String ISBN_TAG = "020    a";
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        
        try (BufferedReader br = new BufferedReader(new FileReader("KlUsCat.txt"))) {
            String line;
            int count = 0;
            String isbn;
            DataLoader dataLoader = new DataLoader();
           
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
                      
            while ((line = br.readLine()) != null) {
               // while (line.equals(END_BOOK)) {
                    
                    if (line.startsWith(ISBN_TAG)) {                        
                        isbn = dataLoader.nacitajIsbn(line);
                        if (!"".equals(isbn)) {
                            Kniha kniha = new Kniha();
                            kniha.setIsbn(isbn);
                            em.getTransaction()
                                .begin();
                            em.persist(kniha);
                            em.getTransaction()
                                .commit();
                        }
                    }
                   
              //  }
            }
            
            em.close();
            PersistenceManager.INSTANCE.close();
        }

    
    }
}
