package Dao.impl;


import Dao.IProfesseurDAO;
import Models.Professeur;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProfesseurDaoImpl implements IProfesseurDAO {

    Instant debutSession = Instant.now();

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Professeur> professeurs = new ArrayList<>();

    @Override
    public Professeur ajouter(Professeur professeur) {
        professeurs.add(professeur);
        System.out.println("Professeur ajouté avec succès !");
        return professeur;
    }

    @Override
    public Professeur modifier(Professeur professeur) {
        for (int i = 0; i < professeurs.size(); i++) {
            if (professeurs.get(i).getId() == professeur.getId()) {
                professeurs.set(i, professeur);
                System.out.println("Professeur modifié avec succès !");
                return professeur;
            }
        }
        System.out.println("Aucun professeur trouvé avec cet ID.");
        return null;
    }

    @Override
    public void supprimer(int id) {
        boolean removed = professeurs.removeIf(professeur -> professeur.getId() == id);
        if (removed) {
            System.out.println("Professeur supprimé avec succès !");
        } else {
            System.out.println("Aucun professeur trouvé avec cet ID.");
        }
    }

    @Override
    public List<Professeur> obtenirProfesseurs() {
        return new ArrayList<>(professeurs); // Retourner une copie pour éviter les modifications externes
    }

    @Override
    public Professeur obtenir(int id) {
        for (Professeur professeur : professeurs) {
            if (professeur.getId() == id) {
                return professeur;
            }
        }
        System.out.println("Aucun professeur trouvé avec cet ID.");
        return null;
    }
}
