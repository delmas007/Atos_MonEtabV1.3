package Services.Imp;


import Dao.impl.EleveDaoImpl;
import Dao.impl.ProfesseurDaoImpl;
import Models.Professeur;
import Other.Menu.MenuProfesseur;
import Services.IProfesseurService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * Classe d'implémentation du service ProfesseurService qui gère les opérations
 * liées aux professeurs, telles que l'ajout, la suppression, la modification,
 * et l'affichage des informations des professeurs.
 */
public class ProfesseurServiceImpl implements IProfesseurService {

    ProfesseurDaoImpl professeurDAO = new ProfesseurDaoImpl();

//    private final ProfesseurDaoImpl professeurDAO;
//
//    public ProfesseurServiceImpl(ProfesseurDaoImpl professeurDAO) {
//        this.professeurDAO = professeurDAO;
//    }

    @Override
    public Professeur save(Professeur Professeur) {
        return professeurDAO.ajouter(Professeur);
    }

    @Override
    public Professeur update(Professeur Professeur) {
        return professeurDAO.modifier(Professeur);
    }

    @Override
    public void delete(int id) {
        professeurDAO.supprimer(id);
    }

    @Override
    public List<Professeur> getAll() {
        return professeurDAO.obtenirProfesseurs();
    }

    @Override
    public Professeur getOne(int id) {
        return professeurDAO.obtenir(id);
    }


}
