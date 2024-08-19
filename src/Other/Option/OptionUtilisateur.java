package Other.Option;

import Dao.impl.UtilisateurDaoImpl;
import Models.Utilisateur;
import Other.Menu.MenuUtilisateur;
import Services.Imp.UtilisateurServiceImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class OptionUtilisateur {
    static UtilisateurServiceImpl utilisateurr = new UtilisateurServiceImpl(new UtilisateurDaoImpl());

    /**
     * Ajoute un nouvel utilisateur après avoir demandé les informations nécessaires
     * (identifiant et mot de passe) via la
     * console. Gère également la validation des entrées.
     */
    public void ajouterUtilisateur()  {
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Ajouter un pseudo");
        String pseudo = scanner2.nextLine();
        System.out.print("Entrez le mot de passe : ");
        String password = scanner2.nextLine();
        boolean user =utilisateurr.save(pseudo,password);
        if (user) {
            System.out.println("Utilisateur ajouté avec succès.");
        } else {
            System.out.println("Erreur lors de l'ajout de l'utilisateur.");
        }
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
    public void supprimerUtilisateur() {
        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Entrez le pseudo de l'utilisateur : ");
        String pseudo = scanner3.nextLine();
        System.out.print("Entrez le mot de passe de l'utilisateur : ");
        String password = scanner3.nextLine();
        boolean user= utilisateurr.delete(pseudo,password);
        if (user) {
            System.out.println("Utilisateur supprimé avec succès.");
        } else {
            System.out.println("Erreur lors de la suppression de l'utilisateur(utilisateur inexistant ou identifiant incorrect)");
        }
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
    public void modifierUtilisateur(Instant debut) {
        Scanner scanner4 = new Scanner(System.in);
        System.out.println(
                """
                    ******************************************************
                            MODIFIER LES INFORMATIONS DE L'utilisateur
                    ******************************************************   
                    MENU:
                        1: Modifier le pseudo
                        2: Modifier mot de passe
                        3: Retour
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
                valeurModifiee = "motDePasse";
                methode(debut, valeurModifiee);
                break;
            case 3:
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
    public void listerUtilisateurs(Instant debut) {
        Utilisateur Utilisateur = new Utilisateur();
        List<Utilisateur> Utilisateurs = utilisateurr.getAll();
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
            if (valeurModifiee == "pseudo"  || valeurModifiee == "motDePasse") {
                if (valeurModifiee.equalsIgnoreCase("pseudo")) {
                    System.out.println("Veuillez entrer votre ancien pseudo :");
                    String pseudo = scanner5.nextLine();
                    System.out.println("Veuillez entrer votre mot de passe :");
                    String passeword = scanner5.nextLine();
                    boolean user = utilisateurr.connexion(pseudo,passeword);
                    if (user){
                        System.out.println("Veuillez entrer le nouveau pseudo :");
                        pseudo = scanner5.nextLine();
                        if (utilisateurr.updatepseudo(pseudo,passeword)){
                            System.out.println("pseudo modifié avec succès");
                        }else {
                            System.out.println("Erreur lors de la modification du pseudo(utilisateur inexistant ou identifiant incorrect)");
                        }
                }
                } else if (valeurModifiee.equalsIgnoreCase("motDePasse")) {
                    System.out.println("Veuillez entrer votre pseudo :");
                    String pseudo = scanner5.nextLine();
                    System.out.println("Veuillez entrer votre ancien mot de passe :");
                    String passeword = scanner5.nextLine();
                    boolean user = utilisateurr.connexion(pseudo,passeword);
                    if (user){
                        System.out.println("Veuillez entrer le nouveau mot de passe :");
                        passeword = scanner5.nextLine();
                        if (utilisateurr.update(pseudo,passeword)){
                            System.out.println("mot de passe modifié avec succès");
                        }else {
                            System.out.println("Erreur lors de la modification du mot de passe(utilisateur inexistant ou identifiant incorrect)");
                        }
                    }
                }
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
