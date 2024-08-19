package Services.Imp;


import Dao.impl.EleveDaoImpl;
import Dao.impl.UtilisateurDaoImpl;
import Models.Utilisateur;
import Other.Menu.MenuUtilisateur;
import Services.IUtilisateurService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Classe d'implémentation du service UtilisateurService qui gère les opérations
 * liées aux Utilisateurs, telles que l'ajout, la suppression, la modification,
 * et l'affichage des informations des Utilisateurs.
 */
public class UtilisateurServiceImpl implements IUtilisateurService {
    private final UtilisateurDaoImpl utilisateurDao;

    public UtilisateurServiceImpl(UtilisateurDaoImpl utilisateurDao) {
        this.utilisateurDao = utilisateurDao;
    }


    @Override
    public boolean connexion(String pseudo, String motDePasse) {
        return utilisateurDao.authentifier(pseudo, motDePasse);
    }

    @Override
    public boolean save(String pseudo, String motDePasse) {
        return utilisateurDao.ajouterCompte(pseudo, motDePasse);
    }

    @Override
    public boolean update(String pseudo, String motDePasse) {
        return utilisateurDao.modifier(pseudo, motDePasse);
    }

    @Override
    public boolean delete(String pseudo, String motDePasse) {
        return utilisateurDao.supprimer(pseudo, motDePasse);
    }

    @Override
    public List<Utilisateur> getAll() {
        return utilisateurDao.listUtilisateur();
    }

    public boolean updatepseudo(String pseudo, String motDePasse) {
        return utilisateurDao.modifierpseudo(pseudo, motDePasse);
    }
}
