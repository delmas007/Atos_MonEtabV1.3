package Services;

import Models.Professeur;

import java.time.Instant;
import java.util.List;

/**
 * Interface définissant les services liés à la gestion des professeurs.
 * Fournit des méthodes pour ajouter, supprimer, modifier et lister les professeurs.
 */
public interface IProfesseurService {


    Professeur save(Professeur Professeur);
    Professeur update(Professeur Professeur);
    void delete(int id);
    List<Professeur> getAll();
    Professeur getOne(int id);

}

