package Services;

import java.time.Instant;

public interface IEleveService {
    void ajouterEleve();
    void supprimerEleve();
    void modifierEleve(Instant debut);
    void listerEleves(Instant debut);
}
