/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.physique.data;

import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import lml.snir.controleacces.metier.entity.Administrateur;
import lml.snir.controleacces.metier.entity.Personne;

/**
 *
 * @author saturne
 */
class PersonneDataServiceJPAImpl extends AbstracCrudServiceJPA<Personne> implements PersonneDataService {

    public PersonneDataServiceJPAImpl(String PU) {
        super(PU);
    }

    @Override
    public Administrateur getByLogin(String login) throws Exception {
        Administrateur a = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Personne m WHERE m.login = :flogin");
            query.setParameter("flogin", login);
            a = (Administrateur) query.getSingleResult();
        } finally {
            this.close();
        }
        return a;
    }

    @Override
    public List<Personne> getByNom(String nom) throws Exception {
        List<Personne> personnes = null;
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Personne m WHERE m.nom = :fnom ORDER BY m.id ASC");
            query.setParameter("fnom", nom);
            personnes = query.getResultList();
        } finally {
            this.close();
        }
        return personnes;
    }

    
}
