package lml.snir.controleacces.physique.data;

import java.util.List;
import lml.persistence.CrudService;
import lml.snir.controleacces.metier.entity.Salle;

/**
 *
 * @author fanou
 */
public interface SalleDataService extends CrudService<Salle> {
    public List<Salle> getByProtege(boolean protege) throws Exception;
}

