package clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalModel {
    static void create(AnimalBean a, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO animal (nome, dataNascimento, idEspecie, idCliente) VALUES (?, ?, ?, ?)");
        st.setString(1, a.getNome());
        st.setDate(2, a.getDataNascimento());
        st.setInt(3, a.getIdEspecie());
        st.setInt(4, a.getIdCliente());
        st.execute();
        st.close();
    }

    static List<AnimalBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        List<AnimalBean> list = new ArrayList<>();
        String sql = "SELECT a.idAnimal, a.nome, a.dataNascimento, a.idEspecie, a.idCliente, c.nome || ' ' || c.sobrenome, e.descricao " +
                     "FROM animal a " +
                     "JOIN cliente c ON a.idCliente = c.idCliente " +
                     "JOIN especie e ON a.idEspecie = e.idEspecie " +
                     "ORDER BY a.idAnimal";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            AnimalBean ab = new AnimalBean(result.getInt(1), result.getString(2), result.getDate(3), result.getInt(4), result.getInt(5));
            ab.setNomeCliente(result.getString(6));
            ab.setNomeEspecie(result.getString(7));
            list.add(ab);
        }
        return list;
    }

    static void update(AnimalBean a, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("UPDATE animal SET nome=?, dataNascimento=?, idEspecie=?, idCliente=? WHERE idAnimal=?");
        st.setString(1, a.getNome());
        st.setDate(2, a.getDataNascimento());
        st.setInt(3, a.getIdEspecie());
        st.setInt(4, a.getIdCliente());
        st.setInt(5, a.getIdAnimal());
        st.execute();
        st.close();
    }

    static void remove(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM animal WHERE idAnimal=?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
}
