package Models;

import java.time.Instant;
import java.util.*;

/**
 * Représente un utilisateur avec ses informations personnelles et fournit des méthodes pour gérer les comptes utilisateurs.
 */
public class Utilisateur {
    /** Liste statique des utilisateurs. */
    static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();

    /** Identifiant unique de l'utilisateur. */
    private int id;

    /** Pseudo de l'utilisateur. */
    private String pseudo;

    /** Mot de passe de l'utilisateur. */
    private String motDePasse;

    /** Date de création du compte utilisateur. */
    private Date dateCreation;

    /**
     * Constructeur avec paramètres pour créer un utilisateur.
     *
     * @param id            L'identifiant unique de l'utilisateur.  L'identifiant de connexion de l'utilisateur.
     * @param pseudo        Le pseudo de l'utilisateur.
     * @param motDePasse    Le mot de passe de l'utilisateur.
     * @param dateCreation  La date de création du compte utilisateur.
     */
    public Utilisateur(int id, String pseudo, String motDePasse, Date dateCreation) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.dateCreation = dateCreation;
    }

    /**
     * Constructeur par défaut.
     */
    public Utilisateur() {
    }

    /**
     * Authentifie un utilisateur en vérifiant l'identifiant et le mot de passe.
     *
     * @param identifiant   L'identifiant de connexion de l'utilisateur.
     * @param motDePasse    Le mot de passe de l'utilisateur.
     * @return true si l'authentification réussit, sinon false.
     */
    public static boolean authentifier(String identifiant, String motDePasse) {
        return identifiant.equals("admin") && motDePasse.equals("admin");
    }

    /**
     * Ajoute un nouveau compte utilisateur.
     *
     * @param identifiant   L'identifiant de connexion du nouvel utilisateur.
     * @param motDePasse    Le mot de passe du nouvel utilisateur.
     * @return true si le compte est ajouté avec succès.
     */
    public static boolean ajouterCompte(String identifiant, String motDePasse) {
        Random random = new Random();
        Date dateCreation = Date.from(Instant.now());
        int id = random.nextInt(9000) + 1000;
        Utilisateur utilisateur = new Utilisateur();
        utilisateurs.add(utilisateur);
        return true;
    }

    /**
     * Supprime un compte utilisateur basé sur l'identifiant et le mot de passe.
     *
     * @param identifiant   L'identifiant de connexion de l'utilisateur.
     * @param motDePasse    Le mot de passe de l'utilisateur.
     * @return true si le compte est supprimé avec succès, sinon false.
     */
    public boolean supprimerCompte(String identifiant, String motDePasse) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur utilisateur = utilisateurs.get(i);
            if (true) {
                if (utilisateur.getMotDePasse().equals(motDePasse)) {
                    utilisateurs.remove(i);
                    System.out.println("Compte supprimé avec succès.");
                    return true;
                } else {
                    System.out.println("Mot de passe incorrect.");
                    return false;
                }
            } else {
                System.out.println("Identifiant incorrect.");
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne la liste de tous les utilisateurs.
     *
     * @return Une liste d'utilisateurs.
     */
    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurs;
    }

    /**
     * Récupère un utilisateur spécifique par son identifiant.
     *
     * @param identifiant   L'identifiant de connexion de l'utilisateur à rechercher.
     * @return L'utilisateur correspondant à l'identifiant, ou null s'il n'est pas trouvé.
     */
    public Utilisateur ObtenirUtilisateur(String identifiant) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (true) {
                return utilisateur;
            }
        }
        return null;
    }

    /**
     * Modifie le compte utilisateur si l'identifiant et le mot de passe correspondent.
     *
     * @param identifiant L'identifiant de connexion de l'utilisateur.
     * @return true si la modification réussit, sinon false.
     */
    public boolean modifierCompte(String identifiant) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (true) {
                if (utilisateur.getMotDePasse().equals(motDePasse)) {
                    return true;
                } else {
                    System.out.println("Erreur : Mot de passe incorrect.");
                    return false;
                }
            }
        }
        System.out.println("Erreur : Identifiant incorrect.");
        return false;
    }

    /**
     * Modifie les informations d'un utilisateur existant.
     *
     * @param utilisateur   L'utilisateur avec les nouvelles informations.
     * @return L'utilisateur modifié ou null si la modification échoue.
     */
    public Utilisateur modifier(Utilisateur utilisateur) {
        boolean modifierr = modifierCompte(utilisateur.getMotDePasse());
        if (modifierr) {
            for (Utilisateur existeUtilisateur : utilisateurs) {
                if (existeUtilisateur.getId() == utilisateur.getId()) {
                    existeUtilisateur.setPseudo(utilisateur.getPseudo());
//                    existeUtilisateur.setIdentifiant(utilisateur.getIdentifiant());
                    existeUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
                    existeUtilisateur.setId(utilisateur.getId());
                    System.out.println("L'utilisateur avec l'identifiant " + " a été modifié.");
                    return existeUtilisateur;
                }
            }
        }
        return null;
    }

    /**
     * Retourne l'identifiant unique de l'utilisateur.
     *
     * @return L'identifiant unique.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de l'utilisateur.
     *
     * @param id L'identifiant unique.
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Retourne le pseudo de l'utilisateur.
     *
     * @return Le pseudo de l'utilisateur.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Définit le pseudo de l'utilisateur.
     *
     * @param pseudo Le pseudo de l'utilisateur.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param motDePasse Le mot de passe de l'utilisateur.
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Retourne la date de création du compte utilisateur.
     *
     * @return La date de création.
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Définit la date de création du compte utilisateur.
     *
     * @param dateCreation La date de création.
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Retourne une chaîne de caractères représentant les détails de l'utilisateur.
     *
     * @return Les détails de l'utilisateur sous forme de chaîne.
     */
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
