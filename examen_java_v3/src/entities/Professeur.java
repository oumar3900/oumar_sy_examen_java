package entities;

import java.util.List;

public class Professeur {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int nci;
    private String nomComplet;
    private String grade;
    List<ProfesseurDeClasse> professeurDeClasses;
    
    public List<ProfesseurDeClasse> getProfesseurDeClasses() {
        return professeurDeClasses;
    }
    public void setProfesseurDeClasses(List<ProfesseurDeClasse> professeurDeClasses) {
        this.professeurDeClasses = professeurDeClasses;
    }
    public Professeur() {
    }
    public int getNci() {
        return nci;
    }
    public void setNci(int nci) {
        this.nci = nci;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Nci = "+this.nci+"- Nom = "+this.nomComplet+"- Grade = "+this.grade;
    }
}
