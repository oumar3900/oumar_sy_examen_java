package repositories;

// import java.sql.ResultSet;
import java.sql.SQLException;



import entities.Etudiant;


public class EtudiantRepository extends Database implements IEtudiant{
    
    private final  String SQL_INSERT="INSERT INTO etudiant (matricule, nomComplet, tuteur) VALUES (?,?,?)";

      // public Etudiant selectEtudiantByMatricule(String mat){
      //     Etudiant etudiant=null;
      //     try {
      //         openConnexion();
      //         initPreparedStatement(SQL_SELECT_BY_MATRICULE);
      //         statement.setString(1, mat);
      //         ResultSet rs= executeSelect();
      //         if (rs.next()) {
      //             //Une ligne ==> rs de la requete
      //             etudiant=new Etudiant();
      //             etudiant.setMatricule(rs.getString("matricule"));
      //             etudiant.setNomComplet(rs.getString("nomComplet"));
      //             etudiant.setTuteur(rs.getString("tuteur"));
      //         }
      //         statement.close();
      //         rs.close();
      //         conn.close();
      //     } 
      //     catch (SQLException e) {
      //       System.out.println("Erreur de Connexion à la BD");
      //     }
      //         return etudiant;
      //   }
              public  void insert(Etudiant etudiant){
                openConnexion();
                try {
                    initPreparedStatement(SQL_INSERT);
                    statement.setString(1, etudiant.getMatricule());
                    statement.setString(2, etudiant.getNomComplet());
                    statement.setString(3, etudiant.getTuteur());
                    int nbreLigne=executeUpdate();
                   closeConnexion();
                 } catch (SQLException e) {
                  e.printStackTrace();
                 }
                 }
}
