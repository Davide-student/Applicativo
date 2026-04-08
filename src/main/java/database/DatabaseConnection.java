package database;
//Questa classe viene utilizzata per la connessione al database "hackathon" su DBMS postgresql
import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance; //Per evitare problemi di concorrenza nell'uso della connessione, viene reso static
    public Connection connection = null;
    private String name = "postgres";
    private String password = "admin";
    private String url = "jdbc:postgresql://localhost:5432/hackathon";
    private String driver = "org.postgresql.Driver";



    private DatabaseConnection() throws SQLException {
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url, name, password);  //Crea la connessione al database
        }catch(ClassNotFoundException e){
            System.out.println("An error occured while creating connection to database" + e.getMessage());
        }
    }
    public static DatabaseConnection getInstance() throws SQLException {
        if(instance == null){   //La connessione non è stata creata o è stata chiusa, viene quindi creata
            try {
                instance = new DatabaseConnection();
            }catch (SQLException e) {
                System.out.println("An error occured while creating connection to database" + e.getMessage());
            }
        }else if(instance.connection.isClosed()){
            try {
                instance = new DatabaseConnection();
            }catch (SQLException e) {
                System.out.println("An error occured while creating connection to database" + e.getMessage());
            }
        }
        return instance;
    }

}
