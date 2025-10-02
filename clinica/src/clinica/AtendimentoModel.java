package clinica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoModel {
    static void create(AtendimentoBean a, Connection con) throws SQLException {
        PreparedStatement st = con.prepareStatement("INSERT INTO atendimento (idAgendamento, idFuncionario) VALUES (?, ?)");
        st.setInt(1, a.getIdAgendamento());
        st.setInt(2, a.getIdFuncionario());
        st.execute();
        st.close();
    }

    static List<AtendimentoBean> listAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        List<AtendimentoBean> list = new ArrayList<>();
        String sql = "SELECT at.idAgendamento, at.idFuncionario, f.nome || ' ' || f.sobrenome, an.nome, s.descricao, ag.data " +
                     "FROM atendimento at " +
                     "JOIN funcionario f ON at.idFuncionario = f.idFuncionario " +
                     "JOIN agendamento ag ON at.idAgendamento = ag.idAgendamento " +
                     "JOIN animal an ON ag.idAnimal = an.idAnimal " +
                     "JOIN servico s ON ag.idServico = s.idServico " +
                     "ORDER BY ag.data";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            AtendimentoBean ab = new AtendimentoBean(result.getInt(1), result.getInt(2));
            ab.setNomeFuncionario(result.getString(3));
            ab.setNomeAnimal(result.getString(4));
            ab.setDescServico(result.getString(5));
            ab.setData(result.getDate(6));
            list.add(ab);
        }
        return list;
    }
}

