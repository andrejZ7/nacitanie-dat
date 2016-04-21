package com.andrej.nacitaniedat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.KnihaPomocna;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.nacitaniedat.model.Transakcia;
import com.andrej.spracovaniedat.DataLoader;
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
    
    private final static String END_TR_TAG = "###";
    private final static String USER_ID_TAG = "100    a";
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("KlTrxVN_example.txt"))) {
            String line;
            int count = 0;
            String[] segmentLine;
            DataLoader dataLoader = new DataLoader();
            Pouzivatel pouzivatel = new Pouzivatel();
            Kniha kniha = new Kniha();
            Transakcia transakcia = new Transakcia();
            EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

            while ((line = br.readLine()) != null) {
                if (line.startsWith(USER_ID_TAG)){
                    pouzivatel = dataLoader.nacitajPouzivatelaZTransakcie(line);
                    transakcia.setPouzivatel(pouzivatel);
                    List<Transakcia> trListPouzivatel = pouzivatel.getTransakciaList();
                    trListPouzivatel.add(transakcia);
                    pouzivatel.setTransakciaList(trListPouzivatel);
                    
                    kniha = dataLoader.nacitajKnihuZTransakcie(line);
                    transakcia.setKniha(kniha);
                    List<Transakcia> trListKniha = kniha.getTransakciaList();
                    trListKniha.add(transakcia);
                    kniha.setTransakciaList(trListKniha);
                    
                }
                else if (line.startsWith(END_TR_TAG)) {                
                    em.getTransaction()
                        .begin();
                    em.persist(transakcia);
                    em.persist(pouzivatel);
                    em.persist(kniha);
                    em.getTransaction()
                        .commit(); 
                    transakcia = new Transakcia();
                    pouzivatel = new Pouzivatel();
                    kniha = new Kniha();
                }
            }
            em.close();
            PersistenceManager.INSTANCE.close();
        }
        /*String idPouzivatela = "0000038";
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
        List<Pouzivatel> pouzivatelList = knihaPomocna.getPouzivatel();
        knihaPomocna.setPouzivatel(pouzivatelList);
       
        
        em.getTransaction()
            .begin();
        em.persist(pouzivatel);        
        em.persist(knihaPomocna);
        em.getTransaction()
            .commit();

        
        
        qu = em.createQuery(query);
        Pouzivatel pouzivatelNovy = (Pouzivatel) qu.getSingleResult();
        quKniha = em.createQuery(queryKniha);
        KnihaPomocna knihaPomocna2 = (KnihaPomocna) quKniha.getSingleResult();
        System.out.println(pouzivatelNovy.getKnihaPomocna().get(0).getIsbn());
        System.out.println(knihaPomocna2.getPouzivatel().get(0).getKatalogoveId());
        
        em.close();
        PersistenceManager.INSTANCE.close();*/
    }
    
}
