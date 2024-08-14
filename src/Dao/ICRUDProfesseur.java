package Dao;

import Models.Professeur;

import java.util.List;

public interface ICRUDProfesseur {
    Professeur ajouter(Professeur professeur);
    Professeur modifier(Professeur professeur);
    void supprimer(int identifiant);
    List<Professeur> obtenirProfesseurs();
    void Obtenir(int identifiant);
}
