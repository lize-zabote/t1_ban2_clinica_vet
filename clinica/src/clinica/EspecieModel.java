package clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecieModel {
    static void create(EspecieBean e, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO especie (descricao) VALUES (?)");
        st.setString(1, e.getDescricao());
        st.execute();
        st.close();
    }

    static List<EspecieBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        List<EspecieBean> list = new ArrayList<>();
        ResultSet result = st.executeQuery("SELECT idespecie, descricao FROM especie ORDER BY idespecie");
        while (result.next()) {
            list.add(new EspecieBean(result.getInt(1), result.getString(2)));
        }
        return list;
    }

    static void update(EspecieBean e, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("UPDATE especie SET descricao=? WHERE idespecie=?");
        st.setString(1, e.getDescricao());
        st.setInt(2, e.getIdEspecie());
        st.execute();
        st.close();
    }

    static void remove(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM especie WHERE idespecie=?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
}
