package com.andrej.nacitaniedat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.KnihaPomocna;
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
    private static String AUTOR_TAG = "100 1  7kl_us_auth*";
    private static String VYDAVATELSTVO_TAG = "260    ";

    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        
        try (BufferedReader br = new BufferedReader(new FileReader("KlUsCat_example.txt"))) {
            String line;
            int count = 0;
            String isbn;
            String autor;
            String vydavatelstvo;
            DataLoader dataLoader = new DataLoader();
            KnihaPomocna kniha = new KnihaPomocna();
           
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
                      
            while ((line = br.readLine()) != null) {
               // while (line.equals(END_BOOK)) {
                    if (line.startsWith(ISBN_TAG)) {                        
                        isbn = dataLoader.nacitajIsbn(line);
                        if (!"".equals(isbn)) {                            
                            kniha.setIsbn(isbn);                            
                        }
                    }                    
                    else if (line.startsWith(AUTOR_TAG)) {
                        autor = dataLoader.nacitajAutora(line);
                        kniha.setAutor(autor);
                    }
                    else if (line.startsWith(VYDAVATELSTVO_TAG)) {
                        vydavatelstvo = dataLoader.nacitajVydavatelstvo(line);
                        if (!"".equals(vydavatelstvo)) {
                            kniha.setVydavatelstvo(vydavatelstvo);
                        }
                    }
                    else if (line.startsWith("###")) {
                        em.getTransaction()
                            .begin();
                        em.persist(kniha);
                        em.getTransaction()
                            .commit();
                        System.out.println("Autor: " + kniha.getAutor() + "***" + 
                                           "ISBN: " + kniha.getIsbn()  + "***" +
                                           "Vydavatelstvo: " + kniha.getVydavatelstvo() +   "***\n");
                        kniha = new KnihaPomocna();
                    }
                   
              //  }
            }
            
            em.close();
            PersistenceManager.INSTANCE.close();
        }

    
    }
}
