/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.controleacces.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lml.snir.controleacces.metier.entity.Attribution;
import lml.snir.controleacces.metier.entity.Badge;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.physique.data.AttributionDataService;
import lml.snir.controleacces.physique.data.BadgeDataService;
import lml.snir.controleacces.physique.data.PersonneDataService;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class AttributBean implements Serializable {

    private List<Attribution> attributions;
    private final AttributionDataService attrSrv = PhysiqueDataFactory.getAttributionDataService();
    private Attribution selectedAttr;

    private final PersonneDataService persSrv = PhysiqueDataFactory.getPersonneDataService();
    private final BadgeDataService badgeSrv = PhysiqueDataFactory.getBadgeDataService();

    private long personne;
    private long badge;

    private List<Personne> personnes;

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
    private List<Badge> badges;

    public long getPersonne() {
        return personne;
    }

    public void setPersonne(long personne) {
        this.personne = personne;
    }

    public long getBadge() {
        return badge;
    }

    public void setBadge(long badge) {
        this.badge = badge;
    }

    public Attribution getSelectedAttr() {
        return selectedAttr;
    }

    public void setSelectedAttr(Attribution selectedAttr) {
        this.selectedAttr = selectedAttr;
    }

    public AttributBean() {
        this.loadData();
    }

    private void loadData() {
        try {
            this.attributions = attrSrv.getAll();
            this.badges = badgeSrv.getAll();
            for (int i = 0; i < badges.size() - 1; i++) {
                if (badges.get(i).equals(attributions.get(i).getBadge())) {
                    badges.remove(attributions.get(i).getBadge());
                }
            }
            this.personnes = persSrv.getAll();
            for (int i = 0; i < personnes.size() - 1; i++) {
                if (personnes.get(i).equals(attributions.get(i).getPersonne())) {
                    personnes.remove(attributions.get(i).getPersonne());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AttributBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Attribution> getAttributions() {
        return attributions;
    }

    public void addEditAttribution() {
        boolean trouve = false;
        try {
            Personne p = this.persSrv.getById(personne);
            Badge b = this.badgeSrv.getById(badge);
            if ((p != null) && (b != null)) {
                Attribution attr = new Attribution();
                attr.setBadge(b);
                attr.setPersonne(p);
                for (int i = 0; i < this.attributions.size(); i++) {
                    if (p.equals(this.attributions.get(i).getPersonne())) {
                        trouve = true;
                        attr = this.attrSrv.getByPersonne(p);
                    }
                }
                if (trouve) {
                    this.attrSrv.update(attr);
                } else {
                    this.attrSrv.add(attr);
                }
                this.loadData();
            }
        } catch (Exception ex) {
            Logger.getLogger(AttributBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeAttr(Attribution attr) {
        if (attr != null) {
            try {
                this.attrSrv.remove(attr);
                this.loadData();
            } catch (Exception ex) {
                Logger.getLogger(AttributBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesMessage message = new FacesMessage("Erreur : Veuillez sélectionner une Attribution !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
