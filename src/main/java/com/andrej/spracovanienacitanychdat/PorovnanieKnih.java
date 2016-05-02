package com.andrej.spracovanienacitanychdat;

import Jama.Matrix;
import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.spracovaniedat.BookServices;
import com.andrej.spracovaniedat.DataLoader;
import com.andrej.spracovaniedat.UserServices;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author andrej
 */
public class PorovnanieKnih {
       
    public static void main(String [] args) {
        DataLoader dataLoader = new DataLoader();
        UserServices userService = new UserServices();
        BookServices bookService = new BookServices();
        List<Pouzivatel> pouzivateliaList = dataLoader.nacitajVsetkychPouzivatelov();
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
                   
        
        for (int i=0 ; i<pouzivateliaList.size() ; i++) {
            userA = pouzivateliaList.get(i);
            userB = userA.getNajblizsiPouzivatel();
            spolocneKnihy = bookService.najdiSplocneKnihy(userA, userB);
            pocetSpKnih = spolocneKnihy.size();
            pocetKnihA = userA.getKnihyList().size();
            pocetKnihB = userB.getKnihyList().size();
            System.out.println("Pouzivatel: " + userA.getId() + " ///////////////////////////////////////////////////////////////////");
            for (int k=0 ; k<pocetKnihA ; k++) {
                for (int l=0 ; l<pocetKnihB ; l++){
                    if (!bookService.jeSpolocna(spolocneKnihy, userA.getKnihyList().get(k))) {
                        listMatic = bookService.vytvorenieVektorov(userA.getKnihyList().get(k), 
                                                                   userB.getKnihyList().get(l));
                        sourceDoc = listMatic.get(0);
                        targetDoc = listMatic.get(1);
                        podobnostKnih = bookService.vypocitajKosinusVzdialenost(sourceDoc, targetDoc);
                        System.out.println("Podobnost knihy cislo " + k + ". " + 
                                           userA.getKnihyList().get(k).getId() + 
                                           " a knihy cislo " + l + ". " + 
                                           userB.getKnihyList().get(l).getId() +
                                           " je *** " + podobnostKnih);
                    }                    
                }               
            }
        }
                    
       
    }
}
