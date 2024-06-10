package services;

import java.util.List;

import entities.Professeur;
import entities.ProfesseurDeClasse;
import repositories.IProfesseur;
import repositories.IProfesseurDeClasse;
// import repositories.ProfesseurDeClasseRepository;
// import repositories.ProfesseurRepository;

public class ProfesseurService implements IProfesseurService{
    IProfesseur professeurRepository;
    IProfesseurDeClasse professeurDeClasseRepository;
    
    public ProfesseurService(IProfesseur professeurRepository) {
      this.professeurRepository = professeurRepository;
   }
   public ProfesseurService(IProfesseur professeurRepository,
         IProfesseurDeClasse professeurDeClasseRepository) {
      this.professeurRepository = professeurRepository;
      this.professeurDeClasseRepository = professeurDeClasseRepository;
   }
   public  void ajouterUnProfesseur(Professeur professeur){
        //Transaction
         professeurRepository.insert(professeur);
         Professeur lastProfesseur= professeurRepository.selectLastProfesseur();
         List<ProfesseurDeClasse> professeurDeClasses = professeur.getProfesseurDeClasses();
         for (ProfesseurDeClasse pc  : professeurDeClasses) {
            pc.setProfesseur(lastProfesseur);
            professeurDeClasseRepository.insert(pc);
         }

      }
      public  List<Professeur>listerProfesseurs(){
         return professeurRepository.selectAllProfesseurs();
     }


      public  Professeur findProfesseurById(int id){
          return professeurRepository.selectProfesseurById(id);
      }
}
