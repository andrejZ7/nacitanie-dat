package com.andrej.spracovanienacitanychdat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.spracovaniedat.DataLoader;
import com.andrej.spracovaniedat.UserServices;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andrej
 */
public class PorovnaniePouzivatelov {
    
    public static void main(String [] args) {
        DataLoader dataLoader = new DataLoader();
        UserServices userService = new UserServices();
        List<Pouzivatel> pouzivateliaList = dataLoader.nacitajVsetkychPouzivatelov();
        int pocitadlo = 0;
        Pouzivatel userA;
        Pouzivatel userB;
        int vsetciPouzivatelia = pouzivateliaList.size();
        
        for(int i=0 ; i<vsetciPouzivatelia ; i++){
            System.out.println(pocitadlo + ". *****************************************************************");
            pocitadlo++;
            for(int j=0 ; j<vsetciPouzivatelia ; j++){                
                userA = pouzivateliaList.get(i);
                userB = pouzivateliaList.get(j);
                if(i != j){
                    userService.porovnajPouzivatelov(userA, userB, j);
                }
            }
        }
        
        
       
    }
}
