package com.andrej.nacitaniedat.testovanie;

import com.andrej.nacitaniedat.PersistenceManager;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.spracovaniedat.BookServices;
import com.andrej.spracovaniedat.UserServices;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author andrej
 */
public class VyhodnotenieOdporucania {
    
    private static double PERCENTO_POROVNAVANYCH_KNIH = 80;
    
    public static void main(String [] args) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        UserServices userService = new UserServices();
        BookServices bookService = new BookServices();
        double uspesnost;
        double zhoda = 0;        
        List<Pouzivatel> pouzivateliaList = userService.nacitajVsetkychPouzivatelov(em);
        double pocetPouzivatelov = pouzivateliaList.size();
        Pouzivatel userA;
        
        
        
        for (int i=0 ; i<pouzivateliaList.size() ; i++) {
            userA = pouzivateliaList.get(i);           
            if ( bookService.vyhodnotOdporuceneKnihy(userA.getKnihyList(), userA.getOdporuceneKnihy(), PERCENTO_POROVNAVANYCH_KNIH) ) {            
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
