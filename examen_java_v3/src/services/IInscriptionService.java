package services;

import java.util.List;

import entities.Inscription;

public interface IInscriptionService {
    
    void faireInscription(Inscription inscription);

    List<Inscription> listerInscriptionParAnneeScolaire(String anneScolaire);

    List<Inscription> listerInscriptionParAnneeScolaire(String anneScolaire, int idClasse);

    Inscription rechercherInscriptionParMatriculeEtudiant(String matricule);
}
