package entities;

public class Inscription {
    private int  id;
    private String anneScolaire;
    Etudiant etudiant;
    Classe classe;
    public Classe getClasse() {
        return classe;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Inscription() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAnneScolaire() {
        return anneScolaire;
    }
    public void setAnneScolaire(String anneScolaire) {
        this.anneScolaire = anneScolaire;
    }
}
