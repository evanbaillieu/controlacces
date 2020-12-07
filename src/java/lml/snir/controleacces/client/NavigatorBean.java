/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class NavigatorBean implements Serializable {
    private String content;

    public void setContent(String content) {
        this.content = content;       
    }
    
    private final Map<MainMenu, String> nav = new HashMap();
    
    private enum MainMenu {
        Home, Autorisation, Attribution, Badge, Personne, PlageHoraire, Salle, Evenement
    };
    
    public NavigatorBean() {
        this.nav.put(MainMenu.Home, "/home.xhtml");
        this.nav.put(MainMenu.Personne, "/user/userService.xhtml");
        this.nav.put(MainMenu.Autorisation, "/autorisation/autorisationService.xhtml");
        this.nav.put(MainMenu.Badge, "/badge/badgeService.xhtml");
        this.nav.put(MainMenu.Salle, "/salle/salleService.xhtml");
        this.nav.put(MainMenu.PlageHoraire, "/plageHoraire/timeSlotService.xhtml");
        this.nav.put(MainMenu.Attribution, "/attribution/attributionService.xhtml");
        this.nav.put(MainMenu.Evenement, "/evenement/eventService.xhtml");
        this.content = this.nav.get(MainMenu.Home);
    }
  
    public String getContent(){
        return this.content;
    }
    
    public void selectHome() {
        this.content = this.nav.get(MainMenu.Home);
        addMessage("Succès", "Home sélectionné");
    }
    
    public void selectPersonneView(){
        this.content = this.nav.get(MainMenu.Personne);
        addMessage("Succès", "Personnes sélectionnés");
    }
    
    public void selectAutorisationView(){
        this.content = this.nav.get(MainMenu.Autorisation);
        addMessage("Succès", "Autorisations sélectionnées");
    }
    
    public void selectAttributionView(){
        this.content = this.nav.get(MainMenu.Attribution);
        addMessage("Succès", "Attributions sélectionnées");
    }
    
    public void selectBadgeView(){
        this.content = this.nav.get(MainMenu.Badge);
        addMessage("Succès", "Badges sélectionnés");
    }
    
    public void selectSalleView(){
        this.content = this.nav.get(MainMenu.Salle);
        addMessage("Succès", "Salles sélectionnées");
    }
    
    public void selectTimeSlotView(){
        this.content = this.nav.get(MainMenu.PlageHoraire);
        addMessage("Succès", "TimeSlots sélectionnées");
    }
    
    public void selectEvenementView(){
        this.content = this.nav.get(MainMenu.Evenement);
        addMessage("Succès", "Evenements sélectionnés");
    }
    
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(summary + " : " + detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
