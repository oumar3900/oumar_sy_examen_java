package services;


import entities.Etudiant;
// import repositories.EtudiantRepository;
import repositories.IEtudiant;

public class EtudiantService implements IEtudiantService{
    
     IEtudiant  etudiantRepository;

    public EtudiantService(IEtudiant etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    public void ajouterEtudiant(Etudiant etudiant){
        etudiantRepository.insert(etudiant);
    }

    
}
