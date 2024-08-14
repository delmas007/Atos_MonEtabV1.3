package Dao;

import Models.Eleve;

import java.util.List;

import java.util.List;

/**
 * Interface définissant les opérations de gestion des élèves.
 * Fournit des méthodes pour ajouter, modifier, supprimer et obtenir des élèves.
 */
public interface ICUDEleve {

    /**
     * Ajoute un nouvel élève.
     *
     * @param eleve L'élève à ajouter.
     * @return L'élève ajouté avec éventuellement des informations supplémentaires (comme un identifiant généré).
     */
    Eleve ajouter(Eleve eleve);

    /**
     * Modifie les informations d'un élève existant.
     *
     * @param eleve L'élève avec les informations modifiées.
     * @return L'élève modifié.
     */
    Eleve modifier(Eleve eleve);

    /**
     * Supprime un élève en fonction de son identifiant.
     *
     * @param identifiant L'identifiant de l'élève à supprimer.
     */
    void supprimer(int identifiant);

    /**
     * Obtient la liste de tous les élèves.
     *
     * @return La liste des élèves.
     */
    List<Eleve> obtenirEleve();

    /**
     * Obtient les détails d'un élève spécifique.
     *
     * @param identifiant L'identifiant de l'élève dont les détails doivent être obtenus.
     */
    void Obtenir(int identifiant);
}

