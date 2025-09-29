package clinica;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoModel {
    static void create(ServicoBean s, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO servico (valor, descricao) VALUES (?, ?)");
        st.setBigDecimal(1, s.getValor());
        st.setString(2, s.getDescricao());
        st.execute();
        st.close();
    }

    static List<ServicoBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        List<ServicoBean> list = new ArrayList<>();
        ResultSet result = st.executeQuery("SELECT idservico, valor, descricao FROM servico ORDER BY idservico");
        while (result.next()) {
            list.add(new ServicoBean(result.getInt(1), result.getBigDecimal(2), result.getString(3)));
        }
        return list;
    }

    static void update(ServicoBean s, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("UPDATE servico SET valor=?, descricao=? WHERE idservico=?");
        st.setBigDecimal(1, s.getValor());
        st.setString(2, s.getDescricao());
        st.setInt(3, s.getIdServico());
        st.execute();
        st.close();
    }

    static void remove(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM servico WHERE idservico=?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
}
