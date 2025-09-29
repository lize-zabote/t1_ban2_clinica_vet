package clinica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class RelatorioController {
    
    public void gerarRelatorioAnimaisPorFuncionario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        new FuncionarioController().listarFuncionarios(con);
        System.out.print("Digite o ID do funcionário para gerar o relatório: ");
        int idFuncionario = input.nextInt();
        RelatorioModel.relatorioAnimaisPorFuncionario(idFuncionario, con);
    }

    public void gerarRelatorioAgendamentosPorPeriodo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o período para o relatório de agendamentos:");
        System.out.print("Data de Início (AAAA-MM-DD): ");
        Date dataInicio = Date.valueOf(input.nextLine());
        System.out.print("Data de Fim (AAAA-MM-DD): ");
        Date dataFim = Date.valueOf(input.nextLine());
        RelatorioModel.relatorioAgendamentosPorPeriodo(dataInicio, dataFim, con);
    }

    public void gerarRelatorioAnimaisPorCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        new ClienteController().listarClientes(con);
        System.out.print("Digite o ID do cliente para gerar o relatório: ");
        int idCliente = input.nextInt();
        RelatorioModel.relatorioAnimaisPorCliente(idCliente, con);
    }
}
