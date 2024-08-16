package Services;

import Models.Eleve;

import java.time.Instant;
import java.util.List;

/**
 * Interface définissant les services liés à la gestion des élèves.
 * Fournit des méthodes pour ajouter, supprimer, modifier et lister les élèves.
 */
public interface IEleveService {


    Eleve save(Eleve eleve);
    Eleve update(Eleve eleve);
    void delete(int id);
    List<Eleve> getAll();
    Eleve getOne(int id);
}

