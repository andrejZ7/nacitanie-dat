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
        int pocetSpKnih;        
        Matrix sourceDoc;
        Matrix targetDoc;
                   
        
        for (int i=0 ; i<pouzivateliaList.size() ; i++) {
            userA = pouzivateliaList.get(i);
            userB = userA.getNajblizsiPouzivatel();
            spolocneKnihy = bookService.najdiSplocneKnihy(userA, userB);
            pocetSpKnih = spolocneKnihy.size();
            pocetKnihA = userA.getKnihyList().size();
            System.out.println("Pouzivatel: " + userA.getId() + " **********************************");
            for (int k=0 ; k<pocetSpKnih ; k++) {
                for (int l=0 ; l<pocetKnihA ; l++){
                    //porovnanie ci su knihy rozne
                    if ( !Objects.equals(spolocneKnihy.get(k).getId(), userA.getKnihyList().get(l).getId()) ) {
                        listMatic = bookService.vytvorenieVektorov(spolocneKnihy.get(k), userA.getKnihyList().get(l));
                        sourceDoc = listMatic.get(0);
                        targetDoc = listMatic.get(1);
                        podobnostKnih = bookService.vypocitajKosinusVzdialenost(sourceDoc, targetDoc);
                        System.out.println("Podobnost knihy cislo " + k + ". " + spolocneKnihy.get(k).getId() + 
                                       " a knihy cislo " + l + ". " + userA.getKnihyList().get(l).getId() +
                                       " je *** " + podobnostKnih);
                    }
                    
                }               
            }
        }
                    
       
    }
}
