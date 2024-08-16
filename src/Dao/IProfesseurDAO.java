package Dao;



import Models.Professeur;

import java.util.List;

public interface IProfesseurDAO {
    Professeur ajouter(Professeur professeur);
    Professeur modifier(Professeur professeur);
    void supprimer(int id);
    List<Professeur> obtenirProfesseurs();
    Professeur obtenir(int id);
}
