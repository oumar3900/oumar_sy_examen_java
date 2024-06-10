package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entities.Etudiant;
import entities.Inscription;

public class InscriptionRepository extends Database implements IInscription{
  private final  String SQL_INSERT="INSERT INTO Inscription (id, anneScolaire, matricule, id_classe) VALUES (?,?,?,?)";
  private final  String SQL_SELECT_ALL_PAR_ANNEE="select * from inscription i, etudiant e where i.matricule=e.matricule and i.anneScolaire like ?";
  private final  String SQL_SELECT_ALL_PAR_ANNEE_CLASSE="select * from inscription i, etudiant e where i.matricule=e.matricule and i.anneScolaire like ? and i.id_classe like ?";
  private final  String SQL_SELECT_BY_MATRICULE="Select * from inscription where matricule like ? " ;
  public  void insert(Inscription inscription){
    openConnexion();
    try {
        initPreparedStatement(SQL_INSERT);
        statement.setInt(1, inscription.getId());
        statement.setString(2, inscription.getAnneScolaire());
        statement.setString(3, inscription.getEtudiant().getMatricule());
        statement.setInt(4, inscription.getClasse().getId());
        int nbreLigne=executeUpdate();
        closeConnexion();
      } catch (SQLException e) {
      e.printStackTrace();
      }
      }

      public  List<Inscription> selectAllInscriptionByAnneeScolaire(String anneScolaire){
         List<Inscription> inscriptions=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL_PAR_ANNEE);
           statement.setString(1, anneScolaire);
           
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
                Inscription inscription=new Inscription();
                inscription.setId(rs.getInt("id"));
                inscription.setAnneScolaire(rs.getString("anneScolaire"));
                Etudiant etudiant=new Etudiant();
                etudiant.setMatricule(rs.getString("matricule"));
                etudiant.setNomComplet(rs.getString("nomComplet"));
                etudiant.setTuteur(rs.getString("tuteur"));
                inscription.setEtudiant(etudiant);
                inscriptions.add(inscription);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  inscriptions;
    }
    public  List<Inscription> selectAllInscriptionByAnneeScolaire(String anneScolaire,int idClasse){
      List<Inscription> inscriptions=new ArrayList<>();
    try {
        openConnexion();
        initPreparedStatement(SQL_SELECT_ALL_PAR_ANNEE_CLASSE);
        statement.setString(1, anneScolaire);
        statement.setInt(2, idClasse);
        
        ResultSet rs= executeSelect();
          while (rs.next()) {
            //Une ligne du ResultSet ==> Une Agence
             Inscription inscription=new Inscription();
             inscription.setId(rs.getInt("id"));
             inscription.setAnneScolaire(rs.getString("anneScolaire"));
             Etudiant etudiant=new Etudiant();
             etudiant.setMatricule(rs.getString("matricule"));
             etudiant.setNomComplet(rs.getString("nomComplet"));
             etudiant.setTuteur(rs.getString("tuteur"));
             inscription.setEtudiant(etudiant);
             inscriptions.add(inscription);
          }
          rs.close();
        closeConnexion();
     }
    catch (SQLException e) {
     System.out.println("Erreur de Connexion a la BD");
   }
     return  inscriptions;
 }

  public Inscription selectInscriptionByMatricule(String mat){
           Inscription inscription=null;
           try {
               openConnexion();
               initPreparedStatement(SQL_SELECT_BY_MATRICULE);
               statement.setString(1, mat);
               ResultSet rs= executeSelect();
               if (rs.next()) {
                   //Une ligne ==> rs de la requete
                    inscription=new Inscription();
                    inscription.setId(rs.getInt("id"));
                    inscription.setAnneScolaire(rs.getString("anneScolaire"));
                    Etudiant etudiant=new Etudiant();
                    etudiant.setMatricule(rs.getString("matricule"));
                    etudiant.setNomComplet(rs.getString("nomComplet"));
                    etudiant.setTuteur(rs.getString("tuteur"));
                    inscription.setEtudiant(etudiant);
               }
               statement.close();
               rs.close();
               conn.close();
           } 
           catch (SQLException e) {
             System.out.println("");
           }
               return inscription;
         }

}
