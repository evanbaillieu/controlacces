package lml.snir.controleacces.physique.data;

import lml.persistence.CrudService;
import lml.snir.controleacces.metier.entity.Badge;

/**
 *
 * @author fanou
 */
public interface BadgeDataService extends CrudService<Badge> { 
    public Badge getByContenu(String contenu) throws Exception;
}
