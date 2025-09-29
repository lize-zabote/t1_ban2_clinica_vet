package clinica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class AgendamentoController {
    public void createAgendamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo agendamento:");
        System.out.print("Data (AAAA-MM-DD): ");
        Date data = Date.valueOf(input.nextLine());
        System.out.print("Hora (HH:MM:SS): ");
        Time hora = Time.valueOf(input.nextLine());

        new ServicoController().listarServicos(con);
        System.out.print("ID do Serviço: ");
        int idServico = input.nextInt();

        new AnimalController().listarAnimais(con);
        System.out.print("ID do Animal: ");
        int idAnimal = input.nextInt();

        AgendamentoBean ab = new AgendamentoBean(data, hora, idServico, idAnimal);
        AgendamentoModel.create(ab, con);
        System.out.println("Agendamento criado com sucesso!!");
    }

    public void listarAgendamentos(Connection con) throws SQLException {
        List<AgendamentoBean> agendamentos = AgendamentoModel.listAll(con);
        System.out.println("\n--- Lista de Agendamentos ---");
        for (AgendamentoBean ab : agendamentos) {
            System.out.println(ab);
        }
        System.out.println("---------------------------\n");
    }

    public void updateAgendamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarAgendamentos(con);
        System.out.print("Informe o ID do agendamento que deseja alterar: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Insira os novos dados:");
        System.out.print("Data (AAAA-MM-DD): ");
        Date data = Date.valueOf(input.nextLine());
        System.out.print("Hora (HH:MM:SS): ");
        Time hora = Time.valueOf(input.nextLine());

        new ServicoController().listarServicos(con);
        System.out.print("ID do Serviço: ");
        int idServico = input.nextInt();

        new AnimalController().listarAnimais(con);
        System.out.print("ID do Animal: ");
        int idAnimal = input.nextInt();

        AgendamentoBean ab = new AgendamentoBean(id, data, hora, idServico, idAnimal);
        AgendamentoModel.update(ab, con);
        System.out.println("Agendamento alterado com sucesso!!");
    }

    public void deleteAgendamento(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarAgendamentos(con);
        System.out.print("Informe o ID do agendamento a ser removido: ");
        int id = input.nextInt();
        AgendamentoModel.remove(id, con);
        System.out.println("Agendamento removido com sucesso!!");
    }
}
