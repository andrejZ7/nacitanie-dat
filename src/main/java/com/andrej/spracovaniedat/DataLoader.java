package com.andrej.spracovaniedat;

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
}
