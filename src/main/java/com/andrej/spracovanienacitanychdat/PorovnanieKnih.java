package com.andrej.spracovanienacitanychdat;

import Jama.Matrix;
import com.andrej.nacitaniedat.PersistenceManager;
import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.spracovaniedat.BookServices;
import com.andrej.spracovaniedat.CustomComparator;
import com.andrej.spracovaniedat.DataLoader;
import com.andrej.spracovaniedat.PodobneKnihy;
import com.andrej.spracovaniedat.UserServices;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;


/**
 *
 * @author andrej
 */
public class PorovnanieKnih {
       
    public static void main(String [] args) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();               
        DataLoader dataLoader = new DataLoader();
        UserServices userService = new UserServices();
        BookServices bookService = new BookServices();
        List<Pouzivatel> pouzivateliaList = userService.nacitajVsetkychPouzivatelov(em);
        Pouzivatel userA;
        Pouzivatel userB;  
        double podobnostKnih;
        List<Matrix> listMatic;
        double v1[][] = { {0,0,0,0} };
        double v2[][] = { {0,0,0,0} };
        List<Kniha> spolocneKnihy;
        int pocetKnihA;
        int pocetKnihB;
        int pocetSpKnih;        
        Matrix sourceDoc;
        Matrix targetDoc;
        PodobneKnihy dvojicaKnih = new PodobneKnihy();
        
        
        
        List<Kniha> odporuceneKnihy = new ArrayList<Kniha>();
        int uspesnost = 0;
                   
        
        for (int i=0 ; i<pouzivateliaList.size() ; i++) {
            List<PodobneKnihy> dvojiceKnihList = new ArrayList<PodobneKnihy>();
            userA = pouzivateliaList.get(i);
            userB = userA.getNajblizsiPouzivatel();
            spolocneKnihy = bookService.najdiSplocneKnihy(userA, userB);
            pocetSpKnih = spolocneKnihy.size();
            pocetKnihA = userA.getKnihyList().size();
            pocetKnihB = userB.getKnihyList().size();
            System.out.println("Pouzivatel: " + userA.getId() + " //////////////////////////////////////////////////////////////////////////////////////////////");
            for (int k=0 ; k<pocetKnihA ; k++) {
                for (int l=0 ; l<pocetKnihB ; l++){
                    if (!bookService.jeSpolocna(spolocneKnihy, userA.getKnihyList().get(k))) {
                        listMatic = bookService.vytvorenieVektorov(userA.getKnihyList().get(k), 
                                                                   userB.getKnihyList().get(l));
                        sourceDoc = listMatic.get(0);
                        targetDoc = listMatic.get(1);
                        podobnostKnih = bookService.vypocitajKosinusVzdialenost(sourceDoc, targetDoc);
                        dvojicaKnih.setKnihaUserA(userA.getKnihyList().get(k));
                        dvojicaKnih.setKnihaUserB(userB.getKnihyList().get(l));
                        dvojicaKnih.setPodobnost(podobnostKnih);
                        dvojiceKnihList.add(dvojicaKnih);
                        dvojicaKnih = new PodobneKnihy();                        
                        /*System.out.println("Podobnost knihy cislo " + k + ". " + 
                                           userA.getKnihyList().get(k).getId() + 
                                           " a knihy cislo " + l + ". " + 
                                           userB.getKnihyList().get(l).getId() +
                                           " je *** " + podobnostKnih);*/
                    }                    
                }                
            }            
            Collections.sort(dvojiceKnihList, new CustomComparator());
            System.out.print("Spolocne knihy: ");
            for (Kniha kniha : spolocneKnihy) {
                System.out.print(kniha.getId() + ", ");
            }
            System.out.println("\nOdporucene knihy: " + 
                                      dvojiceKnihList.get(0).getKnihaUserB().getId() +
                               ", " + dvojiceKnihList.get(1).getKnihaUserB().getId() + 
                               ", " + dvojiceKnihList.get(2).getKnihaUserB().getId() + 
                               ", " + dvojiceKnihList.get(3).getKnihaUserB().getId() + 
                               ", " + dvojiceKnihList.get(4).getKnihaUserB().getId());
            
            odporuceneKnihy.add(dvojiceKnihList.get(0).getKnihaUserB());
            odporuceneKnihy.add(dvojiceKnihList.get(1).getKnihaUserB());
            odporuceneKnihy.add(dvojiceKnihList.get(2).getKnihaUserB());
            odporuceneKnihy.add(dvojiceKnihList.get(3).getKnihaUserB());
            odporuceneKnihy.add(dvojiceKnihList.get(4).getKnihaUserB());
            
            userA.setOdporuceneKnihy(odporuceneKnihy);
            dvojiceKnihList.get(0).getKnihaUserB().getOdporuceniPouzivatelia().add(userA);
            dvojiceKnihList.get(1).getKnihaUserB().getOdporuceniPouzivatelia().add(userA);
            dvojiceKnihList.get(2).getKnihaUserB().getOdporuceniPouzivatelia().add(userA);
            dvojiceKnihList.get(3).getKnihaUserB().getOdporuceniPouzivatelia().add(userA);
            dvojiceKnihList.get(4).getKnihaUserB().getOdporuceniPouzivatelia().add(userA);
            
            em.getTransaction()
              .begin();                 
          
              em.merge(userA);
              em.merge(dvojiceKnihList.get(0).getKnihaUserB());
              em.merge(dvojiceKnihList.get(1).getKnihaUserB());
              em.merge(dvojiceKnihList.get(2).getKnihaUserB());
              em.merge(dvojiceKnihList.get(3).getKnihaUserB());
              em.merge(dvojiceKnihList.get(4).getKnihaUserB());
              em.flush();
                                               
            em.getTransaction()
              .commit();
            
            if ( bookService.vyhodnotOdporuceneKnihy(userA.getKnihyList(), odporuceneKnihy) ) {
                System.out.println("OUU YEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH");
                uspesnost++;
            }
            System.out.println("");
            System.out.println("");
        }
                    
        System.out.println("Uspesnost: " +uspesnost);
        
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
