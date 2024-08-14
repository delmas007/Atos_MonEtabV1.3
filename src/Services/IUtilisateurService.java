package Services;

import java.time.Instant;

/**
 * Interface définissant les services liés à la gestion des utilisateurs.
 * Fournit des méthodes pour ajouter, supprimer, modifier et lister les utilisateurs.
 */
public interface IUtilisateurService {

    /**
     * Ajoute un nouvel utilisateur.
     * Cette méthode permet d'entrer les détails d'un utilisateur et de l'ajouter à la base de données ou à la liste des utilisateurs.
     */
    void ajouterUtilisateur();

    /**
     * Supprime un utilisateur existant.
     * Cette méthode demande un identifiant d'utilisateur et supprime l'utilisateur correspondant de la base de données ou de la liste des utilisateurs.
     */
    void supprimerUtilisateur();

    /**
     * Modifie les informations d'un utilisateur existant.
     * Cette méthode permet de modifier les détails d'un utilisateur en fonction de l'identifiant fourni et des nouvelles informations.
     *
     * @param debut Instant représentant le début de la session ou de l'opération.
     */
    void modifierUtilisateur(Instant debut);

    /**
     * Liste tous les utilisateurs.
     * Cette méthode affiche tous les utilisateurs présents dans la base de données ou dans la liste des utilisateurs.
     *
     * @param debut Instant représentant le début de la session ou de l'opération.
     */
    void listerUtilisateurs(Instant debut);
}

