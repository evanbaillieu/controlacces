/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.controleacces.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lml.snir.controleacces.metier.entity.Day;
import lml.snir.controleacces.metier.entity.TimeSlot;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;
import lml.snir.controleacces.physique.data.TimeSlotDataService;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class timeSlotBean implements Serializable {

    private List<TimeSlot> timeSlots;
    private final TimeSlotDataService tsSrv = PhysiqueDataFactory.getTimeSlotService();
    private TimeSlot selectegTs;

    private final List<Day> joursSemaine = Arrays.asList(Day.values());
    private Day beginDay;
    private Day endDay;
    private int beginHour;
    private int beginMinutes;
    private int endHour;
    private int endMinutes;

    public int getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(int beginHour) {
        this.beginHour = beginHour;
    }

    public int getBeginMinutes() {
        return beginMinutes;
    }

    public void setBeginMinutes(int beginMinutes) {
        this.beginMinutes = beginMinutes;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public void setEndMinutes(int endMinutes) {
        this.endMinutes = endMinutes;
    }

    public Day getEndDay() {
        return endDay;
    }

    public void setEndDay(Day endDay) {
        this.endDay = endDay;
    }

    public Day getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Day beginDay) {
        this.beginDay = beginDay;
    }

    public List<Day> getJoursSemaine() {
        return joursSemaine;
    }

    public void addEditPlageHoraire() {
        if ((beginDay != null) && (beginHour != 0) && (beginMinutes != 0) && (endDay != null) && (endHour != 0) && (endMinutes != 0)) {
            TimeSlot ts = new TimeSlot(beginDay, beginHour, beginMinutes, endDay, endHour, endMinutes);
            for (int i = 0; i < this.timeSlots.size(); i++) {
                if (ts.equals(this.timeSlots.get(i))) {
                    try {
                        // Timeslot existe déjà : Modification
                        this.tsSrv.update(ts);
                        this.loadData();
                    } catch (Exception ex) {
                        Logger.getLogger(timeSlotBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        // Timeslot n'existe pas : Création
                        this.tsSrv.add(ts);
                        this.loadData();
                        break;
                    } catch (Exception ex) {
                        Logger.getLogger(timeSlotBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public void removePlageHoraire(TimeSlot ts) {
        if (ts != null) {
            try {
                this.tsSrv.remove(ts);
                this.loadData();
            } catch (Exception ex) {
                Logger.getLogger(timeSlotBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            FacesMessage message = new FacesMessage("Erreur : Veuillez sélectionner une Plage Horaire !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public TimeSlot getSelectegTs() {
        return selectegTs;
    }

    public void setSelectegTs(TimeSlot selectegTs) {
        this.selectegTs = selectegTs;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public timeSlotBean() {
        this.loadData();
    }

    private void loadData() {
        try {
            this.timeSlots = tsSrv.getAll();
        } catch (Exception ex) {
            Logger.getLogger(timeSlotBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
