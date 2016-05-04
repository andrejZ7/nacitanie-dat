package com.andrej.spracovaniedat;

import Jama.Matrix;
import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Mdt;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.spracovanienacitanychdat.PorovnanieKnih;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author andrej
 */
public class BookServices {
    
    private String upravDatumNaCislo(String datum) {
        if (StringUtils.isNumeric(datum)) {
            return datum;
        }
        else {
            String pattern = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
            String[] dateArray = datum.split(pattern);
            for (int i=0 ; i<dateArray.length ; i++) {
                if ( StringUtils.isNumeric(dateArray[i]) ) {
                    return dateArray[i];
                }
            }
        }
        return datum;
    }
    
    private double vypocitajSkalarnySucin(Matrix m1, Matrix m2) {
        int vectorLength = m1.getColumnDimension();
        double[][] v1 = m1.getArray();                
        double[][] v2 = m2.getArray();
        double sum = 0;

        for(int i=0 ; i<vectorLength ; i++){
            sum += v1[0][i] * v2[0][i];
        }
        return sum;
    }
    
    public double vypocitajKosinusVzdialenost(Matrix sourceDoc, Matrix targetDoc) {    
        double skalarnySucin = vypocitajSkalarnySucin(sourceDoc, targetDoc);
        double euklidovskaVzdialenost = sourceDoc.normF() * targetDoc.normF();
        if (euklidovskaVzdialenost == 0.0) {
            return 0.0;
        }
        else {
            return skalarnySucin / euklidovskaVzdialenost;
        }        
    }
    
    public List<Kniha> najdiSplocneKnihy(Pouzivatel userA, Pouzivatel userB) {
        List<Kniha> knihyA = userA.getKnihyList();
        List<Kniha> knihyB = userB.getKnihyList();        
        int pocetKnihA = knihyA.size();
        int pocetKnihB = knihyB.size();
        List<Kniha> spolocneKnihy = new ArrayList <Kniha>();
        
        for(int i=0 ; i<pocetKnihA ; i++) {
            for(int j=0 ; j<pocetKnihB ; j++) {
                if (Objects.equals(knihyA.get(i).getId(), knihyB.get(j).getId())) {
                    spolocneKnihy.add(knihyA.get(i));
                }
            }            
        }
        return spolocneKnihy;
    }
    
    private static int porovnajMdt(List<Mdt> mdtA , List<Mdt> mdtB) {
        int prienik = 0;
        for (int i=0 ; i<mdtA.size() ; i++) {
            for (int j=0 ; j<mdtB.size() ; j++) {
                if ( mdtA.get(i).getMdt().equals(mdtB.get(j).getMdt()) ) {
                    prienik++;
                }
            } 
        }
        return prienik;
    }
    
    public List<Matrix> vytvorenieVektorov(Kniha knihaA, Kniha knihaB) {
        double v1[][] = { {0,0,0,0,0} };
        double v2[][] = { {0,0,0,0,0} };
        List<Matrix> listMatic = new ArrayList<Matrix>();
        String upravenyDatum;
       
        if (knihaA.getDatum() != null) {
            upravenyDatum = upravDatumNaCislo(knihaA.getDatum());            
            knihaA.setDatum(upravenyDatum);
        }        
        
        if (knihaB.getDatum() != null) {
            upravenyDatum = upravDatumNaCislo(knihaB.getDatum());            
            knihaB.setDatum(upravenyDatum);
        }
        
        if (knihaA.getAutor() != null && knihaB.getAutor() != null){
            if (knihaA.getAutor().equals(knihaB.getAutor())) {
                v1[0][0] = 1;
                v2[0][0] = 1;
            }
            else {
                v1[0][0] = 0;
                v2[0][0] = 1;
            }
        }
        else {
            v1[0][0] = 0;
            v2[0][0] = 0;
        }
        
        if (knihaA.getDatum() != null && knihaB.getDatum() != null) {
            if (knihaA.getDatum().equals(knihaB.getDatum())) {
                v1[0][1] = 1;
                v2[0][1] = 1;
            }
            else {
                v1[0][1] = 0;
                v2[0][1] = 1;
            }
        }
        else {
            v1[0][1] = 0;
            v2[0][1] = 0;
        }
        
        if (knihaA.getKlucoveSlova() != null && knihaB.getKlucoveSlova() != null ) {
            if (knihaA.getKlucoveSlova().equals(knihaB.getKlucoveSlova())) {
                v1[0][2] = 1;
                v2[0][2] = 1;
            }
            else {
                v1[0][2] = 0;
                v2[0][2] = 1;
            }
        }
        else {
            v1[0][2] = 0;
            v2[0][2] = 0;
        }
        
        if (knihaA.getVydavatelstvo() != null && knihaB.getVydavatelstvo() != null) {
            if (knihaA.getVydavatelstvo().equals(knihaB.getVydavatelstvo())) {
                v1[0][3] = 1;
                v2[0][3] = 1;
            }
            else {
                v1[0][3] = 0;
                v2[0][3] = 1;
            }
        }
        else {
            v1[0][3] = 0;
            v2[0][3] = 0;            
        }
        
        if (knihaA.getMdtList() != null && knihaB.getMdtList() != null) {        
            int prienik = porovnajMdt(knihaA.getMdtList(), knihaB.getMdtList());
            if (prienik == 0) {
                v1[0][4] = 0;
                v2[0][4] = 1;
            }
            else if (prienik == 1) {
                v1[0][4] = 1;
                v2[0][4] = 1;
            }
            else if (prienik == 2) {
                v1[0][4] = 2;
                v2[0][4] = 2;
            }
            else if (prienik >= 3) {
                v1[0][4] = 3;
                v2[0][4] = 3;
            }
        }
        else {
            v1[0][4] = 0;
            v2[0][4] = 0;            
        }
        
        
        Matrix sourceDoc = new Matrix(v1);
        Matrix targetDoc = new Matrix(v2);
        
        listMatic.add(0, sourceDoc);
        listMatic.add(1, targetDoc);
        
        return listMatic;
    }
    
    public boolean jeSpolocna(List<Kniha> spolocneKnihy, Kniha kniha) {
        for (int i=0 ; i<spolocneKnihy.size() ; i++) {
            if (Objects.equals(kniha.getId(), spolocneKnihy.get(i).getId())) {
                return true;
            }
        }
        
        return false;
    }
    
    public static int vypocitajPocetPorovnavanychKnih(int pocetKnihPouzivatela, double percentoPorovnavanychKnih) {
        double pocetKnih = (double) pocetKnihPouzivatela;
        int pocetPorovnavanychKnih = (int) ((pocetKnih/100) * percentoPorovnavanychKnih);
        return pocetPorovnavanychKnih;
    }
    
    public boolean vyhodnotOdporuceneKnihy(List<Kniha> knihyPouzivatela, 
                                           List<Kniha> odporuceneKnihy, 
                                           double percentoPorovnavanychKnih) {          
        
        int pocetKnihPouzivatela = knihyPouzivatela.size();        
        int pocetKnihOdporucenych = odporuceneKnihy.size(); 
        
        int pocetKnihVDatasete = pocetKnihPouzivatela -
                                 vypocitajPocetPorovnavanychKnih(knihyPouzivatela.size(), percentoPorovnavanychKnih);
        
        
        for(int i=pocetKnihVDatasete ; i<pocetKnihPouzivatela ; i++) {
            for(int j=0 ; j<pocetKnihOdporucenych ; j++) {
                if (Objects.equals(knihyPouzivatela.get(i).getId(), odporuceneKnihy.get(j).getId())) {
                    return true;
                }
            }            
        }
        return false;
    }
    
}
