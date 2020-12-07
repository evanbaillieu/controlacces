package lml.snir.controleacces.metier.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.metier.entity.Salle;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2020-11-20T15:35:33")
@StaticMetamodel(Evenement.class)
public class Evenement_ { 

    public static volatile SingularAttribute<Evenement, Date> date;
    public static volatile SingularAttribute<Evenement, Salle> salle;
    public static volatile SingularAttribute<Evenement, Personne> personne;
    public static volatile SingularAttribute<Evenement, Boolean> autorise;
    public static volatile SingularAttribute<Evenement, Long> id;

}