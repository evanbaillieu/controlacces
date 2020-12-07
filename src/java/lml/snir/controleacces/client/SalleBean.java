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
import lml.snir.controleacces.metier.entity.Salle;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;
import lml.snir.controleacces.physique.data.SalleDataService;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class SalleBean implements  Serializable{

    private final SalleDataService salleSrv = PhysiqueDataFactory.getSalleDataService();
    private List<Salle> salles;
    private Salle selectedSalle;
    private long salle;
    private boolean protege;

    public long getSalle() {
        return salle;
    }

    public void setSalle(long salle) {
        this.salle = salle;
    }

    public boolean isProtege() {
        return protege;
    }

    public void setProtege(boolean protege) {
        this.protege = protege;
    }
    
    public void addSalle(int salle, boolean protege){
        if(salle != 0){
            Salle s = new Salle();
            s.setId(salle);
            s.setProtege(protege);
            for(int i = 0; i < this.salles.size(); i++){
                if(s.equals(this.salles.get(i))){
                    try {
                        //La salle existe : Modification
                        this.salleSrv.update(s);
                        this.loadData();
                    } catch (Exception ex) {
                        Logger.getLogger(SalleBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        //La salle n'existe pas : Création
                        this.salleSrv.add(s);
                        this.loadData();
                        break;
                    } catch (Exception ex) {
                        Logger.getLogger(SalleBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private void loadData() {
        try {
            this.salles = salleSrv.getAll();
        } catch (Exception ex) {
            Logger.getLogger(SalleBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removeSalle(Salle s){
        if(s != null){
            try {
                this.salleSrv.remove(s);
                this.loadData();
            } catch (Exception ex) {
                Logger.getLogger(SalleBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            FacesMessage message = new FacesMessage("Erreur : Vous avez oublié de sélectionner une Salle !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public Salle getSelectedSalle() {
        return selectedSalle;
    }

    public void setSelectedSalle(Salle selectedSalle) {
        this.selectedSalle = selectedSalle;
    }

    public List<Salle> getSalles() {
        return salles;
    }
    
    public SalleBean() {
        this.loadData();
    }
    
    public String isProtege(Salle s){
        if(s.isProtege()){
            return "Protégée";
        }else{
            return "Non Protégée";
        }
    }
    
    private void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
}
