package com.andrej.nacitaniedat.testovanie;

import com.andrej.databaza.PersistenceManager;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.sluzby.BookServices;
import com.andrej.sluzby.PocetKnih;
import com.andrej.sluzby.UserServices;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author andrej
 */
public class VyhodnotenieOdporucania {
      
    public static void main(String [] args) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        UserServices userService = new UserServices();
        BookServices bookService = new BookServices();
        PocetKnih pocetKnih = new PocetKnih();
        double uspesnost;
        double zhoda = 0;        
        List<Pouzivatel> pouzivateliaList = userService.nacitajVsetkychPouzivatelov(em);
        double pocetPouzivatelov = pouzivateliaList.size();
        Pouzivatel userA;
        
        for (int i=0 ; i<pouzivateliaList.size() ; i++) {
            userA = pouzivateliaList.get(i);           
            if ( bookService.vyhodnotOdporuceneKnihy(userA.getKnihyList(), userA.getOdporuceneKnihy(), pocetKnih.getPercentoPorovnavanychKnih()) ) {            
                //System.out.println("OUU YEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH");
                zhoda++;               
            }
            
        }
        
        uspesnost = (zhoda/pocetPouzivatelov) * 100;
        System.out.println("Pocet pouzivatelov:" +pocetPouzivatelov);
        System.out.println("Zhoda:" + zhoda);
        System.out.println("Uspesnost je: " + uspesnost + "%");
        
        em.close();
        PersistenceManager.INSTANCE.close();
        
    }
}
