package Dao;

import Models.Eleve;

import java.util.List;

public interface ICUDEleve {
    Eleve ajouter(Eleve eleve);
    Eleve modifier(Eleve eleve);
    void supprimer(int identifiant);
    List<Eleve> obtenirEleve();
    void Obtenir(int identifiant);
}
