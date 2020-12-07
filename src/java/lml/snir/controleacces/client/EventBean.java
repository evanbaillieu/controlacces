/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.controleacces.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lml.snir.controleacces.metier.entity.Evenement;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.metier.entity.Salle;
import lml.snir.controleacces.physique.data.EvenementDataService;
import lml.snir.controleacces.physique.data.PersonneDataService;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;
import lml.snir.controleacces.physique.data.SalleDataService;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class EventBean implements Serializable {

    private List<Evenement> events;
    private final EvenementDataService eventSrv = PhysiqueDataFactory.getEvenementDataService();
    private Evenement selectedEvent;

    private final PersonneDataService persSrv = PhysiqueDataFactory.getPersonneDataService();
    private List<Personne> personnes;
    private long personne;

    private final SalleDataService salleSrv = PhysiqueDataFactory.getSalleDataService();
    private List<Salle> salles;
    private long salle;

    private Date date;

    private boolean autorise;

    public boolean isAutorise() {
        return autorise;
    }

    public void setAutorise(boolean autorise) {
        this.autorise = autorise;
    }

    public long getPersonne() {
        return personne;
    }

    public void setPersonne(long personne) {
        this.personne = personne;
    }

    public long getSalle() {
        return salle;
    }

    public void setSalle(long salle) {
        this.salle = salle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Evenement> getEvents() {
        return events;
    }

    public void setEvents(List<Evenement> events) {
        this.events = events;
    }

    public Evenement getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Evenement selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public EventBean() {
        this.loadData();
    }
    
    private void loadData() {
        try {
            this.personnes = this.persSrv.getAll();
            this.salles = this.salleSrv.getAll();
            this.events = this.eventSrv.getAll();
        } catch (Exception ex) {
            Logger.getLogger(EventBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public List<Salle> getSalles() {
        return salles;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    public String isAutorise(boolean autorise) {
        if (autorise) {
            return "Autorisé";
        } else {
            return "Non Autorisé";
        }
    }

    public void addEditEvent() {
        try {
            Personne p = this.persSrv.getById(personne);
            Salle s = this.salleSrv.getById(salle);
            if ((p != null) && (s != null) && (date != null)) {
                Evenement event = new Evenement();
                event.setPersonne(p);
                event.setSalle(s);
                event.setDate(date);
                event.setAutorise(autorise);
                for (int i = 0; i < this.events.size(); i++) {
                    if (event.equals(this.events.get(i))) {
                        // L'event existe : Modification
                        this.eventSrv.update(event);
                        this.loadData();
                        break;
                    } else {
                        // L'event n'existe pas : Création
                        this.eventSrv.add(event);
                        this.loadData();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EventBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeEvent(Evenement event) {
        if (event != null) {
            try {
                this.eventSrv.remove(event);
                this.loadData();
            } catch (Exception ex) {
                Logger.getLogger(BadgeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesMessage message = new FacesMessage("Erreur : Vous avez oublié de sélectionner un Evenement !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
