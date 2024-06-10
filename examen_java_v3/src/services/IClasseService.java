package services;

import java.util.List;

import entities.Classe;

public interface IClasseService {
    
    void ajouterClasse(Classe classe);
   
    public  List<Classe>listerClasse();

    public  Classe findClasseById(int id);
}
