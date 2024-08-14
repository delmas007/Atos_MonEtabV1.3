package Services;

import java.time.Instant;

/**
 * Interface définissant les services liés à la gestion des professeurs.
 * Fournit des méthodes pour ajouter, supprimer, modifier et lister les professeurs.
 */
public interface IProfesseurService {

    /**
     * Ajoute un nouveau professeur.
     * Cette méthode permet d'entrer les détails d'un professeur et de l'ajouter à la base de données ou à la liste des professeurs.
     */
    void ajouterProfesseur();

    /**
     * Supprime un professeur existant.
     * Cette méthode demande un identifiant de professeur et supprime le professeur correspondant de la base de données ou de la liste des professeurs.
     */
    void supprimerProfesseur();

    /**
     * Modifie les informations d'un professeur existant.
     * Cette méthode permet de modifier les détails d'un professeur en fonction de l'identifiant fourni et des nouvelles informations.
     *
     * @param debut Instant représentant le début de la session ou de l'opération.
     */
    void modifierProfesseur(Instant debut);

    /**
     * Liste tous les professeurs.
     * Cette méthode affiche tous les professeurs présents dans la base de données ou dans la liste des professeurs.
     *
     * @param debut Instant représentant le début de la session ou de l'opération.
     */
    void listerProfesseur(Instant debut);
}

