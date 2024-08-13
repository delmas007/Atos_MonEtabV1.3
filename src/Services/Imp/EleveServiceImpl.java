package Services.Imp;


import Models.Eleve;
import Menu.MenuEleve;
import Services.IEleveService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EleveServiceImpl implements IEleveService {
    Eleve elevee = new Eleve();

    @Override
    public void ajouterEleve()  {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Ajouter un élève");
        System.out.print("Entrez le prénom : ");
        String prenom = scanner2.nextLine();
        System.out.print("Entrez le nom : ");
        String nom = scanner2.nextLine();
        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            try {
                System.out.print("Entrez l'âge : ");
                age = scanner2.nextInt();
                validAge = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer l'âge valide.");
                scanner2.nextLine();
            }
        }
        scanner2.nextLine();
        System.out.print("Entrez le genre : ");
        String genre = scanner2.nextLine();
        boolean validId = false;
        int id = 0;
        while (!validId) {
            try {
                System.out.print("Entrez l'Id : ");
                id = scanner2.nextInt();
                validId = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un id valide.");
                scanner2.nextLine();
            }
        }
        scanner2.nextLine();
        System.out.print("Entrez le ville : ");
        String ville = scanner2.nextLine();
        Date date1 = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Entrez la date format dd/MM/yyyy  : ");
                String date = scanner2.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                date1 = sdf.parse(date);
                validDate = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer une date valide.");
            }
        }
        Eleve eleve = new Eleve(id, nom, prenom, age, genre, date1, ville);
        System.out.println("Élève ajouté : " + eleve.toString());
        Eleve.ajouters(eleve);
        System.out.println("Voulez-vous ajouter un autre élève ? (oui/non)");
        String reponse = scanner2.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            ajouterEleve();
        }
    }

    @Override
    public void supprimerEleve() {
        Scanner scanner3 = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Entrez l'identifiant de l'élève : ");
                int id = scanner3.nextInt();
                validInput = true;
                elevee.supprimer(id);
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
                scanner3.next();
            }
        }
        Scanner scanner0 = new Scanner(System.in);
        System.out.print("Voulez vous supprmer un autre élève ? (oui /non): ");
        String choix = scanner0.nextLine();
        while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non")) {
            System.out.println("Veuillez entrer oui ou non");
            choix = scanner0.nextLine();
        }
        if (choix.equalsIgnoreCase("oui")) {
            supprimerEleve();
        }
    }

    @Override
    public void modifierEleve(Instant debut) {
        Scanner scanner4 = new Scanner(System.in);
        Eleve eleve = new Eleve();
        System.out.println(
                """
                    ******************************************************
                            MODIFIER LES INFORMATIONS DE L'ÉLÈVE
                    ******************************************************   
                    MENU:
                        1: Modifier le nom
                        2: Modifier le prénom
                        3: Modifier la date de naissance
                        4: Modifier l'identifiant
                        5: Retour
                        6: Accueil
                       
                        Entrez votre choix :""");
        int choix = scanner4.nextInt();
        String valeurModifiee = "";
        switch (choix) {
            case 1:
                valeurModifiee = "Nom";
                methode(debut, valeurModifiee);
                break;
            case 2:
                valeurModifiee = "Prénom";
                methode(debut, valeurModifiee);
                break;
            case 3:
                valeurModifiee = "Date de naissance";
                methode(debut, valeurModifiee);
                break;
            case 4:
                valeurModifiee = "Identifiant";
                methode(debut, valeurModifiee);
                break;
            case 5:
                MenuEleve.menu(debut);
                break;
            case 0:
                quitter(debut);
                System.exit(0);
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }while (choix != 0);
    } ;

    @Override
    public void listerEleves(Instant debut) {
        Eleve eleve = new Eleve();
        List<Eleve> eleves = eleve.obtenirEleve();
        if (eleves.isEmpty()) {
            System.out.println("La liste des élèves est vide.");
            Scanner scanner = new Scanner(System.in);
            String reponse = "";
            while (true) {
                System.out.print("Appuyer sur 'oui' pour revenir au menu: ");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    MenuEleve.menu(debut);
                }else {
                    System.out.println("Commande invalide. Veuillez entrer 'oui' pour revenir au menu");
                }
            }
        } else {
            System.out.println("Liste des élèves : "+ eleves);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String reponse = "";
                System.out.print("Appuyer sur 'oui' pour revenir au menu: ");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    MenuEleve.menu(debut);
                }else {
                    System.out.println("Commande invalide. Veuillez entrer 'oui' pour revenir au menu");
                }
            }
        }
    }



    public void quitter(Instant debut) {
        System.out.println("Au revoir !");
        Instant fin = Instant.now();
        Duration sessionDuration = Duration.between(debut, fin);
        long totalSeconds = sessionDuration.getSeconds();
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        System.out.println("Durée de la session : " + minutes + " minute(s) et " + seconds + " seconde(s).");


    }
    public int idEleve() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        boolean validId = false;
        while (!validId) {
            try {
                System.out.print("Entrez l'identifiant de l'élève : ");
                id = scanner.nextInt();
                validId = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
                scanner.next();
            }
        }
        return id;
    }

    public void modEleve(Instant debut, String valeurModifiee) {
        Scanner scanner5 = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            int id = idEleve();
            Eleve ele = elevee.ObtenirEleve(id);
            while (ele == null) {
                System.out.println("L'élève avec l'identifiant " + id + " n'existe pas.");
                System.out.println("Voulez-vous réessayer ? (oui/non)");
                String reponse = scanner5.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    id = idEleve();
                    ele = elevee.ObtenirEleve(id);
                } else if (reponse.equalsIgnoreCase("non")) {
                    modifierEleve(debut);
                }
            }
            if (valeurModifiee != "Date de naissance" && valeurModifiee != "Identifiant") {
                System.out.print("Entrez le nouveau " + valeurModifiee + " : ");
                String nom = scanner5.nextLine();
                if (valeurModifiee.equalsIgnoreCase("Nom")) {
                    ele.setNom(nom);
                } else if (valeurModifiee.equalsIgnoreCase("Prénom")) {
                    ele.setPrenom(nom);
                }
            } else if (valeurModifiee.equalsIgnoreCase("Date de naissance")) {
                ele = exceptionDate(ele);
            } else if (valeurModifiee.equalsIgnoreCase("Identifiant")) {
                ele = exceptionInt(ele);
            }
            Eleve nouv = elevee.modifier(ele);
            System.out.println(valeurModifiee + " modifié : " + nouv.toString());
            System.out.println("Voulez-vous modifier un autre utilisateur ? (oui/non)");
            String reponse = scanner5.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                modifierEleve(debut);
            } else if (reponse.equalsIgnoreCase("non")) {
                MenuEleve.menu(debut);
            }
        }
    }
    public Eleve exceptionInt(Eleve ele) {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        boolean validId = false;

        while (!validId) {
            try {
                System.out.print("Entrez le nouveau identifiant de l'élève : ");
                id = scanner.nextInt();
                ele.setId(id);
                validId = true;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
            }
        }

        return ele;
    }
    public Eleve exceptionDate(Eleve ele) {
        Scanner scanner = new Scanner(System.in);
        Date date1 = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Entrez la date format dd/MM/yyyy  : ");
                String date = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                date1 = sdf.parse(date);
                ele.setDateNaissance(date1);
                validDate = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer une date valide.");
            }
        }
        return ele;
    }
    public void methode(Instant debut, String valeurModifiee) {
        Scanner scanner4 = new Scanner(System.in);
        modEleve(debut, valeurModifiee);
        System.out.println("Voulez-vous modifier un autre élève ? (oui/non)");
        String reponse = scanner4.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            modEleve(debut, valeurModifiee);
        }else if (reponse.equalsIgnoreCase("non")) {
            modifierEleve(debut);
        }
    }
}
