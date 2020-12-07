/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lml.snir.controleacces.client;

import java.io.Serializable;
import lml.snir.controleacces.metier.entity.Administrateur;
import lml.snir.controleacces.metier.entity.Personne;

/**
 *
 * @author saturne
 */
public class PersonneBean implements Serializable {
    private long id;
    private String nom;
    private String prenom;
    private char discriminant;
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(Personne p) {
        if(p instanceof Administrateur){
            Administrateur a = (Administrateur) p;
            this.login = a.getLogin();
        }else{
            this.login = "";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(Personne p) {
        if(p instanceof Administrateur){
            Administrateur a = (Administrateur) p;
            this.password = a.getMdp();
        }else{
            this.password = "";
        }
    }

    public long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public char getDiscriminant() {
        return discriminant;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDiscriminant(Personne p) {
        if(p instanceof Administrateur){
            this.discriminant = 'A';
        }else{
            this.discriminant = 'P';
        }
    }
    
    
}
