/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.Attribution;
import lml.snir.controleacces.metier.entity.Badge;
import lml.snir.controleacces.metier.entity.Personne;

/**
 *
 * @author saturne
 */
class AttributionDataServiceJPAImpl extends AbstracCrudServiceJPA<Attribution> implements AttributionDataService {

    public AttributionDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public Attribution getByBadge(Badge badge) throws Exception {
        Attribution attr = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Attribution m WHERE m.badge = :fbadge");
            query.setParameter("fbadge", badge);
            attr = (Attribution) query.getSingleResult();
        } finally {
            this.close();
        }
        return attr;
    }

    @Override
    public Attribution getByPersonne(Personne personne) throws Exception {
        Attribution attr = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Attribution m WHERE m.personne = :fpersonne");
            query.setParameter("fpersonne", personne);
            attr = (Attribution) query.getSingleResult();
        } finally {
            this.close();
        }
        return attr;
    }
    
}
