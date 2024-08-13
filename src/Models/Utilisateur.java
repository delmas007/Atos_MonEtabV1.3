package Models;

import java.time.Instant;
import java.util.*;

public class Utilisateur {
    static ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    private int id ;
    private String identifiant;
    private String pseudo;
    private String motDePasse;
    private Date dateCreation;

    public Utilisateur(int id, String identifiant, String pseudo, String motDePasse, Date dateCreation) {
        this.id = id;
        this.identifiant = identifiant;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.dateCreation = dateCreation;
    }

    public Utilisateur() {
    }

    public static boolean authentifier(String identifiant, String motDePasse) {
        return identifiant.equals("admin") && motDePasse.equals("admin");
    }

    public static boolean ajouterCompte(String identifiant, String motDePasse) {
        Random ramdom = new Random();
        Date dateCreation = Date.from(Instant.now());
        int id = ramdom.nextInt(9000) + 1000;
        Utilisateur utilisateur = new Utilisateur(id, identifiant, "pseudo", motDePasse, dateCreation);
        utilisateurs.add(utilisateur);
        return true;
    }

    public boolean supprimerCompte(String identifiant, String motDePasse) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < utilisateurs.size(); i++) {
            Utilisateur utilisateur = utilisateurs.get(i);
            if (utilisateur.getIdentifiant() == identifiant) {
                if (utilisateur.getMotDePasse() == motDePasse) {
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

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurs;
    }

    public Utilisateur ObtenirUtilisateur(String identifiant) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getIdentifiant() == identifiant) {
                return utilisateur;
            }
        }

        return null;
    }

    public boolean modifierCompte(String identifiant, String motDePasse) {
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getIdentifiant().equals(identifiant)) {
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
    public Utilisateur modifier(Utilisateur utilisateur) {
        boolean modifierr = modifierCompte(utilisateur.getIdentifiant(), utilisateur.getMotDePasse());
        if (modifierr) {
            for (Utilisateur existeUtilisateur : utilisateurs) {
                if (existeUtilisateur.getId() == utilisateur.getId()) {
                    existeUtilisateur.setPseudo(utilisateur.getPseudo());
                    existeUtilisateur.setIdentifiant(utilisateur.getIdentifiant());
                    existeUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
                    existeUtilisateur.setId(utilisateur.getId());
                    System.out.println("L'utilisateur avec l'identifiant " + utilisateur.getIdentifiant() + " a été modifié.");
                    return existeUtilisateur;
                }

            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", identifiant='" + identifiant + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
