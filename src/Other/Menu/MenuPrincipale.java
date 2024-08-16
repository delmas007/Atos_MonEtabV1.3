package Other.Menu;

import Other.Option.OptionEleve;
import Services.Imp.EleveServiceImpl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;


/**
 * Classe qui gère le menu principal de l'application, permettant à l'utilisateur
 * de choisir entre la gestion des élèves, des professeurs, des utilisateurs, ou de quitter l'application.
 * Affiche une interface utilisateur textuelle pour naviguer dans les différentes options disponibles.
 */
public class MenuPrincipale {


    /**
     * Affiche le menu principal et traite les choix de l'utilisateur.
     * Permet d'accéder aux sous-menus pour la gestion des élèves, des professeurs,
     * des utilisateurs, ou de quitter l'application. Utilise l'instant de début pour
     * calculer la durée de la session à la fermeture de l'application.
     *
     * @param debut L'instant de début de la session, utilisé pour calculer la durée de la session à la fin.
     */
    public static void menuPrincipale(Instant debut) {
        int choix;
        do {
            LocalTime date = LocalTime.now();
            Scanner scanner = new Scanner(System.in);
            EleveServiceImpl eleveService = new EleveServiceImpl();
            OptionEleve optionEleve = new OptionEleve();
            System.out.println(
                    """
                    ******************************************************
                            BIENVENU DANS L’APPLICATION ETAB v1.2
                    ****************************************************** 
                      
                    MENU:
                         1: Gestion des élèves
                         2: Gestion des professeurs
                         3: Gestion des utilisateurs
                         0: Quitter
                        
                    Date système: """+ " " + date.getHour() + ":" + date.getMinute() +
                            "\nEntrez votre choix : "
            );
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    MenuEleve.menu(debut);
                    break;
                case 2:
                    MenuProfesseur.menu(debut);
                    break;
                case 3:
                    MenuUtilisateur.menu(debut);
                    break;
                case 0:
                    optionEleve.quitter(debut);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }
}
