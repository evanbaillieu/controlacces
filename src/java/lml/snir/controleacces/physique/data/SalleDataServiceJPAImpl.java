/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.Salle;

/**
 *
 * @author saturne
 */
class SalleDataServiceJPAImpl extends AbstracCrudServiceJPA<Salle> implements SalleDataService {

    public SalleDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Salle> getByProtege(boolean protege) throws Exception {
        List<Salle> salles = null;
        try{
            this.open();
            Query query = em.createQuery("SELECT m FROM Salle m WHERE m.protege = :fprotege");
            query.setParameter("fprotege", protege);
            salles = query.getResultList();
        } finally {
            this.close();
        }
        return salles;
    }

}
