package com.andrej.spracovaniedat;

import Jama.Matrix;
import com.andrej.databaza.PersistenceManager;
import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.sluzby.BookServices;
import com.andrej.sluzby.CustomComparator;
import com.andrej.sluzby.PodobneKnihy;
import com.andrej.sluzby.UserServices;
import com.andrej.sluzby.PocetKnih;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author andrej
 */
public class PorovnanieKnih {
       
    public static void main(String [] args) {
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();        
        UserServices userService = new UserServices();
        BookServices bookService = new BookServices();
        PocetKnih pocetKnih = new PocetKnih();
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
        int pocetPorovnavanychKnihA;
        int pocetPorovnavanychKnihB;
        List<Kniha> odporuceneKnihy = new ArrayList<Kniha>();                           
        
        for (int i=0 ; i<pouzivateliaList.size() ; i++) {
            List<PodobneKnihy> dvojiceKnihList = new ArrayList<PodobneKnihy>();
            userA = pouzivateliaList.get(i);
            userB = userA.getNajblizsiPouzivatel();
            spolocneKnihy = bookService.najdiSplocneKnihy(userA, userB);
            pocetSpKnih = spolocneKnihy.size();
            pocetKnihA = userA.getKnihyList().size();
            pocetKnihB = userB.getKnihyList().size();
            pocetPorovnavanychKnihA = bookService.vypocitajPocetPorovnavanychKnih(pocetKnihA, pocetKnih.getPercentoPorovnavanychKnih());
            pocetPorovnavanychKnihB = bookService.vypocitajPocetPorovnavanychKnih(pocetKnihB, pocetKnih.getPercentoPorovnavanychKnih());
            System.out.println("Pouzivatel c." + i + " id: " + userA.getId());
            for (int k=0 ; k<pocetPorovnavanychKnihA ; k++) {
                for (int l=0 ; l<pocetPorovnavanychKnihB ; l++){
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
                        
                    }                    
                }                
            }            
            Collections.sort(dvojiceKnihList, new CustomComparator());
                        
            for (int x=0 ; x<pocetKnih.getPocetOdporucenychKnih() ; x++) {
                odporuceneKnihy.add(dvojiceKnihList.get(x).getKnihaUserB());
            }            
            
            userA.setOdporuceneKnihy(odporuceneKnihy);
            
            for (int x=0 ; x<pocetKnih.getPocetOdporucenychKnih() ; x++) {
                dvojiceKnihList.get(x).getKnihaUserB().getOdporuceniPouzivatelia().add(userA);
            }      
            
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
                                    
            odporuceneKnihy = new ArrayList<Kniha>();           
        }   
        
        em.close();
        PersistenceManager.INSTANCE.close();
    }
}
