package repositories;

import java.sql.SQLException;

import entities.ProfesseurDeClasse;

public class ProfesseurDeClasseRepository extends Database implements IProfesseurDeClasse{
    private final String SQL_INSERT="INSERT INTO professeurDeClasse (id_classe, id_professeur) VALUES (?, ?);";
    public void insert(ProfesseurDeClasse  professeurDeClasse){
        try {
            openConnexion();
            initPreparedStatement(SQL_INSERT);
             statement.setInt(1,professeurDeClasse.getClasse().getId());
             statement.setInt(2,professeurDeClasse.getProfesseur().getId());
             executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
}
