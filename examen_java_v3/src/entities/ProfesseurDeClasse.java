package entities;

public class ProfesseurDeClasse {
    private int id;
    Professeur professeur;
    
    Classe classe;
    
    
    public ProfesseurDeClasse() {
    }

    public ProfesseurDeClasse(Classe classe) {
        this.classe = classe;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Professeur getProfesseur() {
        return professeur;
    }
    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Classe = "+this.classe+"Professeur = "+this.professeur;
    }
}
