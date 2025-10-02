package clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoModel {
    static void create(AgendamentoBean a, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO agendamento (data, hora, idServico, idAnimal) VALUES (?, ?, ?, ?)");
        st.setDate(1, a.getData());
        st.setTime(2, a.getHora());
        st.setInt(3, a.getIdServico());
        st.setInt(4, a.getIdAnimal());
        st.execute();
        st.close();
    }

    static List<AgendamentoBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        List<AgendamentoBean> list = new ArrayList<>();
        String sql = "SELECT ag.idAgendamento, ag.data, ag.hora, ag.idServico, ag.idAnimal, an.nome, s.descricao " +
                     "FROM agendamento ag " +
                     "JOIN animal an ON ag.idAnimal = an.idAnimal " +
                     "JOIN servico s ON ag.idServico = s.idServico " +
                     "ORDER BY ag.data, ag.hora";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            AgendamentoBean ab = new AgendamentoBean(result.getInt(1), result.getDate(2), result.getTime(3), result.getInt(4), result.getInt(5));
            ab.setNomeAnimal(result.getString(6));
            ab.setDescServico(result.getString(7));
            list.add(ab);
        }
        return list;
    }

    static void update(AgendamentoBean a, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("UPDATE agendamento SET data=?, hora=?, idServico=?, idAnimal=? WHERE idAgendamento=?");
        st.setDate(1, a.getData());
        st.setTime(2, a.getHora());
        st.setInt(3, a.getIdServico());
        st.setInt(4, a.getIdAnimal());
        st.setInt(5, a.getIdAgendamento());
        st.execute();
        st.close();
    }

    static void remove(int id, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("DELETE FROM agendamento WHERE idAgendamento=?");
        st.setInt(1, id);
        st.execute();
        st.close();
    }
}
