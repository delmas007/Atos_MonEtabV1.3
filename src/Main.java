import Models.Utilisateur;
import Other.Menu.MenuPrincipale;

import java.sql.*;
import java.time.Instant;
import java.util.Scanner;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/etab_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Point d'entrée principal de l'application.
     * Cette méthode démarre l'application, affiche un message de bienvenue,
     * gère le processus de connexion utilisateur, et dirige l'utilisateur vers le menu principal
     * après une connexion réussie.
     *
     * @param args Arguments de ligne de commande (non utilisés dans cette méthode).
     */
    public static void main(String[] args) {
        Instant debut = Instant.now();

        Scanner scanner = new Scanner(System.in);


        ajouterUtilisateurParDefaut();


        System.out.println(
                """
                ******************************************************
                        BIENVENU DANS L’APPLICATION ETAB v1.3
                ****************************************************** 
                                CONNEXION                 
               """
        );

        // Demande l'identifiant et le mot de passe à l'utilisateur
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

    private static void ajouterUtilisateurParDefaut() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String checkUserQuery = "SELECT COUNT(*) FROM utilisateur WHERE identifiant = ?";
            preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setString(1, "admin");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);
            if (count == 0) {
                String insertUserQuery = "INSERT INTO utilisateur (identifiant, motDePasse) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertUserQuery);
                preparedStatement.setString(1, "admin");
                preparedStatement.setString(2, "admin");
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
