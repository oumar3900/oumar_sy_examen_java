package repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entities.Classe;
import entities.Filiere;
import entities.Niveau;

public class ClasseRepository extends Database implements IClasse{
    
     private final  String SQL_SELECT_ALL_CLASSE="select * from classe" ;
    private final  String SQL_INSERT_CLASSE="INSERT INTO classe (filiere, niveau) VALUES (?,?)";
    private final  String SQL_SELECT_BY_ID="Select * from classe where id_classe like ? ";
    //select
    public  List<Classe> selectAllClasse(){
         List<Classe> classes=new ArrayList<>();
       try {
           openConnexion();
           initPreparedStatement(SQL_SELECT_ALL_CLASSE);
           ResultSet rs= executeSelect();
             while (rs.next()) {
               //Une ligne du ResultSet ==> Une Agence
               Classe classe=new Classe();
               classe.setId(rs.getInt("id_classe"));
               int niveau=rs.getInt("niveau");
               int filiere=rs.getInt("filiere");
                classe.setNiveaux(Niveau.values()[niveau]);
                classe.setFiliere(Filiere.values()[filiere]);
                classes.add(classe);
             }
             rs.close();
           closeConnexion();
        }
       catch (SQLException e) {
        System.out.println("Erreur de Connexion a la BD");
      }
        return  classes;
    }
    public  void insertClasse(Classe classe){
        openConnexion();
        try {
            initPreparedStatement(SQL_INSERT_CLASSE);
            statement.setDouble(1, classe.getFiliere().ordinal());
            statement.setDouble(2, classe.getNiveaux().ordinal());
            int nbreLigne=executeUpdate();
           closeConnexion();
         } catch (SQLException e) {
          e.printStackTrace();
         }
         }

         public Classe selectClasseById(int id){
                Classe classe=null;
                try {
                    openConnexion();
                    initPreparedStatement(SQL_SELECT_BY_ID);
                    statement.setInt(1, id);
                    ResultSet rs= executeSelect();
                    if (rs.next()) {
                       //Une ligne ==> rs de la requete
                        classe=new Classe();
                        classe.setId(rs.getInt("id_classe"));
                        int  niveau=rs.getInt("niveau");
                        int filiere=rs.getInt("filiere");
                        classe.setNiveaux(Niveau.values()[niveau]);
                        classe.setFiliere(Filiere.values()[filiere]);
                    }
                    statement.close();
                    rs.close();
                    conn.close();
               } 
               catch (SQLException e) {
                 System.out.println("Erreur de Connexion à la BD");
               }
                   return classe;
              }
}
