package Models;

import Interface.ICRUDProfesseur;
import Interface.IEducation;
import Methode.MethodeProfesseur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Professeur extends Personne implements ICRUDProfesseur, IEducation {
    static ArrayList<Professeur> professeurs = new ArrayList<>();
    private boolean vacant;
    private String matiereEnseigne;
    private String prochainCours;
    private String sujetProchaineReunion;

    public Professeur(boolean vacant, int id, String nom, String prenom, Date dateNaissance, String ville) {
        super(id=id,nom=nom,prenom=prenom,dateNaissance=dateNaissance,ville=ville);
        this.vacant = vacant;
    }
    public Professeur() {
    }
    public static void ajouters(Professeur professeur) {
        professeurs.add(professeur);
    }

    @Override
    public Professeur ajouter(Professeur professeur) {
        return null;
    }

    @Override
    public Professeur modifier(Professeur professeur) {
        return null;
    }

    @Override
    public void supprimer(int identifiant) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < professeurs.size(); i++) {
            Professeur professeur = professeurs.get(i);
            if (professeur.getId() == identifiant) {
                String nom = professeur.getNom();
                String prenom = professeur.getPrenom();
                professeurs.remove(i);
                System.out.println("Le professeur " + nom +" "+ prenom + " a été supprimé.");
                return;
            }
        }
        System.out.println("Aucun professeur trouvé avec l'identifiant " + identifiant);

    }

    @Override
    public List<Professeur> obtenirProfesseurs() {
        return professeurs;
    }

    @Override
    public void Obtenir(int identifiant) {

    }

    public void mettreAJour(Professeur professeur) {
    }
    public static void dernier() {
        Scanner scanner2 = new Scanner(System.in);
        if (!professeurs.isEmpty()) {
            Professeur dernierProfesseur = professeurs.get(professeurs.size() - 1);
            System.out.println("Dernier professeur ajouté : " + dernierProfesseur.toString());
        } else {
            System.out.println("Aucun professeur n'a été ajouté.");
            System.out.println("Voulez-vous ajouter un professeur ? (oui/non)");
            String reponse = scanner2.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                MethodeProfesseur.ajouterProfesseur();
            }
        }
    }

    @Override
    public String toString() {
        return "Entite.Professeur{" +
                "vacant=" + vacant +", id= " + super.getId() + ", dateNaissance= " + super.getDateNaissance() + ", ville= " + super.getVille() + ", nom= " + super.getNom() + ", prenom= " + super.getPrenom()+ "}";
    }

    @Override
    public String emseigner(String matiere) {
        return "";
    }

    @Override
    public String preparerCours(String cours) {
        return "";
    }

    @Override
    public String assisterReunion(String sujet) {
        return "";
    }
    public Professeur ObtenirProfesseur(int identifiant) {
        for (Professeur professeur : professeurs) {
            if (professeur.getId() == identifiant) {
                return professeur;
            }
        }

        return null;
    }
}
