package com.andrej.nacitaniedat;

import com.andrej.databaza.PersistenceManager;
import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.sluzby.DataLoaderServices;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.persistence.EntityManager;

/**
 *
 * @author andrej
 */
public class NacitanieTransakcii {
    
    private final static String END_TR_TAG = "###";
    private final static String USER_ID_TAG = "100    a";
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
       // try (BufferedReader br = new BufferedReader(new FileReader("KlTrxVN_example.txt"))) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\andre\\Desktop\\ARL_data\\ex_kl\\KlTrxVN.txt"))) {
            String line;
            DataLoaderServices dataLoader = new DataLoaderServices();
            Pouzivatel pouzivatel = new Pouzivatel();
            Kniha kniha = new Kniha();            
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

            while ((line = br.readLine()) != null) {
                if (line.startsWith(USER_ID_TAG)){
                    pouzivatel = dataLoader.nacitajPouzivatelaZTransakcie(line, em);                    
                    kniha = dataLoader.nacitajKnihuZTransakcie(line, em);                    
                    if (kniha != null){
                        pouzivatel.getKnihyList().add(kniha);
                        kniha.getPouzivateliaList().add(pouzivatel);
                    }
                }
                else if (line.startsWith(END_TR_TAG)) {                
                    em.getTransaction()
                      .begin();                    
                    if (pouzivatel != null && kniha != null) {
                        em.merge(pouzivatel);
                        em.merge(kniha);
                        em.flush();
                    }                                     
                    em.getTransaction()
                      .commit();                                       
                    pouzivatel = new Pouzivatel();
                    kniha = new Kniha();
                }
            }
            em.close();
            PersistenceManager.INSTANCE.close();
        }
    }
    
}
