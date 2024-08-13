package Models;

import Interface.IEleveDao;
import Methode.MethodeEleve;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Eleve extends Personne  {
    static ArrayList<Eleve> eleves = new ArrayList<>();
    private int age ;
    private String genre ;
    private String classe ;
    private String matricule ;

    public Eleve(int id, String nom, String prenom, int age, String genre, Date dateNaissance,String ville) {
        super(id=id,nom=nom,prenom=prenom,dateNaissance=dateNaissance,ville=ville);
        this.age = age;
        this.genre = genre;
    }
    public Eleve() {
        super();
    }
    public static void ajouters(Eleve eleve) {
        eleves.add(eleve);
    }
    public void mettreAJour(Eleve eleve) {
    }
    public static void dernier() {
        if (!eleves.isEmpty()) {
            Eleve dernierEleve = eleves.get(eleves.size() - 1);
            System.out.println("Dernier élève ajouté : " + dernierEleve.toString());
        } else {
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("Aucun élève n'a été ajouté.");
            System.out.println("Voulez-vous ajouter un élève ? (oui/non)");
            String reponse = scanner2.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                MethodeEleve.ajouterEleve();
            }
        }
    }


    public String toString() {
        return  "age= " + age + ", genre= " + genre+ ", id= " + super.getId() + ", dateNaissance= " + super.getDateNaissance() + ", ville= " + super.getVille() + ", nom= " + super.getNom() + ", prenom= " + super.getPrenom();
    }
    public Eleve ajouter(Eleve eleve) {
        return null;
    }

    public Eleve modifier(Eleve eleve) {
        for(int i = 0; i < eleves.size(); i++){
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

    public void supprimer(int identifiant) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < eleves.size(); i++) {
            Eleve eleve = eleves.get(i);
            if (eleve.getId() == identifiant) {
                String nom = eleve.getNom();
                String prenom = eleve.getPrenom();
                eleves.remove(i);
                System.out.println("L'élève " + nom +" "+ prenom + " a été supprimé.");
                return;
            }
        }
        System.out.println("Aucun élève trouvé avec l'identifiant " + identifiant);

    }

    public Eleve ObtenirEleve(int identifiant) {
        for (Eleve eleve : eleves) {
            if (eleve.getId() == identifiant) {
                return eleve;
            }
        }

        return null;
    }
}
