package clinica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Clinica {

    public static void main(String[] args) {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        Scanner input = new Scanner(System.in);

        int op;
        do {
            op = menuPrincipal(input);
            try {
                switch (op) {
                    case 1:
                        menuGerenciamento(input, con);
                        break;
                    case 2:
                        menuProcessos(input, con);
                        break;
                    case 3:
                        menuRelatorios(input, con);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException ex) {
                System.err.println("ERRO DE BANCO DE DADOS: " + ex.getMessage());
            } catch (Exception ex) {
                System.err.println("ERRO INESPERADO: " + ex.getMessage());
            }
        } while (op != 0);

        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
        input.close();
    }

    private static int menuPrincipal(Scanner input) {
        System.out.println("\n===== CLÍNICA VETERINÁRIA =====");
        System.out.println("1 - Gerenciamento (CRUD)");
        System.out.println("2 - Processos de Negócio");
        System.out.println("3 - Relatórios");
        System.out.println("0 - Sair");
        System.out.print("Sua opção: ");
        return input.nextInt();
    }

    private static void menuGerenciamento(Scanner input, Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- Menu de Gerenciamento ---");
            System.out.println("1 - Clientes");
            System.out.println("2 - Animais");
            System.out.println("3 - Funcionários");
            System.out.println("4 - Serviços");
            System.out.println("5 - Espécies");
            System.out.println("6 - Agendamentos");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Sua opção: ");
            op = input.nextInt();

            if (op != 0) {
                menuCRUD(op, input, con);
            }

        } while (op != 0);
    }
    
    private static void menuCRUD(int tabela, Scanner input, Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- Ações ---");
            System.out.println("1 - Criar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Deletar");
            System.out.println("0 - Voltar");
            System.out.print("Sua opção: ");
            op = input.nextInt();

            switch (tabela) {
                case 1: // Clientes
                    ClienteController cc = new ClienteController();
                    if(op == 1) cc.createCliente(con);
                    else if(op == 2) cc.listarClientes(con);
                    else if(op == 3) cc.updateCliente(con);
                    else if(op == 4) cc.deleteCliente(con);
                    break;
                case 2: // Animais
                    AnimalController ac = new AnimalController();
                    if(op == 1) ac.createAnimal(con);
                    else if(op == 2) ac.listarAnimais(con);
                    else if(op == 3) ac.updateAnimal(con);
                    else if(op == 4) ac.deleteAnimal(con);
                    break;
                case 3: // Funcionários
                    FuncionarioController fc = new FuncionarioController();
                    if(op == 1) fc.createFuncionario(con);
                    else if(op == 2) fc.listarFuncionarios(con);
                    else if(op == 3) fc.updateFuncionario(con);
                    else if(op == 4) fc.deleteFuncionario(con);
                    break;
                case 4: // Serviços
                    ServicoController sc = new ServicoController();
                    if(op == 1) sc.createServico(con);
                    else if(op == 2) sc.listarServicos(con);
                    else if(op == 3) sc.updateServico(con);
                    else if(op == 4) sc.deleteServico(con);
                    break;
                case 5: // Espécies
                    EspecieController ec = new EspecieController();
                    if(op == 1) ec.createEspecie(con);
                    else if(op == 2) ec.listarEspecies(con);
                    else if(op == 3) ec.updateEspecie(con);
                    else if(op == 4) ec.deleteEspecie(con);
                    break;
                case 6: // Agendamentos
                    AgendamentoController agc = new AgendamentoController();
                    if(op == 1) agc.createAgendamento(con);
                    else if(op == 2) agc.listarAgendamentos(con);
                    else if(op == 3) agc.updateAgendamento(con);
                    else if(op == 4) agc.deleteAgendamento(con);
                    break;
            }
        } while (op != 0);
    }
    
    private static void menuProcessos(Scanner input, Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- Processos de Negócio ---");
            System.out.println("1 - Efetuar Atendimento");
            System.out.println("2 - Listar Histórico de Atendimentos");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Sua opção: ");
            op = input.nextInt();
            
            AtendimentoController atc = new AtendimentoController();
            if(op == 1) atc.efetuarAtendimento(con);
            else if(op == 2) atc.listarAtendimentos(con);

        } while (op != 0);
    }

    private static void menuRelatorios(Scanner input, Connection con) throws SQLException {
        int op;
        do {
            System.out.println("\n--- Menu de Relatórios ---");
            System.out.println("1 - Atendimentos por Funcionário");
            System.out.println("2 - Agendamentos por Período");
            System.out.println("3 - Animais por Cliente");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Sua opção: ");
            op = input.nextInt();
            
            RelatorioController rc = new RelatorioController();
            if(op == 1) rc.gerarRelatorioAnimaisPorFuncionario(con);
            else if(op == 2) rc.gerarRelatorioAgendamentosPorPeriodo(con);
            else if(op == 3) rc.gerarRelatorioAnimaisPorCliente(con);
            
        } while (op != 0);
    }
}

