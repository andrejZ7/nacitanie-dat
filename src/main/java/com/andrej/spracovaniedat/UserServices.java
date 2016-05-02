package com.andrej.spracovaniedat;

import com.andrej.nacitaniedat.model.Kniha;
import com.andrej.nacitaniedat.model.Pouzivatel;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    
    public List<Pouzivatel> nacitajVsetkychPouzivatelov(EntityManager em) {
        //EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
        CriteriaBuilder critBld = em.getCriteriaBuilder();		
        CriteriaQuery<Pouzivatel> query = critBld.createQuery(Pouzivatel.class);  
        Root<Pouzivatel> root = query.from(Pouzivatel.class);
        
        query.select(root);   
	Query qu = em.createQuery(query);
	try{
            return (List<Pouzivatel>) qu.getResultList();    
        }
        catch(javax.persistence.NoResultException e){			
            return null;			
        }
    }
    
}
