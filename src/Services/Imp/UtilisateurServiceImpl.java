package Services.Imp;


import Models.Utilisateur;
import Other.Menu.MenuUtilisateur;
import Services.IUtilisateurService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Classe d'implémentation du service UtilisateurService qui gère les opérations
 * liées aux Utilisateurs, telles que l'ajout, la suppression, la modification,
 * et l'affichage des informations des Utilisateurs.
 */
public class UtilisateurServiceImpl implements IUtilisateurService {
    Utilisateur utilisateurr = new Utilisateur();


    /**
     * Ajoute un nouvel utilisateur après avoir demandé les informations nécessaires
     * (identifiant et mot de passe) via la
     * console. Gère également la validation des entrées.
     */
    @Override
    public void ajouterUtilisateur()  {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Ajouter un utilisateur");
        System.out.print("Entrez le mot de passe : ");
        String password = scanner2.nextLine();
        String identifiant = UUID.randomUUID().toString();
        Utilisateur.ajouterCompte(identifiant, password);
        System.out.println("Voulez-vous ajouter un autre utilisateur ? (oui/non)");
        String reponse = scanner2.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            ajouterUtilisateur();
        }
    }

    /**
     * Supprime un utilisateur à partir de son identifiant. Demande confirmation à
     * l'utilisateur s'il souhaite supprimer un autre élève.
     */
    @Override
    public void supprimerUtilisateur() {
        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Entrez l'identifiant de l'utilisateur : ");
        String id = scanner3.nextLine();
        System.out.print("Entrez le mot de passe de l'utilisateur : ");
        String password = scanner3.nextLine();
        utilisateurr.supprimerCompte(id,password);
        Scanner scanner0 = new Scanner(System.in);
        System.out.print("Voulez vous supprmer un autre utilisateur ? (oui /non): ");
        String choix = scanner0.nextLine();
        while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non")) {
            System.out.println("Veuillez entrer oui ou non");
            choix = scanner0.nextLine();
        }
        if (choix.equalsIgnoreCase("oui")) {
            supprimerUtilisateur();
        }
    }

    /**
     * Modifie les informations d'un utilisateur (pseudo, mot de passe ou
     * identifiant) en fonction du choix de l'utilisateur. Affiche un menu
     * permettant de choisir quelle information modifier.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     */
    @Override
    public void modifierUtilisateur(Instant debut) {
        Scanner scanner4 = new Scanner(System.in);
        Utilisateur Utilisateur = new Utilisateur();
        System.out.println(
                """
                    ******************************************************
                            MODIFIER LES INFORMATIONS DE L'utilisateur
                    ******************************************************   
                    MENU:
                        1: Modifier le pseudo
                        2: Modifier le identifiant
                        3: Modifier motDePasse
                        4: Retour
                        0: Accueil
                       
                        Entrez votre choix :""");
        int choix = scanner4.nextInt();
        String valeurModifiee = "";
        switch (choix) {
            case 1:
                valeurModifiee = "pseudo";
                methode(debut, valeurModifiee);
                break;
            case 2:
                valeurModifiee = "identifiant";
                methode(debut, valeurModifiee);
                break;
            case 3:
                valeurModifiee = "motDePasse";
                methode(debut, valeurModifiee);
                break;
            case 4:
                MenuUtilisateur.menu(debut);
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
     * Affiche la liste des utilisateurs. Si la liste est vide, l'utilisateur est
     * invité à retourner au menu principal.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     */
    @Override
    public void listerUtilisateurs(Instant debut) {
        Utilisateur Utilisateur = new Utilisateur();
        List<Utilisateur> Utilisateurs = Utilisateur.listerUtilisateurs();
        if (Utilisateurs.isEmpty()) {
            System.out.println("La liste des utilisateurs est vide.");
            Scanner scanner = new Scanner(System.in);
            String reponse = "";
            while (true) {
                System.out.print("Appuyer sur 'oui' pour revenir au menu: ");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    MenuUtilisateur.menu(debut);
                }else {
                    System.out.println("Commande invalide. Veuillez entrer 'oui' pour revenir au menu");
                }
            }
        } else {
            System.out.println("Liste des utilisateurs : "+ Utilisateurs);
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String reponse = "";
                System.out.print("Appuyer sur 'oui' pour revenir au menu: ");
                reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    MenuUtilisateur.menu(debut);
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
     * Demande à l'utilisateur de saisir un identifiant numérique pour un utilisateur
     * et valide l'entrée.
     *
     * @return L'identifiant numérique saisi par l'utilisateur.
     */
    public String idUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        String id = "";
        boolean validId = false;
        while (!validId) {
            try {
                System.out.print("Entrez l'identifiant de l'utilisateur : ");
                id = scanner.nextLine();
                validId = true;
            } catch (Exception e) {
                System.out.println("Erreur : Veuillez entrer un identifiant numérique valide.");
                scanner.next();
            }
        }
        return id;
    }


    /**
     * Modifie l'utilisateur spécifié en fonction de la valeur à modifier (Identifiant,
     * motDePasse, pseudo).
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     * @param valeurModifiee La valeur à modifier pour l'utilisateur (Nom, Prénom, etc.).
     */
    public void modUtilisateur(Instant debut, String valeurModifiee) {
        Scanner scanner5 = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            String id = idUtilisateur();
            Utilisateur ele = utilisateurr.ObtenirUtilisateur(id);
            while (ele == null) {
                System.out.println("L'utilisateur avec l'identifiant " + id + " n'existe pas.");
                System.out.println("Voulez-vous réessayer ? (oui/non)");
                String reponse = scanner5.nextLine();
                if (reponse.equalsIgnoreCase("oui")) {
                    id = idUtilisateur();
                    ele = utilisateurr.ObtenirUtilisateur(id);
                } else if (reponse.equalsIgnoreCase("non")) {
                    modifierUtilisateur(debut);
                }
            }
            if (valeurModifiee != "pseudo" && valeurModifiee != "Identifiant" && valeurModifiee != "motDePasse") {
                System.out.print("Entrez le nouveau " + valeurModifiee + " : ");
                String nom = scanner5.nextLine();
                if (valeurModifiee.equalsIgnoreCase("pseudo")) {
                    ele.setPseudo(nom);
                } else if (valeurModifiee.equalsIgnoreCase("Identifiant")) {
                    ele.setIdentifiant(nom);
                } else if (valeurModifiee.equalsIgnoreCase("motDePasse")) {
                    ele.setMotDePasse(nom);
                }
            }
            Utilisateur nouv = utilisateurr.modifier(ele);
            if (nouv != null) {
                System.out.println(valeurModifiee + " modifié : " + nouv.toString());
            }
            System.out.println("Voulez-vous modifier un autre utilisateur ? (oui/non)");
            String reponse = scanner5.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                modifierUtilisateur(debut);
            } else if (reponse.equalsIgnoreCase("non")) {
                MenuUtilisateur.menu(debut);
            }
        }
    }

    /**
     * Méthode utilisée pour gérer la modification d'une valeur spécifique
     * de l'utilisateur. Demande confirmation à l'utilisateur
     * s'il souhaite modifier un autre professeur après la modification.
     *
     * @param debut L'Instant marquant le début de la session, utilisé pour calculer la durée de la session.
     * @param valeurModifiee La valeur à modifier pour l'utilisateur.
     */
    public void methode(Instant debut, String valeurModifiee) {
        Scanner scanner4 = new Scanner(System.in);
        modUtilisateur(debut, valeurModifiee);
        System.out.println("Voulez-vous modifier un autre utilisateur ? (oui/non)");
        String reponse = scanner4.nextLine();
        if (reponse.equalsIgnoreCase("oui")) {
            modUtilisateur(debut, valeurModifiee);
        }else if (reponse.equalsIgnoreCase("non")) {
            modifierUtilisateur(debut);
        }
    }
}
