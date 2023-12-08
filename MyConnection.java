
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	
	// Les informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/gestionemploye?zeroDateTimeBehavior=convertToNull&serverTimezone=Europe/Paris";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    public static Connection obtenirConnexion() {
        Connection connexion = null;
        try {
            // Chargez le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établissez la connexion à la base de données
            connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		catch(Exception e) {
			System.out.println("connextion echoue");
			System.out.println( e.getMessage());
			return null;
		}
        
        return connexion;
    }
}