package lml.snir.controleacces.physique.data;

import java.util.List;
import lml.persistence.CrudService;
import lml.snir.controleacces.metier.entity.Autorisation;
import lml.snir.controleacces.metier.entity.Personne;
import lml.snir.controleacces.metier.entity.Salle;
import lml.snir.controleacces.metier.entity.TimeSlot;

/**
 *
 * @author fanou
 */
public interface AutorisationDataService extends CrudService<Autorisation> {
    public List<Autorisation> getBySalle(Salle salle) throws Exception;
    public List<Autorisation> getByPersonne(Personne personne) throws Exception;
    public List<Autorisation> getByPlageHoraire(TimeSlot plageHoraire) throws Exception;
    public List<Autorisation> getByPeronneEtSalle(Personne personne, Salle salle) throws Exception;
}
