package com.andrej.spracovaniedat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author andrej
 */
public class UserServices {
    public PodobnyPouzivatel porovnajPouzivatelov(Pouzivatel userA, Pouzivatel userB, int poradie){
        int pocetKnihA = userA.getKnihyList().size();
        int pocetKnihB = userB.getKnihyList().size();
        List<Kniha> knihyA =  userA.getKnihyList();
        List<Kniha> knihyB =  userB.getKnihyList();
        double zjednotenie = pocetKnihA + pocetKnihB;
        double prienik = 0;
        int pocitadlo = 0;        
        
        for(int i=0 ; i<pocetKnihA ; i++){            
            for(int j=0 ; j<pocetKnihB ; j++){
                pocitadlo++;
                if (Objects.equals(knihyA.get(i).getId(), knihyB.get(j).getId())){
                    prienik++;
                    System.out.println("ZHODA ###############################################################");
                }
            }
        }
        double jaccardovaPodobnost = prienik / (zjednotenie - prienik);
        System.out.println(pocitadlo + " porovnani, " + poradie+ ". Podobnost " + "Prienik: " +prienik+ "     " +userA.getId()+ "/" +userB.getId() + " = " + jaccardovaPodobnost);
        
        PodobnyPouzivatel podobnost = new PodobnyPouzivatel();
        podobnost.setPouzivatel(userB);
        podobnost.setPodobnost(jaccardovaPodobnost);
        return podobnost;
    }
}
