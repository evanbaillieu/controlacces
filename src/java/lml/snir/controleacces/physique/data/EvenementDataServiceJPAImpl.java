/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.Evenement;
import lml.snir.controleacces.metier.entity.Salle;

/**
 *
 * @author saturne
 */
class EvenementDataServiceJPAImpl extends AbstracCrudServiceJPA<Evenement> implements EvenementDataService {

    public EvenementDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Evenement> getByJour(Date jour) throws Exception {
        List<Evenement> events = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Evenement m WHERE m.date = :fdate");
            query.setParameter("fdate", jour);
            events = query.getResultList();
        } finally {
            this.close();
        }
        return events;
    }

    @Override
    public List<Evenement> getBySalle(Salle salle) throws Exception {
        List<Evenement> events = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Evenement m WHERE m.salle = :fsalle");
            query.setParameter("fsalle", salle);
            events = query.getResultList();
        } finally {
            this.close();
        }
        return events;
    }

   
}
