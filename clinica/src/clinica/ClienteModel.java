package clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteModel {
    static void create(ClienteBean c, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO cliente (nome, sobrenome, telefone, cep, bairro, rua) VALUES (?, ?, ?, ?, ?, ?)");
        st.setString(1, c.getNome());
        st.setString(2, c.getSobrenome());
        st.setString(3, c.getTelefone());
        st.setString(4, c.getCep());
        st.setString(5, c.getBairro());
        st.setString(6, c.getRua());
        st.execute();
        st.close();
    }

    static List<ClienteBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        List<ClienteBean> list = new ArrayList<>();
        String sql = "SELECT idcliente, nome, sobrenome, telefone, cep, bairro, rua FROM cliente ORDER BY idcliente";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            list.add(new ClienteBean(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7)));
        }
        return list;
    }

    static void update(ClienteBean c, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("UPDATE cliente SET nome=?, sobrenome=?, telefone=?, cep=?, bairro=?, rua=? WHERE idcliente=?");
        st.setString(1, c.getNome());
        st.setString(2, c.getSobrenome());
        st.setString(3, c.getTelefone());
        st.setString(4, c.getCep());
        st.setString(5, c.getBairro());
        st.setString(6, c.getRua());
        st.setInt(7, c.getIdCliente());
        st.execute();
        st.close();
    }

    static void remove(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM cliente WHERE idcliente=?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
}
