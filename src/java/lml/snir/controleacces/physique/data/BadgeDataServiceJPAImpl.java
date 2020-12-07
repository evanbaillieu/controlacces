/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.Badge;

/**
 *
 * @author saturne
 */
class BadgeDataServiceJPAImpl extends AbstracCrudServiceJPA<Badge> implements BadgeDataService {

    public BadgeDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public Badge getByContenu(String contenu) throws Exception {
        Badge badge = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Badge m WHERE m.contenu = :fcontenu");
            query.setParameter("fcontenu", contenu);
            badge = (Badge) query.getSingleResult();
        } finally {
            this.close();
        }
        return badge;
    }
    
}
