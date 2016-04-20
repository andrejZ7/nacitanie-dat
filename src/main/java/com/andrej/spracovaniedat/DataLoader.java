package com.andrej.spracovaniedat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andrej
 */
public class DataLoader {
    
    public String nacitajIsbn(String line) {       
        String[] lineArray = line.split("a");   //ISBN je vzdy v lineArray[1]
        String isbn = "";
        for (int i=0 ; i<lineArray[1].length() ; i++) { //prehladavam lineArray[1] kym najdem cele ISBN
            if ( (lineArray[1].charAt(i) >= '0' && 
                  lineArray[1].charAt(i) <= '9') ||
                  lineArray[1].charAt(i) == 'X' ||
                  lineArray[1].charAt(i) == '-'){
                isbn += lineArray[1].charAt(i);
            }
            else {
                break;  //ak nie je znak z ISBN tak break
            }
        }       
        return isbn;
    }
    
    public String nacitajAutora(String line) {
        Pattern p = Pattern.compile("\\*", Pattern.CASE_INSENSITIVE);
        Pattern p2 = Pattern.compile("\\*p", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(line);
        Matcher m2 = p2.matcher(line);
        boolean obsahujeHviezdicku = m.find();
        boolean obsahujeHviezdickuAjP = m2.find();
        if (obsahujeHviezdickuAjP) {        //zistujem ci je pred ideckom *p alebo iba hviezdicka
            String[] lineArray = line.split("\\*p");
            String autor = lineArray[1].substring(0, 7);
            return autor;
        }
        else if (obsahujeHviezdicku) {
            String[] lineArray = line.split("\\*");
            String autor = lineArray[1].substring(0, 7);
            return autor; 
        }
        return "";
        
    }
    
    public String nacitajVydavatelstvo(String line) {
        String[] lineArray = line.split("b");
        if (lineArray.length >= 2) {    //zistit ci sa tam vobec vydavatelstvo nachadza            
            String[] temp = lineArray[1].split(",");
            String vydavatelstvo = temp[0];
            return vydavatelstvo;
        }
        else {
            return "";
        }
    }
    
    public String nacitajMdt(String line) {
        String[] lineArray = line.split("a");
        String[] mdt = lineArray[1].split("");
        return mdt[0];
    }
    
    public String nacitajMdtCosm(String line) {
        String[] lineArray = line.split("a");                    
        return lineArray[1];        
    }
    
    public String nacitajDatum(String line) {      
        String[] lineArray = line.split("c");
        if (lineArray.length >= 2){
            return lineArray[1];
        }
        return "";
    }
    
    public String nacitajKlucoveSlova(String line) {
        String[] lineArray = line.split("a");
        String[] returnedWords = lineArray[1].split("-");
        return returnedWords[0];
    }
    
    public String nacitajKlucoveSlovaIne(String line) {
        String[] lineArray = line.split("a");        
        return lineArray[1];
    }
    
    public String nacitajKatId(String line) {        
        String[] lineArray = line.split(" c");
        return lineArray[1];
    }
    
}
