import Models.Utilisateur;
import Menu.MenuPrincipale;

import java.time.Instant;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Instant debut = Instant.now();
        Scanner scanner = new Scanner(System.in);
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
}
