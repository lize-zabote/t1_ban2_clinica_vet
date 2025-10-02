package clinica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private Connection con;
    
    public Conexao() {
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "udesc"; // Altere se sua senha for diferente
        String url = "jdbc:postgresql://localhost:5432/clinica";

        try {
            Class.forName(driver);
            this.con = DriverManager.getConnection(url, user, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, "Erro ao conectar ao banco de dados.", ex);
            System.exit(1);
        }  
    }
    
    public Connection getConnection() {
        return con;
    }
    
    public void closeConnection(){
        try {
            if (this.con != null && !this.con.isClosed()) {
                this.con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, "Erro ao fechar a conexão.", ex);
        }
    }
}
