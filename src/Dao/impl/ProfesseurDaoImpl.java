package Dao.impl;


import Dao.IProfesseurDAO;
import Dao.SingletonDataBase;
import Models.Professeur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDaoImpl implements IProfesseurDAO {

    private final Connection connection;

    public ProfesseurDaoImpl() {
        this.connection = SingletonDataBase.getInstance();
    }

    @Override
    public Professeur ajouter(Professeur professeur) {
        String insertprofesseurSQL = "INSERT INTO professeur (vacant, matiereEnseigne,prochainCours,sujetProchaineReunion,nom, prenom, date_naissance, ville, telephone) VALUES ( ?, ?,?, ?, ?, ?, ?, ?, ?)";
        java.util.Date utilDate = professeur.getDateNaissance();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        try (PreparedStatement professeurStatement = connection.prepareStatement(insertprofesseurSQL)) {
            professeurStatement.setBoolean(1, professeur.isVacant());
            professeurStatement.setString(2, professeur.getMatiereEnseigne());
            professeurStatement.setString(3, professeur.getProchainCours());
            professeurStatement.setString(4, professeur.getSujetProchaineReunion());
            professeurStatement.setString(5, professeur.getNom());
            professeurStatement.setString(6, professeur.getPrenom());
            professeurStatement.setDate(7, sqlDate);
            professeurStatement.setString(8, professeur.getVille());
            professeurStatement.setInt(9, professeur.getTelephone());
            professeurStatement.executeUpdate();
            System.out.println("professeur ajouté avec succès !");
        }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du Professeur : " + e.getMessage());
        }
        return professeur;
    }

    @Override
    public Professeur modifier(Professeur professeur) {
        String query = "UPDATE personne SET nom=?, prenom=?, date_naissance=?, ville=?, telephone=? WHERE id=?";
        String queryprofesseur = "UPDATE professeur SET vacant=?, matiereEnseigne=?,prochainCours=?,sujetProchaineReunion=?,nom=?, prenom=?, date_naissance=?, ville=?, telephone=? WHERE id=?";
        java.util.Date utilDate = professeur.getDateNaissance();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        try {
            connection.setAutoCommit(false); // Commencer la transaction
            try (PreparedStatement statementprofesseur = connection.prepareStatement(queryprofesseur)) {
                statementprofesseur.setBoolean(1, professeur.isVacant());
                statementprofesseur.setString(2, professeur.getMatiereEnseigne());
                statementprofesseur.setString(3, professeur.getProchainCours());
                statementprofesseur.setString(4, professeur.getSujetProchaineReunion());
                statementprofesseur.setString(5, professeur.getNom());
                statementprofesseur.setString(6, professeur.getPrenom());
                statementprofesseur.setDate(7, sqlDate);
                statementprofesseur.setString(8, professeur.getVille());
                statementprofesseur.setInt(9, professeur.getTelephone());
                statementprofesseur.setInt(10, professeur.getId());
                statementprofesseur.executeUpdate();

                connection.commit(); // Valider la transaction
            }catch (SQLException e) {
                connection.rollback(); // Annuler la transaction en cas d'erreur
                System.out.println("Erreur lors de la modification du professeur : " + e.getMessage());
            }

        } catch (SQLException e) {
            try {
                connection.rollback(); // Annuler la transaction en cas d'erreur
            } catch (SQLException rollbackEx) {
                System.out.println("Erreur lors de l'annulation de la transaction : " + rollbackEx.getMessage());
            }
            System.out.println("Erreur lors de la modification du professeur : " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); // Remettre l'auto-commit à true
            } catch (SQLException e) {
                System.out.println("Erreur lors du rétablissement de l'auto-commit : " + e.getMessage());
            }
        }
        return professeur;
    }

    @Override
    public void supprimer(int id) {
        String deleteprofesseurSQL = "DELETE FROM professeur WHERE id = ?";
        try {
            connection.setAutoCommit(false); // Commencer la transaction
            try (PreparedStatement deletePersonneStmt = connection.prepareStatement(deleteprofesseurSQL)) {
                deletePersonneStmt.setInt(1, id);
                int rowsDeletedprofesseur = deletePersonneStmt.executeUpdate();

                if (rowsDeletedprofesseur > 0) {
                    connection.commit(); // Valider la transaction
                    System.out.println("Professeur supprimé avec succès !");
                } else {
                    connection.rollback(); // Annuler la transaction si l'ID n'existe pas dans la table
                    System.out.println("Erreur lors de la suppression de du professeur car l'ID n'existe pas");
                }
            } catch (SQLException e) {
                connection.rollback(); // Annuler la transaction en cas d'erreur
                System.out.println("Erreur lors de la suppression de du professeur  : " + e.getMessage());
            }finally {
                connection.setAutoCommit(true); // Remettre l'auto-commit à true
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la gestion de la transaction : " + e.getMessage());
        }
    }

    @Override
    public List<Professeur> obtenirProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        String query = "SELECT vacant,id, matiereEnseigne,prochainCours,sujetProchaineReunion,nom, prenom, date_naissance, ville, telephone FROM Professeur ";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Professeur professeur = new Professeur(
                        resultSet.getBoolean("vacant"),
                        resultSet.getString("matiereEnseigne"),
                        resultSet.getString("prochainCours"),
                        resultSet.getString("sujetProchaineReunion"),
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date_naissance"),
                        resultSet.getString("ville"),
                        resultSet.getInt("telephone")
                );
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des professeurs : " + e.getMessage());
        }
        return professeurs;
    }

//    @Override
//    public Professeur obtenir(int id) {
//        String query = "SELECT vacant, matiereEnseigne,prochainCours,sujetProchaineReunion,nom, prenom, date_naissance, ville, telephone FROM professeur WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Professeur(
//                        resultSet.getBoolean("vacant"),
//                        resultSet.getString("matiereEnseigne"),
//                        resultSet.getString("prochainCours"),
//                        resultSet.getString("sujetProchaineReunion"),
//                        resultSet.getInt("id"),
//                        resultSet.getString("nom"),
//                        resultSet.getString("prenom"),
//                        resultSet.getDate("date_naissance"),
//                        resultSet.getString("ville"),
//                        resultSet.getInt("telephone")
//
//                );
//            } else {
//                System.out.println("Aucun professeur trouvé avec cet ID.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Erreur lors de la récupération du professeur : " + e.getMessage());
//        }
//        return null;
//    }
    @Override
    public Professeur obtenir(int id) {
        String query="SELECT id, vacant, matiereEnseigne, prochainCours, sujetProchaineReunion, nom, prenom, date_naissance, ville, telephone FROM professeur WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Professeur(
                        resultSet.getBoolean("vacant"),
                        resultSet.getString("matiereEnseigne"),
                        resultSet.getString("prochainCours"),
                        resultSet.getString("sujetProchaineReunion"),
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date_naissance"),
                        resultSet.getString("ville"),
                        resultSet.getInt("telephone")
                );
            } else {
                System.out.println("Aucun professeur trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du professeur : " + e.getMessage());
        }
        return null;
    }

}
