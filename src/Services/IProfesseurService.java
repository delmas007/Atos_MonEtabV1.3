package Services;

import java.time.Instant;

public interface IProfesseurService {
    void ajouterProfesseur();
    void supprimerProfesseur();
    void modifierProfesseur(Instant debut);
    void listerProfesseur(Instant debut);
}
