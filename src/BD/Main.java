package BD;

import Models.Utilisateur;
import Menu.MenuPrincipale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Scanner;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
    private static final String DB_USER = "votre_utilisateur";
    private static final String DB_PASSWORD = "votre_mot_de_passe";

    public static void gg() {
        Instant debut = Instant.now();
        Scanner scanner = new Scanner(System.in);

        // Initialiser la base de données avec un utilisateur par défaut
        ajouterUtilisateurParDefaut();

        System.out.println(
                """
                ******************************************************
                        BIENVENU DANS L’APPLICATION ETAB v1.3
                ****************************************************** 
                                    CONNEXION                 
               """
        );

        System.out.println("Identifiant : ");
        String identifiant = scanner.nextLine();
        System.out.println("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        boolean connexion = Utilisateur.authentifier(identifiant, motDePasse);
        while (!connexion) {
            System.out.println("Identifiant ou mot de passe incorrect. Veuillez réessayer.");
            System.out.println("Identifiant : ");
            identifiant = scanner.nextLine();
            System.out.println("Mot de passe : ");
            motDePasse = scanner.nextLine();
            connexion = Utilisateur.authentifier(identifiant, motDePasse);
        }
        MenuPrincipale.menuPrincipale(debut);
    }

    /**
     * Ajoute un utilisateur par défaut dans la base de données si celui-ci n'existe pas.
     */
    private static void ajouterUtilisateurParDefaut() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Établir une connexion à la base de données
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Vérifier si l'utilisateur 'admin' existe
            String checkUserQuery = "SELECT COUNT(*) FROM utilisateurs WHERE identifiant = ?";
            preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setString(1, "admin");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);
            if (count == 0) {
                // Si l'utilisateur n'existe pas, l'ajouter
                String insertUserQuery = "INSERT INTO utilisateurs (identifiant, mot_de_passe) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertUserQuery);
                preparedStatement.setString(1, "admin");
                preparedStatement.setString(2, "admin"); // Stocker le mot de passe haché en production
                preparedStatement.executeUpdate();
                System.out.println("Utilisateur 'admin' ajouté par défaut.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur par défaut : " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
