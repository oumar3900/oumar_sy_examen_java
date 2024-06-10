package repositories;

import java.util.List;

import entities.Inscription;

public interface IInscription {
    void insert(Inscription inscription);
    List<Inscription> selectAllInscriptionByAnneeScolaire(String anneScolaire);
    List<Inscription> selectAllInscriptionByAnneeScolaire(String anneScolaire,int idClasse);
    Inscription selectInscriptionByMatricule(String mat);
}
