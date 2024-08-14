package Dao;

/**
 * Interface définissant les opérations liées à l'éducation et à l'enseignement.
 * Fournit des méthodes pour enseigner, préparer des cours et assister à des réunions.
 */
public interface IEducation {

    /**
     * Enseigne une matière spécifique.
     *
     * @param matiere La matière à enseigner.
     * @return Un message indiquant le résultat de l'opération d'enseignement.
     */
    String emseigner(String matiere);

    /**
     * Prépare un cours pour une session d'enseignement.
     *
     * @param cours Le nom ou la description du cours à préparer.
     * @return Un message indiquant le résultat de l'opération de préparation du cours.
     */
    String preparerCours(String cours);

    /**
     * Assiste à une réunion sur un sujet donné.
     *
     * @param sujet Le sujet de la réunion à laquelle assister.
     * @return Un message indiquant le résultat de l'opération d'assistance à la réunion.
     */
    String assisterReunion(String sujet);
}

