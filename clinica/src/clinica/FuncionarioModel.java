package clinica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioModel {
    
    static void create(FuncionarioBean f, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO funcionario (nome, sobrenome, rua, cep, bairro, especialidade, cargo) VALUES (?,?,?,?,?,?,?)");
        st.setString(1, f.getNome());
        st.setString(2, f.getSobrenome());
        st.setString(3, f.getRua());
        st.setString(4, f.getCep());
        st.setString(5, f.getBairro());
        st.setString(6, f.getEspecialidade());
        st.setString(7, f.getCargo());
        st.execute();
        st.close();
    }
    
    static List<FuncionarioBean> listAll(Connection con) throws SQLException {
        Statement st;
        List<FuncionarioBean> list = new ArrayList<>();
        st = con.createStatement();
        String sql = "SELECT idfuncionario, nome, sobrenome, rua, cep, bairro, especialidade, cargo FROM funcionario ORDER BY idfuncionario";
        ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new FuncionarioBean(
                result.getInt(1), 
                result.getString(2), 
                result.getString(3), 
                result.getString(4), 
                result.getString(5), 
                result.getString(6), 
                result.getString(7),
                result.getString(8)
            ));
        }
        return list;
    }
    
    static void remove(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE idfuncionario=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    static void update(FuncionarioBean f, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE funcionario SET nome=?, sobrenome=?, rua=?, cep=?, bairro=?, especialidade=?, cargo=? WHERE idfuncionario=?");
        st.setString(1, f.getNome());
        st.setString(2, f.getSobrenome());
        st.setString(3, f.getRua());
        st.setString(4, f.getCep());
        st.setString(5, f.getBairro());
        st.setString(6, f.getEspecialidade());
        st.setString(7, f.getCargo());
        st.setInt(8, f.getIdFuncionario());
        st.execute();
        st.close();          
    }
}
