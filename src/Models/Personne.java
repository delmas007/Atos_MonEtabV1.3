package Models;

import java.util.Date;

public class Personne {
    private int id ;
    private Date dateNaissance ;
    private String ville ;
    private String nom ;
    private String prenom ;

    public Personne(int id,String nom, String prenom, Date dateNaissance, String ville ) {
        this.id = id;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Personne( ) {
    }
    public int obtenirAge() {
        return 0;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
