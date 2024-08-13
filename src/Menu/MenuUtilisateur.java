package Menu;

import Services.Imp.UtilisateurServiceImpl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuUtilisateur {

    public static void menu(Instant debut) {
        LocalTime date = LocalTime.now();
        Scanner scanner = new Scanner(System.in);
        UtilisateurServiceImpl utilisateurService = new UtilisateurServiceImpl();
        int choix;
        do {
            System.out.println(
                    """
                    ******************************************************
                                    GESTION DES UTILISATEURS
                    ****************************************************** 
                      
                    MENU:
                         1: Ajouter un utilisateur
                         2: Supprimer un utilisateur
                         3: Modifier les informations d’un utilisateur
                         4: Lister les utilisateurs
                         6: Retour
                         0: Accueil
                        
                    Date système : """+ " " + date.getHour() + ":" + date.getMinute() +
                            "\nEntrez votre choix : "
            );
                choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    utilisateurService.ajouterUtilisateur();
                    break;
                case 2:
                    utilisateurService.supprimerUtilisateur();
                    break;
                case 3:
                    utilisateurService.modifierUtilisateur(debut);
                    break;
                case 4:
                    utilisateurService.listerUtilisateurs(debut);
                    break;
                case 5, 0:
                    MenuPrincipale.menuPrincipale(debut);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }
}
