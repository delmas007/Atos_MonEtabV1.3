package Other.Menu;

import Models.Professeur;
import Services.Imp.ProfesseurServiceImpl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Classe qui gère le menu des professeurs, permettant d'ajouter, supprimer,
 * modifier, lister et obtenir le dernier professeur ajouté. Fournit une
 * interface utilisateur textuelle pour interagir avec le service de professeurs.
 */
public class MenuProfesseur {


    /**
     * Affiche le menu de gestion des professeurs et traite les choix de l'utilisateur.
     * Permet d'ajouter, de supprimer, de modifier, de lister les professeurs et
     * d'obtenir le dernier professeur ajouté. Dirige également vers le menu principal
     * ou quitte selon le choix de l'utilisateur.
     *
     * @param debut L'instant de début de la session, utilisé pour calculer la durée de la session.
     */
    public static void menu(Instant debut) {
        LocalTime date = LocalTime.now();
        Scanner scanner = new Scanner(System.in);
        ProfesseurServiceImpl professeurService = new ProfesseurServiceImpl();
        Professeur professeur = new Professeur();
        int choix;
        do {
            System.out.println(
                    """
                    ******************************************************
                                   GESTION DES PROFESSEURS 
                    ****************************************************** 
                      
                    MENU:
                         1: Ajouter un professeur
                         2: Supprimer un professeur
                         3: Modifier les informations du professeur
                         4: Lister les professeurs
                         5: Obtenir le dernier professeur ajouté
                         6: Retour
                         0: Quitter
                        
                    Date système : """+ " " + date.getHour() + ":" + date.getMinute() +
                            "\nEntrez votre choix : "
            );
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    professeurService.ajouterProfesseur();
                    break;
                case 2:
                    professeurService.supprimerProfesseur();
                    break;
                case 3:
                    professeurService.modifierProfesseur(debut);
                    break;
                case 4:
                    professeurService.listerProfesseur(debut);
                    break;
                case 5:
                    professeur.dernier();
                    break;
                case 6:
                    MenuPrincipale.menuPrincipale(debut);
                    break;
                case 0:
                    MenuPrincipale.menuPrincipale(debut);
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }
}
