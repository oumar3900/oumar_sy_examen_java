package repositories;

import java.util.List;

import entities.Professeur;

public interface IProfesseur {
    void insert(Professeur  professeur);
    Professeur selectLastProfesseur();
    List<Professeur> selectAllProfesseurs();
    Professeur selectProfesseurById(int id);
}
