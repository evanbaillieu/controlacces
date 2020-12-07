package lml.snir.controleacces.physique.data;

import java.util.List;
import lml.persistence.CrudService;
import lml.snir.controleacces.metier.entity.Administrateur;
import lml.snir.controleacces.metier.entity.Personne;

/**
 *
 * @author fanou
 */
public interface PersonneDataService extends CrudService<Personne> {
    public Administrateur getByLogin(String login) throws Exception;
    public List<Personne> getByNom(String nom) throws Exception;
}
