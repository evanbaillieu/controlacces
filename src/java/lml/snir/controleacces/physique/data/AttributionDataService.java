package lml.snir.controleacces.physique.data;

import lml.persistence.CrudService;
import lml.snir.controleacces.metier.entity.Attribution;
import lml.snir.controleacces.metier.entity.Badge;
import lml.snir.controleacces.metier.entity.Personne;

/**
 *
 * @author fanou
 */
public interface AttributionDataService extends CrudService<Attribution> {
    public Attribution getByBadge(Badge badge) throws Exception;
    public Attribution getByPersonne(Personne personne) throws Exception;
}
