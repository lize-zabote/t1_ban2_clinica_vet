package clinica;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ServicoController {
    public void createServico(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo serviço:");
        System.out.print("Descrição: ");
        String descricao = input.nextLine();
        System.out.print("Valor: ");
        BigDecimal valor = input.nextBigDecimal();
        ServicoModel.create(new ServicoBean(valor, descricao), con);
        System.out.println("Serviço criado com sucesso!!");
    }

    public void listarServicos(Connection con) throws SQLException {
        List<ServicoBean> servicos = ServicoModel.listAll(con);
        System.out.println("\n--- Lista de Serviços ---");
        for (ServicoBean sb : servicos) {
            System.out.println(sb);
        }
        System.out.println("-------------------------\n");
    }

    public void updateServico(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarServicos(con);
        System.out.print("Informe o ID do serviço que deseja alterar: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Nova Descrição: ");
        String descricao = input.nextLine();
        System.out.print("Novo Valor: ");
        BigDecimal valor = input.nextBigDecimal();
        ServicoModel.update(new ServicoBean(id, valor, descricao), con);
        System.out.println("Serviço alterado com sucesso!!");
    }

    public void deleteServico(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarServicos(con);
        System.out.print("Informe o ID do serviço a ser removido: ");
        int id = input.nextInt();
        ServicoModel.remove(id, con);
        System.out.println("Serviço removido com sucesso!!");
    }
}
