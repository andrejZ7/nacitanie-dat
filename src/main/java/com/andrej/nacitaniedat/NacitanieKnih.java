package com.andrej.nacitaniedat;

import com.andrej.nacitaniedat.model.KnihaPomocna;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author andre
 */
public class NacitanieKnih {
    
    private static String END_BOOK = "###";
    private static String ISBN_TAG = "020    a";
    
    public static void main(String [] args) throws FileNotFoundException, IOException {
        
        try (BufferedReader br = new BufferedReader(new FileReader("KlUsCat_more.txt"))) {
            String line;
            int count = 0;         
                      
            while ((line = br.readLine()) != null) {
                while (line.equals(END_BOOK)) {
                    KnihaPomocna knihaPom = new KnihaPomocna();
                    if (line.startsWith(ISBN_TAG)) {
                        String[] lineArray = line.split("a");                        
                        String isbn;
                        for (int i=0 ; i<lineArray[1].length() ; i++) {
                            
                        }
                        
                    }
                    count++;
                }
            }
           
        }

    
    }
}
