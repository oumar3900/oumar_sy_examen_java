package services;

import java.util.List;

import entities.Inscription;
import repositories.IInscription;
// import repositories.InscriptionRepository;

public class InscriptionService implements IInscriptionService{

     IInscription inscriptionRepository;
    
    public InscriptionService(IInscription inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    public void faireInscription(Inscription inscription){
        inscriptionRepository.insert(inscription);
    }

    public  List<Inscription> listerInscriptionParAnneeScolaire(String anneScolaire){
        return inscriptionRepository.selectAllInscriptionByAnneeScolaire(anneScolaire);
    }
    public  List<Inscription> listerInscriptionParAnneeScolaire(String anneScolaire, int idClasse){
        return inscriptionRepository.selectAllInscriptionByAnneeScolaire(anneScolaire,idClasse);
    }

    public Inscription rechercherInscriptionParMatriculeEtudiant(String matricule){
        return inscriptionRepository.selectInscriptionByMatricule(matricule);
    }
    
}
