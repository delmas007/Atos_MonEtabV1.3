package Services.Imp;


import Dao.impl.EleveDaoImpl;
import Models.Eleve;
import Other.Menu.MenuEleve;
import Services.IEleveService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Classe d'implémentation du service EleveService qui gère les opérations
 * liées aux élèves, telles que l'ajout, la suppression, la modification,
 * et l'affichage des informations des élèves.
 */
public class EleveServiceImpl implements IEleveService {
    Eleve elevee = new Eleve();
      EleveDaoImpl eleveDAO = new EleveDaoImpl();

    public EleveServiceImpl() {
    }


    @Override
    public Eleve save(Eleve eleve) {
        return eleveDAO.ajouter(eleve);
    }

    @Override
    public Eleve update(Eleve eleve) {
        return eleveDAO.modifier(eleve);
    }

    @Override
    public void delete(int id) {
        eleveDAO.supprimer(id);
    }

    @Override
    public List<Eleve> getAll() {
        return eleveDAO.obtenirEleves();
    }

    @Override
    public Eleve getOne(int id) {
        return eleveDAO.obtenir(id);
    }

}
