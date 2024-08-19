package Services;

import Models.Eleve;
import Models.Utilisateur;

import java.time.Instant;
import java.util.List;

/**
 * Interface définissant les services liés à la gestion des utilisateurs.
 * Fournit des méthodes pour ajouter, supprimer, modifier et lister les utilisateurs.
 */
public interface IUtilisateurService {

    boolean connexion(String pseudo, String motDePasse) ;
    boolean save(String pseudo, String motDePasse);
    boolean update(String pseudo, String motDePasse);
    boolean delete(String pseudo, String motDePasse);
    List<Utilisateur> getAll();

}

