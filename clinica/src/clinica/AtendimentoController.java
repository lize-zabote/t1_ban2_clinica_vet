package clinica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AtendimentoController {

    // Processo de Negócio: Efetuar um atendimento
    public void efetuarAtendimento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Efetuar Novo Atendimento ---");

        // Lista agendamentos disponíveis
        System.out.println("Agendamentos disponíveis:");
        new AgendamentoController().listarAgendamentos(con);
        System.out.print("Selecione o ID do Agendamento: ");
        int idAgendamento = input.nextInt();

        // Lista funcionários disponíveis
        System.out.println("Funcionários disponíveis:");
        new FuncionarioController().listarFuncionarios(con);
        System.out.print("Selecione o ID do Funcionário: ");
        int idFuncionario = input.nextInt();

        AtendimentoBean ab = new AtendimentoBean(idAgendamento, idFuncionario);
        AtendimentoModel.create(ab, con);
        System.out.println("Atendimento registrado com sucesso!");
    }
    
    public void listarAtendimentos(Connection con) throws SQLException {
        List<AtendimentoBean> atendimentos = AtendimentoModel.listAll(con);
        System.out.println("\n--- Histórico de Atendimentos ---");
        for (AtendimentoBean ab : atendimentos) {
            System.out.println(ab);
        }
        System.out.println("---------------------------------\n");
    }
}

