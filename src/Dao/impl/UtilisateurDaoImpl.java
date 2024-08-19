package Dao.impl;


import Dao.IUtilisateurDao;
import Dao.SingletonDataBase;
import Models.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoImpl implements IUtilisateurDao {
    private final Connection connection;

    public UtilisateurDaoImpl() {
        this.connection = SingletonDataBase.getInstance();
    }

    @Override
    public boolean authentifier(String pseudo, String motDePasse) {
        String authSQL = "SELECT COUNT(*) FROM utilisateur WHERE pseudo = ? AND motDePasse = ?";
        try (PreparedStatement authStatement = connection.prepareStatement(authSQL)) {
            authStatement.setString(1, pseudo);
            authStatement.setString(2, motDePasse);
            try (ResultSet rs = authStatement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'authentification : " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean ajouterCompte(String pseudo, String motDePasse) {
        String insertUserSQL = "INSERT INTO utilisateur (pseudo, motDePasse,dateCreation) VALUES (?, ?,?)";
        try (PreparedStatement userStatement = connection.prepareStatement(insertUserSQL)) {
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            userStatement.setString(1, pseudo);
            userStatement.setString(2, motDePasse);
            userStatement.setDate(3, sqlDate);
            int rowsInserted = userStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du compte : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modifier(String pseudo, String motDePasse) {
        String updateUserSQL = "UPDATE utilisateur SET motDePasse = ? WHERE pseudo = ?";
        try (PreparedStatement userStatement = connection.prepareStatement(updateUserSQL)) {
            userStatement.setString(1, motDePasse);
            userStatement.setString(2, pseudo);
            int rowsUpdated = userStatement.executeUpdate();
            return rowsUpdated > 0; // Retourne true si la mise à jour a réussi
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
        }
        return false;
    }
    public boolean modifierpseudo(String pseudo, String motDePasse) {
        String updateUserSQL = "UPDATE utilisateur SET pseudo = ? WHERE motDePasse = ?";
        try (PreparedStatement userStatement = connection.prepareStatement(updateUserSQL)) {
            userStatement.setString(1, motDePasse);
            userStatement.setString(2, pseudo);
            int rowsUpdated = userStatement.executeUpdate();
            return rowsUpdated > 0; // Retourne true si la mise à jour a réussi
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean supprimer(String pseudo, String motDePasse) {
        String deleteUserSQL = "DELETE FROM utilisateur WHERE pseudo = ? AND motDePasse = ?";
        try (PreparedStatement userStatement = connection.prepareStatement(deleteUserSQL)) {
            userStatement.setString(1, pseudo);
            userStatement.setString(2, motDePasse);
            int rowsDeleted = userStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Utilisateur> listUtilisateur() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String selectUsersSQL = "SELECT * FROM utilisateur";
        try (PreparedStatement userStatement = connection.prepareStatement(selectUsersSQL)) {
            try (ResultSet rs = userStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String pseudo = rs.getString("pseudo");
                    String motDePasse = rs.getString("motDePasse");
                    Date dateCreation = rs.getDate("dateCreation");
                    Utilisateur utilisateur = new Utilisateur(id,pseudo, motDePasse, dateCreation);
                    utilisateurs.add(utilisateur);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }

}
