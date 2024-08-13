package Methode;

import Models.Professeur;
import Menu.MenuProfesseur;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MethodeProfesseur {
    Professeur professeurr = new Professeur();
    static Scanner scanner2 = new Scanner(System.in);

    public static void ajouterProfesseur()  {
        System.out.println("Ajouter un professeur");
        System.out.print("Entrez le prénom : ");
        String prenom = scanner2.nextLine();
        System.out.print("Entrez le nom : ");
        String nom = scanner2.nextLine();
        boolean validVacant = false;
        boolean vacant = true;
        while (!validVacant) {
            try {
                System.out.print("Vacant : ");
                 vacant = scanner2.nextBoolean();
                validVacant = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer 'true' pour oui ou 'false' pour non.");
                scanner2.nextLine();
            }
        }
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
        Professeur professeur = new Professeur(vacant,id,nom,prenom,date1,ville);
        Professeur.ajouters(professeur);
        System.out.println("Entite.Professeur ajouté : " + professeur.toString());

        System.out.println("Voulez-vous ajouter un autre professeur ? (oui/non)");
        String reponse = scanner2.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            ajouterProfesseur();
        }
    }

    public void supprimerProfesseur() {
        Scanner scanner3 = new Scanner(System.in);
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Entrez l'identifiant du professeur : ");
                int id = scanner3.nextInt();
                validInput = true;
                professeurr.supprimer(id);
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
                scanner3.next();
            }
        }
        Scanner scanner0 = new Scanner(System.in);
        System.out.print("Voulez vous supprmer un autre professeur ? (oui /non): ");
        String choix = scanner0.nextLine();
        while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non")) {
            System.out.println("Veuillez entrer oui ou non");
            choix = scanner0.nextLine();
        }
        if (choix.equalsIgnoreCase("oui")) {
            supprimerProfesseur();
        }
    }

    public void modifierProfesseur(Instant debut) {
        Scanner scanner4 = new Scanner(System.in);
        System.out.println(
                """
                    ******************************************************
                            MODIFIER LES INFORMATIONS DU PROFESSEUR
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
                MenuProfesseur.menu(debut);
                break;
            case 0:
                quitter(debut);
                System.exit(0);
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }while (choix != 0);


    }

    public void listerProfesseur(Instant debut) {
        Professeur professeur = new Professeur();
        List<Professeur> professeurs = professeur.obtenirProfesseurs();
        if (professeurs.isEmpty()) {
            System.out.println("La liste des professeurs est vide.");
            Scanner scanner = new Scanner(System.in);
            String reponse = "";
            while (true) {
                System.out.print("Appuyer sur 'oui' pour revenir au menu: ");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    MenuProfesseur.menu(debut);
                }else {
                    System.out.println("Commande invalide. Veuillez entrer 'oui' pour revenir au menu");
                }
            }
        } else {
            System.out.println("Liste des professeurs : "+ professeurs);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String reponse = "";
                System.out.print("Appuyer sur 'oui' pour revenir au menu: ");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    MenuProfesseur.menu(debut);
                }else {
                    System.out.println("Commande invalide. Veuillez entrer 'oui' pour revenir au menu");
                }
            }
        }
    }

    public void gererProfesseur() {
        Scanner scanner4 = new Scanner(System.in);
        System.out.println(
                """
                    ******************************************************
                                        GÉRER LES NOTES
                    ******************************************************   
                    MENU:
                        1: Ajouter une note
                        2: Modifier une note
                        3: Supprimer une note
                        4: Afficher les notes
                        5: Retour
                        6: Accueil
                        Entrez votre choix :""");
        int reponse = scanner4.nextInt();
        if (reponse != 6) {
            System.out.println("Choix invalide. Veuillez réessayer.");
            gererProfesseur();
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
    public void methode(Instant debut, String valeurModifiee) {
        Scanner scanner4 = new Scanner(System.in);
        modProfesseur(debut, valeurModifiee);
        System.out.println("Voulez-vous modifier un autre professeur ? (oui/non)");
        String reponse = scanner4.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            modProfesseur(debut, valeurModifiee);
        }else if (reponse.equalsIgnoreCase("non")) {
            modifierProfesseur(debut);
        }
    }
    public void modProfesseur(Instant debut, String valeurModifiee) {
        Scanner scanner5 = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            int id = idProfesseur();
            Professeur ele = professeurr.ObtenirProfesseur(id);
            while (ele == null) {
                System.out.println("Le professeur avec l'identifiant " + id + " n'existe pas.");
                System.out.println("Voulez-vous réessayer ? (oui/non)");
                String reponse = scanner5.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    id = idProfesseur();
                    ele = professeurr.ObtenirProfesseur(id);
                } else if (reponse.equalsIgnoreCase("non")) {
                    modifierProfesseur(debut);
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
            Professeur nouv = professeurr.modifier(ele);
            System.out.println(valeurModifiee + " modifié : " + nouv.toString());
            System.out.println("Voulez-vous modifier un autre professeur ? (oui/non)");
            String reponse = scanner5.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                modifierProfesseur(debut);
            } else if (reponse.equalsIgnoreCase("non")) {
                MenuProfesseur.menu(debut);
            }
        }
    }
    public int idProfesseur() {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        boolean validId = false;
        while (!validId) {
            try {
                System.out.print("Entrez l'identifiant du professeur : ");
                id = scanner.nextInt();
                validId = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
                scanner.next();
            }
        }
        return id;
    }

    public Professeur exceptionInt(Professeur ele) {
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        boolean validId = false;

        while (!validId) {
            try {
                System.out.print("Entrez le nouveau identifiant du professuer : ");
                id = scanner.nextInt();
                ele.setId(id);
                validId = true;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
            }
        }

        return ele;
    }
    public Professeur exceptionDate(Professeur ele) {
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
}
