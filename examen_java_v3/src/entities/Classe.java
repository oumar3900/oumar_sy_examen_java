package entities;

import java.util.List;

public class Classe {
    private int id;
    private Filiere filiere;
    private Niveau niveaux;
    List<Inscription> inscriptions;
    List<ProfesseurDeClasse> professeurDeClasses;
    public List<ProfesseurDeClasse> getProfesseurDeClasses() {
        return professeurDeClasses;
    }
    public void setProfesseurDeClasses(List<ProfesseurDeClasse> professeurDeClasses) {
        this.professeurDeClasses = professeurDeClasses;
    }
    public List<Inscription> getInscriptions() {
        return inscriptions;
    }
    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
    public Classe() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Filiere getFiliere() {
        return filiere;
    }
    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
    public Niveau getNiveaux() {
        return niveaux;
    }
    public void setNiveaux(Niveau niveaux) {
        this.niveaux = niveaux;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "classe ="+this.niveaux+"-"+this.filiere;
    }
}
