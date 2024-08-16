package Services;

import Models.Eleve;

import java.time.Instant;
import java.util.List;

/**
 * Interface définissant les services liés à la gestion des élèves.
 * Fournit des méthodes pour ajouter, supprimer, modifier et lister les élèves.
 */
public interface IEleveService {

    /**
     * Ajoute un nouvel élève.
     * Cette méthode permet d'entrer les détails d'un élève et de l'ajouter à la base de données ou à la liste des élèves.
     */
    void ajouterEleve();

    /**
     * Supprime un élève existant.
     * Cette méthode demande un identifiant d'élève et supprime l'élève correspondant de la base de données ou de la liste des élèves.
     */
    void supprimerEleve();

    /**
     * Modifie les informations d'un élève existant.
     * Cette méthode permet de modifier les détails d'un élève en fonction de l'identifiant fourni et des nouvelles informations.
     *
     * @param debut Instant représentant le début de la session ou de l'opération.
     */
    void modifierEleve(Instant debut);

    /**
     * Liste tous les élèves.
     * Cette méthode affiche tous les élèves présents dans la base de données ou dans la liste des élèves.
     *
     * @param debut Instant représentant le début de la session ou de l'opération.
     */
    void listerEleves(Instant debut);

    Eleve save(Eleve eleve);
    Eleve update(Eleve eleve);
    void delete(int id);
    List<Eleve> getAll();
    Eleve getOne(int id);
}

