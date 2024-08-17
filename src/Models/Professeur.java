package Models;


import Services.Imp.ProfesseurServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Représente un professeur qui étend la classe Personne et implémente les interfaces ICRUDProfesseur et IEducation.
 * Cette classe gère les informations sur les professeurs, y compris les opérations CRUD, et les interactions liées à l'éducation.
 */
public class Professeur extends Personne {
//    /** Service pour la gestion des opérations sur les professeurs. */
//    ProfesseurServiceImpl professeurService = new ProfesseurServiceImpl();

    /** Liste statique des professeurs. */
    static ArrayList<Professeur> professeurs = new ArrayList<>();

    /** Indique si le professeur est disponible (vacant) ou non. */
    private boolean vacant;

    /** Matière enseignée par le professeur. */
    private String matiereEnseigne;

    /** Nom du prochain cours à enseigner par le professeur. */
    private String prochainCours;

    /** Sujet de la prochaine réunion à laquelle le professeur doit assister. */
    private String sujetProchaineReunion;

    /**
     * Constructeur avec paramètres pour créer un professeur.
     *
     * @param vacant        Indique si le professeur est vacant.
     * @param id            L'identifiant du professeur.
     * @param nom           Le nom du professeur.
     * @param prenom        Le prénom du professeur.
     * @param dateNaissance La date de naissance du professeur.
     * @param ville         La ville de résidence du professeur.
     */
    public Professeur(boolean vacant,String matiereEnseigne,String prochainCours,String sujetProchaineReunion, int id, String nom, String prenom, Date dateNaissance, String ville,int telephone) {
        super(id, nom, prenom, dateNaissance, ville,telephone);
        this.vacant = vacant;
    }

    /**
     * Constructeur par défaut.
     */
    public Professeur() {
        super();
    }

    /**
     * Ajoute un professeur à la liste des professeurs.
     *
     * @param professeur Le professeur à ajouter.
     */
    public static void ajouters(Professeur professeur) {
        professeurs.add(professeur);
    }

    /**
     * Ajoute un professeur à la base de données.
     *
     * @param professeur Le professeur à ajouter.
     * @return Le professeur ajouté ou null.
     */

    public Professeur ajouter(Professeur professeur) {
        return null;
    }

    /**
     * Modifie les informations d'un professeur existant.
     *
     * @param professeur Le professeur avec les nouvelles informations.
     * @return Le professeur modifié ou null.
     */

    public Professeur modifier(Professeur professeur) {
        return null;
    }

    /**
     * Supprime un professeur de la liste par son identifiant.
     *
     * @param identifiant L'identifiant du professeur à supprimer.
     */
//    @Override
    public void supprimer(int identifiant) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < professeurs.size(); i++) {
            Professeur professeur = professeurs.get(i);
            if (professeur.getId() == identifiant) {
                String nom = professeur.getNom();
                String prenom = professeur.getPrenom();
                professeurs.remove(i);
                System.out.println("Le professeur " + nom + " " + prenom + " a été supprimé.");
                return;
            }
        }
        System.out.println("Aucun professeur trouvé avec l'identifiant " + identifiant);
    }

    /**
     * Retourne la liste de tous les professeurs.
     *
     * @return Une liste de professeurs.
     */
//    @Override
    public List<Professeur> obtenirProfesseurs() {
        return professeurs;
    }

    /**
     * Méthode vide pour obtenir un professeur par son identifiant (implémentation non fournie).
     *
     * @param identifiant L'identifiant du professeur à obtenir.
     */
//    @Override
    public void Obtenir(int identifiant) {
        // Implémentation à fournir
    }

    /**
     * Met à jour les informations d'un professeur.
     *
     * @param professeur Le professeur avec les nouvelles informations.
     */
    public void mettreAJour(Professeur professeur) {
        // Code pour mettre à jour les informations du professeur
    }


    /**
     * Retourne une chaîne de caractères représentant les détails du professeur.
     *
     * @return Les détails du professeur sous forme de chaîne.
     */
    @Override
    public String toString() {
        return "Entite.Professeur{" +
                "vacant=" + vacant + ", tel = " + super.getTelephone() + ", dateNaissance= " + super.getDateNaissance() + ", ville= " + super.getVille() + ", nom= " + super.getNom() + ", prenom= " + super.getPrenom() + "}";
    }

    /**
     * Enseigne une matière.
     *
     * @param matiere La matière à enseigner.
     * @return Une chaîne de caractères indiquant la matière enseignée.
     */
//    @Override
    public String emseigner(String matiere) {
        return "Enseigne la matière " + matiere;
    }

    /**
     * Prépare le contenu d'un cours.
     *
     * @param cours Le sujet du cours à préparer.
     * @return Une chaîne de caractères indiquant le sujet du cours préparé.
     */
//    @Override
    public String preparerCours(String cours) {
        return "Prépare le contenu d'un cours sur le sujet " + cours;
    }

    /**
     * Assiste à une réunion sur un sujet donné.
     *
     * @param sujet Le sujet de la réunion.
     * @return Une chaîne de caractères indiquant le sujet de la réunion.
     */
//    @Override
    public String assisterReunion(String sujet) {
        return "Doit assister à une réunion sur " + sujet;
    }

    /**
     * Récupère un professeur spécifique par son identifiant.
     *
     * @param identifiant L'identifiant du professeur à récupérer.
     * @return Le professeur correspondant à l'identifiant, ou null s'il n'est pas trouvé.
     */
    public Professeur ObtenirProfesseur(int identifiant) {
        for (Professeur professeur : professeurs) {
            if (professeur.getId() == identifiant) {
                return professeur;
            }
        }
        return null;
    }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public String getMatiereEnseigne() {
        return matiereEnseigne;
    }

    public void setMatiereEnseigne(String matiereEnseigne) {
        this.matiereEnseigne = matiereEnseigne;
    }

    public String getProchainCours() {
        return prochainCours;
    }

    public void setProchainCours(String prochainCours) {
        this.prochainCours = prochainCours;
    }

    public String getSujetProchaineReunion() {
        return sujetProchaineReunion;
    }

    public void setSujetProchaineReunion(String sujetProchaineReunion) {
        this.sujetProchaineReunion = sujetProchaineReunion;
    }
}
