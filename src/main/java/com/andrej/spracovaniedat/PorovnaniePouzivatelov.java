package com.andrej.spracovaniedat;

import com.andrej.databaza.PersistenceManager;
import com.andrej.nacitaniedat.model.Pouzivatel;
import com.andrej.sluzby.PodobnyPouzivatel;
import com.andrej.sluzby.UserServices;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author andrej
 */
public class PorovnaniePouzivatelov {
    
    private static double PERCENTO_POROVNAVANYCH_KNIH = 80;
    
    public static void main(String [] args) {
        
        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        UserServices userService = new UserServices();
        List<Pouzivatel> pouzivateliaList = userService.nacitajVsetkychPouzivatelov(em);
        int pocitadlo = 0;
        Pouzivatel userA = null;
        Pouzivatel userB = null;
        int vsetciPouzivatelia = pouzivateliaList.size();
        PodobnyPouzivatel podobnost = new PodobnyPouzivatel();
        double maxPodobnost = 0;
        double aktualnaPodobnost;
        Integer maxPodobnyPouzivatelId = 0;
        Pouzivatel podobniPouzivatel = new Pouzivatel();
        
        for(int i=0 ; i<vsetciPouzivatelia ; i++){
            System.out.println(pocitadlo + ". *****************************************************************");
            pocitadlo++;
            maxPodobnost = 0;
            userA = pouzivateliaList.get(i);
            for(int j=0 ; j<vsetciPouzivatelia ; j++){ 
                userB = pouzivateliaList.get(j);
                if(i != j){
                    podobnost = userService.porovnajDvochPouzivatelov(userA, userB, PERCENTO_POROVNAVANYCH_KNIH);                    
                }
                aktualnaPodobnost = podobnost.getPodobnost();
                if (aktualnaPodobnost > maxPodobnost) {
                    maxPodobnost = aktualnaPodobnost;
                    maxPodobnyPouzivatelId = podobnost.getPouzivatel().getId();
                    podobniPouzivatel = podobnost.getPouzivatel();
                }
            }
            /*System.out.println("Najpodobnejsi pouzivatel k pouzivatelovi " + userA.getId() + 
                               " je " + maxPodobnyPouzivatelId + " *** Podobnost: " +maxPodobnost +
                               "/////////////////////////// " + podobniPouzivatel.getId());*/
            
            userA.setNajblizsiPouzivatel(podobniPouzivatel);
            podobniPouzivatel.getPodobniPouzivatelia().add(userA);
            
            em.getTransaction()
              .begin();
            em.merge(userA);
            em.merge(podobniPouzivatel);
            em.flush();                                                        
            em.getTransaction()
              .commit();            
        }
        
        em.close();
        PersistenceManager.INSTANCE.close();
       
    }
}
