package lml.snir.controleacces.metier.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.metier.entity.Salle;
import lml.snir.controleacces.metier.entity.TimeSlot;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2020-12-10T19:10:56")
@StaticMetamodel(Autorisation.class)
public class Autorisation_ { 

    public static volatile SingularAttribute<Autorisation, Salle> salle;
    public static volatile SingularAttribute<Autorisation, Personne> personne;
    public static volatile SingularAttribute<Autorisation, TimeSlot> plageHoraire;
    public static volatile SingularAttribute<Autorisation, Long> id;

}