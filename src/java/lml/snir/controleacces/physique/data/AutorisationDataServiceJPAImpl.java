/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.Autorisation;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.metier.entity.Salle;
import lml.snir.controleacces.metier.entity.TimeSlot;

/**
 *
 * @author saturne
 */
class AutorisationDataServiceJPAImpl extends AbstracCrudServiceJPA<Autorisation> implements AutorisationDataService {

    public AutorisationDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public List<Autorisation> getBySalle(Salle salle) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.salle = :fsalle");
            query.setParameter("fsalle", salle);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getByPersonne(Personne personne) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.personne = :fpersonne");
            query.setParameter("fpersonne", personne);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getByPlageHoraire(TimeSlot plageHoraire) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.plageHoraire = :fts");
            query.setParameter("fts", plageHoraire);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    @Override
    public List<Autorisation> getByPeronneEtSalle(Personne personne, Salle salle) throws Exception {
        List<Autorisation> autorisations = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Autorisation m WHERE m.personne = :fpersonne AND m.salle = :fsalle");
            query.setParameter("fpersonne", personne);
            query.setParameter("fsalle", salle);
            autorisations = query.getResultList();
        } finally {
            this.close();
        }
        return autorisations;
    }

    
}
