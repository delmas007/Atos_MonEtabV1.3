package Services;

import java.time.Instant;

public interface IUtilisateurService {
    void ajouterUtilisateur();
    void supprimerUtilisateur();
    void modifierUtilisateur(Instant debut);
    void listerUtilisateurs(Instant debut);
}
