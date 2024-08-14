package Dao;

import Models.Professeur;

import java.util.List;

import java.util.List;

/**
 * Interface définissant les opérations de gestion des professeurs.
 * Fournit des méthodes pour ajouter, modifier, supprimer et obtenir des professeurs.
 */
public interface ICRUDProfesseur {

    /**
     * Ajoute un nouveau professeur.
     *
     * @param professeur Le professeur à ajouter.
     * @return Le professeur ajouté avec éventuellement des informations supplémentaires (comme un identifiant généré).
     */
    Professeur ajouter(Professeur professeur);

    /**
     * Modifie les informations d'un professeur existant.
     *
     * @param professeur Le professeur avec les informations modifiées.
     * @return Le professeur modifié.
     */
    Professeur modifier(Professeur professeur);

    /**
     * Supprime un professeur en fonction de son identifiant.
     *
     * @param identifiant L'identifiant du professeur à supprimer.
     */
    void supprimer(int identifiant);

    /**
     * Obtient la liste de tous les professeurs.
     *
     * @return La liste des professeurs.
     */
    List<Professeur> obtenirProfesseurs();

    /**
     * Obtient les détails d'un professeur spécifique.
     *
     * @param identifiant L'identifiant du professeur dont les détails doivent être obtenus.
     */
    void Obtenir(int identifiant);
}

