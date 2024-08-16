package Dao.impl;

import Dao.IEleveDAO;
import Dao.SingletonDataBase;
import Models.Eleve;

import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EleveDaoImpl implements IEleveDAO {

    Instant debutSession = Instant.now();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Eleve> eleves = new ArrayList<>();

    private final Connection connection;

    public EleveDaoImpl() {
        this.connection = SingletonDataBase.getInstance();
    }

    @Override
    public Eleve ajouter(Eleve eleve) {
        String insertEleveSQL = "INSERT INTO eleve (classe, matricule,nom, prenom, date_naissance, ville, telephone) VALUES ( ?, ?,?, ?, ?, ?, ?)";
        java.util.Date utilDate = eleve.getDateNaissance();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            try (PreparedStatement eleveStatement = connection.prepareStatement(insertEleveSQL)) {
                eleveStatement.setString(1, eleve.getClasse());
                eleveStatement.setString(2, eleve.getMatricule());
                eleveStatement.setString(3, eleve.getNom());
                eleveStatement.setString(4, eleve.getPrenom());
                eleveStatement.setDate(5, sqlDate);
                eleveStatement.setString(6, eleve.getVille());
                eleveStatement.setInt(7, eleve.getTelephone());
                eleveStatement.executeUpdate();
                System.out.println("Élève ajouté avec succès !");
            }catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'élève : " + e.getMessage());
        }
        return eleve;
    }

    @Override
    public Eleve modifier(Eleve eleve) {
        String query = "UPDATE personne SET nom=?, prenom=?, date_naissance=?, ville=?, telephone=? WHERE id=?";
        String queryEleve = "UPDATE eleve SET classe=?, matricule=?,nom=?, prenom=?, date_naissance=?, ville=?, telephone=? WHERE id=?";
        java.util.Date utilDate = eleve.getDateNaissance();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        try {
            connection.setAutoCommit(false); // Commencer la transaction
                    try (PreparedStatement statementEleve = connection.prepareStatement(queryEleve)) {
                        statementEleve.setString(1, eleve.getClasse());
                        statementEleve.setString(2, eleve.getMatricule());
                        statementEleve.setString(3, eleve.getNom());
                        statementEleve.setString(4, eleve.getPrenom());
                        statementEleve.setDate(5, sqlDate);
                        statementEleve.setString(6, eleve.getVille());
                        statementEleve.setInt(7, eleve.getTelephone());
                        statementEleve.setInt(8, eleve.getId());
                        statementEleve.executeUpdate();

                        connection.commit(); // Valider la transaction
//                        System.out.println("Élève modifié avec succès !");
                    }catch (SQLException e) {
                        connection.rollback(); // Annuler la transaction en cas d'erreur
                        System.out.println("Erreur lors de la modification de l'élève : " + e.getMessage());
                    }

        } catch (SQLException e) {
            try {
                connection.rollback(); // Annuler la transaction en cas d'erreur
            } catch (SQLException rollbackEx) {
                System.out.println("Erreur lors de l'annulation de la transaction : " + rollbackEx.getMessage());
            }
            System.out.println("Erreur lors de la modification de l'élève : " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true); // Remettre l'auto-commit à true
            } catch (SQLException e) {
                System.out.println("Erreur lors du rétablissement de l'auto-commit : " + e.getMessage());
            }
        }
        return eleve;
    }

    @Override
    public void supprimer(int id) {
        String deleteEleveSQL = "DELETE FROM eleve WHERE id = ?";

        try {
            connection.setAutoCommit(false); // Commencer la transaction
                    try (PreparedStatement deletePersonneStmt = connection.prepareStatement(deleteEleveSQL)) {
                        deletePersonneStmt.setInt(1, id);
                        int rowsDeletedEleve = deletePersonneStmt.executeUpdate();

                        if (rowsDeletedEleve > 0) {
                            connection.commit(); // Valider la transaction
                            System.out.println("Élève supprimé avec succès !");
                        } else {
                            connection.rollback(); // Annuler la transaction si l'ID n'existe pas dans la table
                            System.out.println("Erreur lors de la suppression de l'élève car l'ID n'existe pas");
                        }
                    } catch (SQLException e) {
                        connection.rollback(); // Annuler la transaction en cas d'erreur
                        System.out.println("Erreur lors de la suppression de l'élève  : " + e.getMessage());
                    }finally {
                        connection.setAutoCommit(true); // Remettre l'auto-commit à true
                    }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la gestion de la transaction : " + e.getMessage());
        }
    }

    @Override
    public List<Eleve> obtenirEleves() {
        List<Eleve> eleves = new ArrayList<>();
        String query = "SELECT id, nom, prenom, date_naissance, ville, telephone, classe, matricule FROM eleve ";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Eleve eleve = new Eleve(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date_naissance"),
                        resultSet.getString("ville"),
                        resultSet.getString("classe"),
                        resultSet.getString("matricule"),
                        resultSet.getInt("telephone")
                );
                eleves.add(eleve);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des élèves : " + e.getMessage());
        }
        return eleves;
    }

    @Override
    public Eleve obtenir(int id) {
        String query = "SELECT id, nom, prenom, date_naissance, ville, telephone, classe, matricule FROM  eleve  WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Eleve(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getDate("date_naissance"),
                        resultSet.getString("ville"),
                        resultSet.getString("classe"),
                        resultSet.getString("matricule"),
                        resultSet.getInt("telephone")
                );
            } else {
                System.out.println("Aucun élève trouvé avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'élève : " + e.getMessage());
        }
        return null;
    }

}
