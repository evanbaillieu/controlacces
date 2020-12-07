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
import lml.snir.controleacces.metier.entity.Autorisation;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.metier.entity.Salle;
import lml.snir.controleacces.metier.entity.TimeSlot;
import lml.snir.controleacces.physique.data.AutorisationDataService;
import lml.snir.controleacces.physique.data.PersonneDataService;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;
import lml.snir.controleacces.physique.data.SalleDataService;
import lml.snir.controleacces.physique.data.TimeSlotDataService;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class AutorisationBean implements Serializable {

    private Autorisation selectedAuto;
    private List<Autorisation> autorisations;

    private final AutorisationDataService autoSrv = PhysiqueDataFactory.getAutorisationDataService();
    private final SalleDataService salleSrv = PhysiqueDataFactory.getSalleDataService();
    private final PersonneDataService persSrv = PhysiqueDataFactory.getPersonneDataService();
    private final TimeSlotDataService tsSrv = PhysiqueDataFactory.getTimeSlotService();

    private List<Personne> personnes;
    private List<Salle> salles;
    private List<TimeSlot> timeSlots;

    private long personne;
    private long salle;
    private long timeSlot;

    public long getPersonne() {
        return personne;
    }

    public void setPersonne(long personne) {
        this.personne = personne;
    }

    public void setSalle(long salle) {
        this.salle = salle;
    }

    public long getSalle() {
        return salle;
    }

    public Autorisation getSelectedAuto() {
        return selectedAuto;
    }

    public void setSelectedAuto(Autorisation selectedAuto) {
        this.selectedAuto = selectedAuto;
    }

    public AutorisationBean() {
        this.loadData();
    }

    private void loadData() {
        try {
            this.autorisations = this.autoSrv.getAll();
            this.personnes = this.persSrv.getAll();
            this.salles = this.salleSrv.getAll();
            this.timeSlots = this.tsSrv.getAll();

        } catch (Exception ex) {
            Logger.getLogger(AutorisationBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public long getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(long timeSlot) {
        this.timeSlot = timeSlot;
    }

    public List<Autorisation> getAutorisations() {
        return autorisations;
    }

    public void addEditAutorisation() {
        try {
            Personne p = this.persSrv.getById(personne);
            TimeSlot ts = this.tsSrv.getById(timeSlot);
            Salle s = this.salleSrv.getById(salle);
            if ((p != null) && (ts != null) && (s != null)) {
                Autorisation auto = new Autorisation();
                auto.setPersonne(p);
                auto.setPlageHoraire(ts);
                auto.setSalle(s);
                for (int i = 0; i < this.autorisations.size(); i++) {
                    if (auto.equals(this.autorisations.get(i))) {
                        try {
                            // L'Autorisation existe déjà : Modification
                            this.autoSrv.update(auto);
                            this.loadData();
                        } catch (Exception ex) {
                            Logger.getLogger(AutorisationBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            // L 'Autorisation n'existe pas : Création
                            this.autoSrv.add(auto);
                            this.loadData();
                            break;
                        } catch (Exception ex) {
                            Logger.getLogger(AutorisationBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AutorisationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeAutorisation(Autorisation autorisation) {
        if (autorisation != null) {
            try {
                this.autoSrv.remove(autorisation);
                this.loadData();
            } catch (Exception ex) {
                Logger.getLogger(AutorisationBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesMessage message = new FacesMessage("Erreur : Veuillez sélectionner une Autorisation !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
