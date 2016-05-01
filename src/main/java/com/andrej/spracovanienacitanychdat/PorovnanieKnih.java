package com.andrej.spracovanienacitanychdat;

import Jama.Matrix;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.spracovaniedat.BookServices;
import com.andrej.spracovaniedat.DataLoader;
import com.andrej.spracovaniedat.UserServices;
import java.util.List;


/**
 *
 * @author andrej
 */
public class PorovnanieKnih {
    
    public static void main(String [] args) {
        DataLoader dataLoader = new DataLoader();
        UserServices userService = new UserServices();
        List<Pouzivatel> pouzivateliaList = dataLoader.nacitajVsetkychPouzivatelov();
        Pouzivatel userA = null;
        Pouzivatel userB = null;
        double podobnostKnih;
        
        double m[][] = { {1,0,1,1,1,0,0,5.6} };
        double n[][] = { {1,1,1,1,0,1,0,4.8} };
        
        BookServices bookService = new BookServices();
        Matrix sourceDoc = new Matrix(m);
        Matrix targetDoc = new Matrix(n);
        podobnostKnih = bookService.vypocitajKosinusVzdialenost(sourceDoc, targetDoc);
        
    }
}
