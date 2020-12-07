/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lml.snir.controleacces.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lml.snir.controleacces.metier.entity.Administrateur;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.physique.data.PersonneDataService;
import lml.snir.controleacces.physique.data.PhysiqueDataFactory;

/**
 *
 * @author saturne
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private List<PersonneBean> personnesBean;
    private List<Personne> personnes;
    private PersonneBean selectedPersBean;
    
    private String prenom;
    private String nom;
    
    private String login;
    private String mdp;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public PersonneBean getSelectedPersBean() {
        return selectedPersBean;
    }

    public void removePersBean(long i) throws Exception {
        if (i != 0) {
            this.persSrv.remove(this.persSrv.getById(i));
            this.loadData();
            //this.reload();
        } else {
            FacesMessage message = new FacesMessage("Erreur : Vous avez oublié de sélectionner une Personne !");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void setSelectedPersBean(PersonneBean selectedPersBean) {
        this.selectedPersBean = selectedPersBean;
    }

    public List<PersonneBean> getPersonnesBean() {
        return personnesBean;
    }

    public void setPersonnesBean(List<PersonneBean> personnesBean) {
        this.personnesBean = personnesBean;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }
    private final PersonneDataService persSrv = PhysiqueDataFactory.getPersonneDataService();

    private void loadData() {
        try {
            this.personnes = persSrv.getAll();
            tranform(personnes);
        } catch (Exception ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public UserBean() {
        this.loadData();
    }

    public void addEditPersonne() {
        if((!this.nom.equals("")) && (!"".equals(prenom))) {
            // Nom & Prénom non null : Vérification personne
            Personne p = new Personne();
            p.setNom(nom);
            p.setPrenom(prenom);
            // Vérif Admin :
            if ((!"".equals(login)) && (!"".equals(mdp))){
                try {
                    // Admin détecté :
                    Administrateur a = new Administrateur();
                    a.setNom(nom);
                    a.setPrenom(prenom);
                    a.setLogin(login);
                    a.setMdp(mdp);
                    for (int i = 0; i < this.personnes.size(); i++) {
                        if (a.equals(this.personnes.get(i))) {
                            // L'admin existe : Modification
                            this.persSrv.update(a);
                            this.loadData();
                            //this.reload();
                        }else {
                            // Nouvel Admin : Création
                            this.persSrv.add(a);
                            this.loadData();
                            //this.reload();
                            break;
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                // C'est bien une Personne :
                for (int i = 0; i < this.personnes.size(); i++) {
                    if (p.equals(this.personnes.get(i))){
                        try {
                            // La personne existe : Modification
                            this.persSrv.update(p);
                            this.loadData();
                        } catch (Exception ex) {
                            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else {
                        try {
                            this.persSrv.add(p);
                            this.loadData();
                            break;
                        } catch (Exception ex) {
                            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
    
    private void tranform(List<Personne> p) {
        this.personnesBean = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            PersonneBean pB = new PersonneBean();

            pB.setId(p.get(i).getId());
            pB.setNom(p.get(i).getNom());
            pB.setPrenom(p.get(i).getPrenom());
            pB.setDiscriminant(p.get(i));
            pB.setLogin(p.get(i));
            pB.setPassword(p.get(i));

            this.personnesBean.add(pB);
        }
    }
}
