package Models;

import Dao.ICUDEleve;
import Services.Imp.EleveServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Représente un élève qui étend la classe Personne et implémente l'interface ICUDEleve.
 * Cette classe gère les informations sur les élèves, y compris les opérations CRUD.
 */
public class Eleve extends Personne implements ICUDEleve {
    /** Service pour la gestion des opérations sur les élèves. */
    EleveServiceImpl eleveService = new EleveServiceImpl();

    /** Liste statique des élèves. */
    static ArrayList<Eleve> eleves = new ArrayList<>();

    /** Âge de l'élève. */
    private int age;

    /** Genre de l'élève. */
    private String genre;

    /** Classe de l'élève. */
    private String classe;

    /** Matricule de l'élève. */
    private String matricule;

    /**
     * Constructeur avec paramètres pour créer un élève.
     *
     * @param id            L'identifiant de l'élève.
     * @param nom           Le nom de l'élève.
     * @param prenom        Le prénom de l'élève.
     * @param age           L'âge de l'élève.
     * @param genre         Le genre de l'élève.
     * @param dateNaissance La date de naissance de l'élève.
     * @param ville         La ville de résidence de l'élève.
     */
    public Eleve(int id, String nom, String prenom, int age, String genre, Date dateNaissance, String ville) {
        super(id, nom, prenom, dateNaissance, ville);
        this.age = age;
        this.genre = genre;
    }

    /**
     * Constructeur par défaut.
     */
    public Eleve() {
        super();
    }

    /**
     * Ajoute un élève à la liste des élèves.
     *
     * @param eleve L'élève à ajouter.
     */
    public static void ajouters(Eleve eleve) {
        eleves.add(eleve);
    }

    /**
     * Met à jour les informations d'un élève.
     *
     * @param eleve L'élève avec les nouvelles informations.
     */
    public void mettreAJour(Eleve eleve) {
        // Code pour mettre à jour les informations de l'élève
    }

    /**
     * Affiche le dernier élève ajouté à la liste.
     * Si aucun élève n'est présent, demande si un élève doit être ajouté.
     */
    public void dernier() {
        if (!eleves.isEmpty()) {
            Eleve dernierEleve = eleves.get(eleves.size() - 1);
            System.out.println("Dernier élève ajouté : " + dernierEleve.toString());
        } else {
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Aucun élève n'a été ajouté.");
            System.out.println("Voulez-vous ajouter un élève ? (oui/non)");
            String reponse = scanner2.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                eleveService.ajouterEleve();
            }
        }
    }

    /**
     * Retourne une chaîne de caractères représentant les détails de l'élève.
     *
     * @return Les détails de l'élève sous forme de chaîne.
     */
    public String toString() {
        return "age= " + age + ", genre= " + genre + ", id= " + super.getId() + ", dateNaissance= " + super.getDateNaissance() + ", ville= " + super.getVille() + ", nom= " + super.getNom() + ", prenom= " + super.getPrenom();
    }

    /**
     * Ajoute un élève à la base de données.
     *
     * @param eleve L'élève à ajouter.
     * @return L'élève ajouté ou null.
     */
    public Eleve ajouter(Eleve eleve) {
        return null;
    }

    /**
     * Modifie les informations d'un élève existant dans la liste.
     *
     * @param eleve L'élève avec les nouvelles informations.
     * @return L'élève modifié, ou null si l'élève n'a pas été trouvé.
     */
    public Eleve modifier(Eleve eleve) {
        for (int i = 0; i < eleves.size(); i++) {
            Eleve existeEleve = eleves.get(i);
            if (existeEleve.getId() == eleve.getId()) {
                existeEleve.setNom(eleve.getNom());
                existeEleve.setPrenom(eleve.getPrenom());
                existeEleve.setDateNaissance(eleve.getDateNaissance());
                existeEleve.setId(eleve.getId());
                System.out.println("L'élève avec l'identifiant " + eleve.getId() + " a été modifié.");
                return existeEleve;
            }
        }
        return null;
    }

    /**
     * Supprime un élève de la liste par son identifiant.
     *
     * @param identifiant L'identifiant de l'élève à supprimer.
     */
    public void supprimer(int identifiant) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < eleves.size(); i++) {
            Eleve eleve = eleves.get(i);
            if (eleve.getId() == identifiant) {
                String nom = eleve.getNom();
                String prenom = eleve.getPrenom();
                eleves.remove(i);
                System.out.println("L'élève " + nom + " " + prenom + " a été supprimé.");
                return;
            }
        }
        System.out.println("Aucun élève trouvé avec l'identifiant " + identifiant);
    }

    /**
     * Retourne la liste de tous les élèves.
     *
     * @return Une liste d'élèves.
     */
    @Override
    public List<Eleve> obtenirEleve() {
        return eleves;
    }

    /**
     * Méthode vide pour obtenir un élève par son identifiant (implémentation non fournie).
     *
     * @param identifiant L'identifiant de l'élève à obtenir.
     */
    @Override
    public void Obtenir(int identifiant) {
        // Implémentation à fournir
    }

    /**
     * Récupère un élève spécifique par son identifiant.
     *
     * @param identifiant L'identifiant de l'élève à récupérer.
     * @return L'élève correspondant à l'identifiant, ou null s'il n'est pas trouvé.
     */
    public Eleve ObtenirEleve(int identifiant) {
        for (Eleve eleve : eleves) {
            if (eleve.getId() == identifiant) {
                return eleve;
            }
        }
        return null;
    }
}