package com.andrej.nacitaniedat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.KnihaPomocna;
import com.andrej.nacitaniedat.model.Mdt;
import com.andrej.spracovaniedat.DataLoader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
    private static String MDT_TAG = "080    a";
    private static String MDT_COSM_TAG = "962    a";
    private static String DATUM_TAG = "008    ";

    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        
        try (BufferedReader br = new BufferedReader(new FileReader("KlUsCat_example.txt"))) {
            String line;
            int count = 0;
            String isbn;
            String autor;
            String vydavatelstvo;
            String mdtString;
            String mdtCosmString;
            String datum;
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
                    else if (line.startsWith(MDT_TAG)) {
                        mdtString = dataLoader.nacitajMdt(line);
                        Mdt mdt = new Mdt();
                        mdt.setMdt(mdtString);                        
                        List<Mdt> MdtList = kniha.getMdtList();
                        kniha.setMdtList(MdtList);
                        mdt.setKnihaPomocna(kniha);                         
                        em.persist(mdt);                        
                    }
                    else if (line.startsWith(MDT_COSM_TAG)){
                        mdtCosmString = dataLoader.nacitajMdtCosm(line);
                        if (mdtCosmString.indexOf('+') >= 0){       //ak obsahuje znak plus tak sa tam nachadza viac mdt a treba rozdelovat
                            String[] mdtList = mdtCosmString.split("\\+");
                            for (int i=0 ; i<mdtList.length ; i++)  {
                                Mdt mdt = new Mdt();
                                mdt.setMdt(mdtList[i]);                        
                                List<Mdt> MdtList = kniha.getMdtList();
                                kniha.setMdtList(MdtList);
                                mdt.setKnihaPomocna(kniha);                         
                                em.persist(mdt); 
                            }
                        }
                        else {
                            Mdt mdt = new Mdt();
                            mdt.setMdt(mdtCosmString);                        
                            List<Mdt> MdtList = kniha.getMdtList();
                            kniha.setMdtList(MdtList);
                            mdt.setKnihaPomocna(kniha);                         
                            em.persist(mdt);
                        }
                        
                    }
                    else if (line.startsWith(DATUM_TAG)) {
                        datum = dataLoader.nacitajDatum(line);
                        if (!"".equals(datum)){
                            kniha.setDatum(datum);
                        }
                    }
                    else if (line.startsWith(END_BOOK)) {
                        if (kniha.getIsbn() != null){   //do DB sa ulozi iba ak ma ISBN teda je to kniha
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
                    }
                   
              //  }
            }
            
            em.close();
            PersistenceManager.INSTANCE.close();
        }

    
    }
}
