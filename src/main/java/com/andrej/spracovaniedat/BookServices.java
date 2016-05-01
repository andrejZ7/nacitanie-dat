package com.andrej.spracovaniedat;

import Jama.Matrix;

/**
 *
 * @author andrej
 */
public class BookServices {
    
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
}
