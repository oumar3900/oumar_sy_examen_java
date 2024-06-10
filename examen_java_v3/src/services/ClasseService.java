package services;

import java.util.List;

import entities.Classe;
// import repositories.ClasseRepository;
import repositories.IClasse;

public class ClasseService implements IClasseService{

     IClasse classeRepository;

     public ClasseService(IClasse classeRepository) {
        this.classeRepository = classeRepository;
    }

    public void ajouterClasse(Classe classe){
        classeRepository.insertClasse(classe);
    }
   
    public  List<Classe>listerClasse(){
        return classeRepository.selectAllClasse();
    }

    public  Classe findClasseById(int id){
        return classeRepository.selectClasseById(id);
    }
}
