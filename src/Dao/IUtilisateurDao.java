package Dao;



import Models.Eleve;
import Models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IUtilisateurDao {
    boolean authentifier(String pseudo, String motDePasse) ;
    boolean ajouterCompte(String pseudo, String motDePasse);
    boolean modifier(String pseudo, String motDePasse);
    boolean supprimer(String pseudo, String motDePasse);
    List<Utilisateur> listUtilisateur();
}
