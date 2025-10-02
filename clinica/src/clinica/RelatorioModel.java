package clinica;

import java.sql.*;

public class RelatorioModel {

    // Relatório 1: Animais atendidos por um funcionário específico.
    public static void relatorioAnimaisPorFuncionario(int idFuncionario, Connection con) throws SQLException {
        String sql = "SELECT f.nome as funcionario, a.nome as animal, e.descricao as especie, c.nome as cliente, s.descricao as servico, ag.data " +
                     "FROM atendimento at " +
                     "JOIN funcionario f ON at.idFuncionario = f.idFuncionario " +
                     "JOIN agendamento ag ON at.idAgendamento = ag.idAgendamento " +
                     "JOIN animal a ON ag.idAnimal = a.idAnimal " +
                     "JOIN especie e ON a.idEspecie = e.idEspecie " +
                     "JOIN cliente c ON a.idCliente = c.idCliente " +
                     "JOIN servico s ON ag.idServico = s.idServico " +
                     "WHERE f.idFuncionario = ? " +
                     "ORDER BY ag.data";
        
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idFuncionario);
        ResultSet result = st.executeQuery();

        boolean found = false;
        System.out.println("\n--- Relatório de Atendimentos por Funcionário ---");
        while(result.next()) {
            if (!found) {
                System.out.println("Funcionário: " + result.getString("funcionario"));
                System.out.println("-------------------------------------------------");
                found = true;
            }
            System.out.println("  Data: " + result.getDate("data") + 
                             " | Animal: " + result.getString("animal") + 
                             " | Espécie: " + result.getString("especie") +
                             " | Dono: " + result.getString("cliente") + 
                             " | Serviço: " + result.getString("servico"));
        }
        if (!found) {
            System.out.println("Nenhum atendimento encontrado para este funcionário.");
        }
        System.out.println("-------------------------------------------------\n");
    }

    // Relatório 2: Agendamentos em um período.
    public static void relatorioAgendamentosPorPeriodo(Date dataInicio, Date dataFim, Connection con) throws SQLException {
        String sql = "SELECT ag.data, ag.hora, a.nome as animal, c.nome as cliente, s.descricao as servico " +
                     "FROM agendamento ag " +
                     "JOIN animal a ON ag.idAnimal = a.idAnimal " +
                     "JOIN cliente c ON a.idCliente = c.idCliente " +
                     "JOIN servico s ON ag.idServico = s.idServico " +
                     "WHERE ag.data BETWEEN ? AND ? " +
                     "ORDER BY ag.data, ag.hora";
                     
        PreparedStatement st = con.prepareStatement(sql);
        st.setDate(1, dataInicio);
        st.setDate(2, dataFim);
        ResultSet result = st.executeQuery();

        System.out.println("\n--- Relatório de Agendamentos de " + dataInicio + " a " + dataFim + " ---");
        while (result.next()) {
             System.out.println("  Data: " + result.getDate("data") +
                              " | Hora: " + result.getTime("hora") +
                              " | Animal: " + result.getString("animal") + 
                              " | Dono: " + result.getString("cliente") +
                              " | Serviço: " + result.getString("servico"));
        }
        System.out.println("------------------------------------------------------------------\n");
    }

    // Relatório 3: Animais de um cliente específico.
    public static void relatorioAnimaisPorCliente(int idCliente, Connection con) throws SQLException {
        String sql = "SELECT c.nome as cliente, a.nome as animal, e.descricao as especie, a.dataNascimento " +
                     "FROM animal a " +
                     "JOIN cliente c ON a.idCliente = c.idCliente " +
                     "JOIN especie e ON a.idEspecie = e.idEspecie " +
                     "WHERE c.idCliente = ? " +
                     "ORDER BY a.nome";

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, idCliente);
        ResultSet result = st.executeQuery();

        boolean found = false;
        System.out.println("\n--- Relatório de Animais por Cliente ---");
        while(result.next()) {
            if (!found) {
                System.out.println("Cliente: " + result.getString("cliente"));
                System.out.println("----------------------------------------");
                found = true;
            }
            System.out.println("  Animal: " + result.getString("animal") +
                             " | Espécie: " + result.getString("especie") +
                             " | Data Nasc.: " + result.getDate("dataNascimento"));
        }
        if (!found) {
            System.out.println("Nenhum animal encontrado para este cliente.");
        }
        System.out.println("----------------------------------------\n");
    }
}
