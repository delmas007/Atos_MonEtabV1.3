package Services.Imp;


import Models.Professeur;
import Menu.MenuProfesseur;
import Services.IProfesseurService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * Classe d'implémentation du service ProfesseurService qui gère les opérations
 * liées aux professeurs, telles que l'ajout, la suppression, la modification,
 * et l'affichage des informations des professeurs.
 */
public class ProfesseurServiceImpl implements IProfesseurService {
    Professeur professeurr = new Professeur();
    static Scanner scanner2 = new Scanner(System.in);


    /**
     * Ajoute un nouvel professeur après avoir demandé les informations nécessaires
     * (nom, prénom, Vacant, id, ville, date de naissance) via la
     * console. Gère également la validation des entrées.
     */
    @Override
    public void ajouterProfesseur()  {
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


    /**
     * Supprime un professur à partir de son identifiant. Demande confirmation à
     * l'utilisateur s'il souhaite supprimer un autre professeur.
     */
    @Override
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


    /**
     * Modifie les informations du professseur (nom, prénom, date de naissance,
     * identifiant) en fonction du choix de l'utilisateur. Affiche un menu
     * permettant de choisir quelle information modifier.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     */
    @Override
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


    /**
     * Affiche la liste des professeurs. Si la liste est vide, l'utilisateur est
     * invité à retourner au menu principal.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     */
    @Override
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


    /**
     * Quitte l'application en affichant un message d'au revoir et la durée de
     * la session.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     */
    public void quitter(Instant debut) {
        System.out.println("Au revoir !");
        Instant fin = Instant.now();
        Duration sessionDuration = Duration.between(debut, fin);
        long totalSeconds = sessionDuration.getSeconds();
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        System.out.println("Durée de la session : " + minutes + " minute(s) et " + seconds + " seconde(s).");
    }


    /**
     * Méthode utilisée pour gérer la modification d'une valeur spécifique
     * (nom, prénom, etc.) du professeur. Demande confirmation à l'utilisateur
     * s'il souhaite modifier un autre professeur après la modification.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     * @param valeurModifiee La valeur à modifier pour le professeur (Nom, Prénom, etc.).
     */
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

    /**
     * Modifie un professeur spécifié en fonction de la valeur à modifier (nom,
     * prénom, date de naissance, identifiant).
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     * @param valeurModifiee La valeur à modifier pour le professeur (Nom, Prénom, etc.).
     */
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

    /**
     * Demande à l'utilisateur de saisir un identifiant numérique pour un professeur
     * et valide l'entrée.
     *
     * @return L'identifiant numérique saisi par l'utilisateur.
     */
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

    /**
     * Valide et modifie l'identifiant du professeur en s'assurant qu'il s'agit d'un
     * nombre entier valide.
     *
     * @param ele Le professeur dont l'identifiant doit être modifié.
     * @return L'objet professeur mis à jour avec l'identifiant modifié.
     */
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


    /**
     * Valide et modifie la date de naissance du professeur en s'assurant qu'elle
     * est au format "dd/MM/yyyy".
     *
     * @param ele Le professeur dont la date de naissance doit être modifiée.
     * @return L'objet professeur mis à jour avec la date de naissance modifiée.
     */
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
