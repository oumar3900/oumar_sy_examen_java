package services;

import java.util.List;

import entities.Professeur;

public interface IProfesseurService {
    void ajouterUnProfesseur(Professeur professeur);

      
    List<Professeur>listerProfesseurs();


    Professeur findProfesseurById(int id);
}
