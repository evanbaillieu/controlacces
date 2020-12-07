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
import lml.snir.controleacces.metier.entity.Badge;
import lml.snir.controleacces.physique.data.BadgeDataService;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class BadgeBean implements Serializable{
    
    private List<Badge> badges;
    private final BadgeDataService badgeSrv = PhysiqueDataFactory.getBadgeDataService();
    private Badge selectedBadge;
    private String contenu;

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Badge getSelectedBadge() {
        return selectedBadge;
    }

    public void setSelectedBadge(Badge selectedBadge) {
        this.selectedBadge = selectedBadge;
    }

    public BadgeBean() {
        this.loadData();
    }
    
    private void loadData() {
        try {
            this.badges = this.badgeSrv.getAll();
        } catch (Exception ex) {
            Logger.getLogger(BadgeBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Badge> getBadges() {
        return badges;
    }
    
    public void addBadge(String contenu) {
        if(!"".equals(contenu)){
            Badge b = new Badge();
            b.setContenu(contenu);
            for (int i = 0; i < this.badges.size(); i++) {
                if(b.equals(this.badges.get(i))){
                    try {
                        //Le badge existe : Modification
                        this.badgeSrv.update(b);
                        this.loadData();
                    } catch (Exception ex) {
                        Logger.getLogger(BadgeBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        //Le badge n'existe pas : Création
                        this.badgeSrv.add(b);
                        this.loadData();
                        break;
                    } catch (Exception ex) {
                        Logger.getLogger(BadgeBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public void removeBadge(Badge b){
        if(b != null){
            try {
                this.badgeSrv.remove(b);
                this.loadData();
            } catch (Exception ex) {
                Logger.getLogger(BadgeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            FacesMessage message = new FacesMessage("Erreur : Vous avez oublié de sélectionner un Badge !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
