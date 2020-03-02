
package sga.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;
public class BancoDadosDao {
    
    //Status da conexão, para consultas
    private String statusConexao = "Não conectado";
 
    public Connection connect() {
        //Atributo para conexão
        Connection isConnected = null;
 
        //Começa a tentativa de conexão
        try {
 
            //Designa o driver de conexão padrão para essa conexão:
            
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driverName);
            //oracle.jdbc.driver.OracleDriver
            //Seta os parâmetros da conexão ao banco -IMPORTANTE AQUI!!!-
            String host = "localhost"; //Caminho de rede do BD
            String database = "DPtec";   //Nome do schema que será usado
            String port = "1433";
            String usuario = "sa";    //Usuário da base, criado anteriormente
            String pass = "1234";   //Senha do usuário
 
            //URL para o driver JDBC:
            String url = "jdbc:sqlserver://"+ host +":" + port + ";databaseName=" + database + ";user=" + usuario + ";password=" + pass + ";";
 
            //Parâmetros da conexão (usuário e senha)
            //Atenção: COLOQUE AQUI O SEU USUÁRIO E A SUA SENHA!

 
            //seta a conexão:
            isConnected = (Connection) DriverManager.getConnection(url);
 
            //Testando a conexão
            if (isConnected != null) {   //Se aconexão for ok, ela não é nula...
                //Troca o status da conexão
                statusConexao = ("Conetado");
            } else {
                statusConexao = ("Não conectado");
            }
 
            //retorna a conexão para quem for utiliza-la
            return isConnected;
 
        } catch (ClassNotFoundException e) {  //Caso não encontre o driver de conexão
            System.out.println("Driver de conexão não encontrado");
            return null;
        } catch (SQLException e) { //Caso não consiga conectar
            System.out.println("Falha na conexão: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
 
    //Retorna o status da conexão
    public String getStatusConexao() {
        return statusConexao;
    }
 
    //Método para fechar (encerrar) a conexão
    public boolean FechaConexao() {
        try {
            connect().close(); //Fecha
            statusConexao = "Conexão fechada"; //Atualiza o status
            return true; //Retorna verdadeiro = conseguiu fechar!
        } catch (SQLException e) {
            //Se der algum erro, imprime
            System.out.println(e.getMessage());
            return false;
        }
    }
 
    //caso precise reiniciar sua conexão
    public Connection ReiniciaConexao() {
        FechaConexao(); //Fecha
        return connect(); //Abre e retorna
    }
}

