package com.andrej.sluzby;

import java.util.Comparator;

/**
 *
 * @author andrej
 */
public class CustomComparator  implements Comparator<PodobneKnihy> {

    @Override
    public int compare(PodobneKnihy o1, PodobneKnihy o2) {
        if (o1.getPodobnost() > o2.getPodobnost()) return -1;
        if (o1.getPodobnost() < o2.getPodobnost()) return 1;
        return 0;
    }
    
    
    
}
