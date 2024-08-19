package Other.Menu;

import Other.Option.OptionUtilisateur;
import Services.Imp.UtilisateurServiceImpl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Classe qui gère le menu des utilisateurs, permettant d'ajouter, supprimer,
 * modifier et lister les utilisateurs. Fournit une interface utilisateur
 * textuelle pour interagir avec le service d'utilisateurs.
 */
public class MenuUtilisateur {


    /**
     * Affiche le menu de gestion des utilisateurs et traite les choix de l'utilisateur.
     * Permet d'ajouter, de supprimer, de modifier et de lister les utilisateurs.
     * Dirige également vers le menu principal ou l'accueil selon le choix de l'utilisateur.
     *
     * @param debut L'instant de début de la session, utilisé pour calculer la durée de la session.
     */
    public static void menu(Instant debut) {
        LocalTime date = LocalTime.now();
        Scanner scanner = new Scanner(System.in);
        OptionUtilisateur optionUtilisateur = new OptionUtilisateur();
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
                    optionUtilisateur.ajouterUtilisateur();
                    break;
                case 2:
                    optionUtilisateur.supprimerUtilisateur();
                    break;
                case 3:
                    optionUtilisateur.modifierUtilisateur(debut);
                    break;
                case 4:
                    optionUtilisateur.listerUtilisateurs(debut);
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
