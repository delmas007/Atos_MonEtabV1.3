package Dao.impl;


import Dao.IUtilisateurDao;
import Models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public class UtilisateurDaoImpl implements IUtilisateurDao {

    @Override
    public Utilisateur getUser(String identifiant, String motDePasse) throws SQLException {
        return null;
    }

    @Override
    public Utilisateur updateUser(String identifiant, String motDePasse) {
        return null;
    }

    @Override
    public void deleteUser(String identifiant, String motDePasse) {

    }

    @Override
    public List<Utilisateur> listeUtilisateur() {
        return List.of();
    }
}
