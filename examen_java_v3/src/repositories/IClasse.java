package repositories;

import java.util.List;

import entities.Classe;

public interface IClasse {
    List<Classe> selectAllClasse();
    void insertClasse(Classe classe);
    Classe selectClasseById(int id);
}
