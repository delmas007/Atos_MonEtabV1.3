package Menu;

import Methode.MethodeEleve;
import Services.Imp.EleveServiceImpl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuPrincipale {

    public static void menuPrincipale(Instant debut) {
        int choix;
        do {
            LocalTime date = LocalTime.now();
            Scanner scanner = new Scanner(System.in);
            EleveServiceImpl eleveService = new EleveServiceImpl();
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
                    eleveService.quitter(debut);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }
}
