package Menu;

import Models.Eleve;
import Services.Imp.EleveServiceImpl;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Scanner;

public class MenuEleve {

    public static void menu(Instant debut) {
        LocalTime date = LocalTime.now();
        Scanner scanner = new Scanner(System.in);
        EleveServiceImpl eleveService = new EleveServiceImpl();
        int choix;
        do {
            System.out.println(
                    """
                    ******************************************************
                                    GESTION DES ELEVES
                    ****************************************************** 
                      
                    MENU:
                         1: Ajouter un élève
                         2: Supprimer un élève
                         3: Modifier les informations de l'élève
                         4: Lister les élèves
                         5: Obtenir le dernier élève ajouté
                         6: Retour
                         0: Quitter
                        
                    Date système : """+ " " + date.getHour() + ":" + date.getMinute() +
                            "\nEntrez votre choix : "
            );
                choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    eleveService.ajouterEleve();
                    break;
                case 2:
                    eleveService.supprimerEleve();
                    break;
                case 3:
                    eleveService.modifierEleve(debut);
                    break;
                case 4:
                    eleveService.listerEleves(debut);
                    break;
                case 5:
                    Eleve.dernier();
                    scanner.nextLine();
                    System.out.println("Appuyez sur 0 pour revenir au menu : ");
                    int choixRetour = scanner.nextInt();
                    break;
                case 6:
                    MenuPrincipale.menuPrincipale(debut);
                    break;
                case 0:
                    eleveService.quitter(debut);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }
}
