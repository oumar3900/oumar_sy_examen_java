package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Classe;
import entities.Filiere;
import entities.Niveau;
import entities.Professeur;
import entities.ProfesseurDeClasse;

public class ProfesseurRepository extends Database implements IProfesseur{
    private final String SQL_INSERT="INSERT INTO professeur (nci,nomcomplet,grade ) VALUES (?,?,?);";
    private final  String SQL_SELECT_ALL_PROFESSEUR="select * from professeur" ;
    private final String SQL_LAST_VALUE_INSERT="SELECT Max(id_professeur) as max FROM professeur";
    private final String SQL_SELECT_PROFESSEUR_BY_ID="SELECT * FROM professeur p JOIN professeurDeClasse pc ON p.id_professeur = pc.id_professeur JOIN classe c ON pc.id_classe = c.id_classe WHERE p.id_professeur = ?";

    public void insert(Professeur  professeur){
        try {
             openConnexion();
             initPreparedStatement(SQL_INSERT);
             statement.setInt(1,professeur.getNci());
             statement.setString(2,professeur.getNomComplet());
             statement.setString(3,professeur.getGrade());
             executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public  Professeur selectLastProfesseur(){
        Professeur professeur=null;
        try {
            openConnexion();
            initPreparedStatement(SQL_LAST_VALUE_INSERT);
     
            ResultSet rs = executeSelect();
            while (rs.next()) {
                professeur=new Professeur(); 
                professeur.setId(rs.getInt("max")); 
             
            }
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return professeur;
      }

      public  List<Professeur> selectAllProfesseurs(){
         List<Professeur> professeurs=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL_PROFESSEUR);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
               Professeur professeur=new Professeur();
               professeur.setId(rs.getInt("id_professeur"));
               professeur.setNci(rs.getInt("nci"));
               professeur.setNomComplet(rs.getString("nomcomplet"));
               professeur.setGrade(rs.getString("grade"));
               professeurs.add(professeur);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  professeurs;
    }

      public  Professeur selectProfesseurById(int id){
        Professeur professeur=null;
        List<ProfesseurDeClasse> ListeClasseDuProf = new ArrayList<>();
        try {
            openConnexion();
            initPreparedStatement(SQL_SELECT_PROFESSEUR_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            professeur=new Professeur(); 
            while (rs.next()) {
                //Produits
                      Classe classe=new Classe();
                        classe.setId(rs.getInt("id_classe"));
                        int niveau=rs.getInt("niveau");
                        int filiere=rs.getInt("filiere");
                        classe.setNiveaux(Niveau.values()[niveau]);
                        classe.setFiliere(Filiere.values()[filiere]);

                 //Professeur 
                  
                   professeur.setId(rs.getInt("id_professeur")); 
                   professeur.setNci(rs.getInt("nci")); 
                   professeur.setNomComplet(rs.getString("nomComplet"));
                      professeur.setGrade(rs.getString("grade"));

                //Professeur De classer 
                 ProfesseurDeClasse professeurDeClasse=new ProfesseurDeClasse();
                 professeurDeClasse.setId(rs.getInt("id_professeurDeClasse"));
                 professeurDeClasse.setClasse(classe);
                 professeurDeClasse.setProfesseur(professeur);
                 ListeClasseDuProf.add(professeurDeClasse);
                 professeur.setProfesseurDeClasses(ListeClasseDuProf);
            }
       } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return professeur;
      }
}
