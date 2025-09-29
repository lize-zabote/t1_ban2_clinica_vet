package clinica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClienteController {
    public void createCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo cliente:");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("CEP: ");
        String cep = input.nextLine();
        System.out.print("Bairro: ");
        String bairro = input.nextLine();
        System.out.print("Rua: ");
        String rua = input.nextLine();
        ClienteBean cb = new ClienteBean(nome, sobrenome, telefone, cep, bairro, rua);
        ClienteModel.create(cb, con);
        System.out.println("Cliente criado com sucesso!!");
    }

    public void listarClientes(Connection con) throws SQLException {
        List<ClienteBean> clientes = ClienteModel.listAll(con);
        System.out.println("\n--- Lista de Clientes ---");
        for (ClienteBean cb : clientes) {
            System.out.println(cb);
        }
        System.out.println("-------------------------\n");
    }

    public void updateCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarClientes(con);
        System.out.print("Informe o ID do cliente que deseja alterar: ");
        int id = input.nextInt();
        input.nextLine(); 
        
        System.out.println("Insira os novos dados:");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = input.nextLine();
        System.out.print("Telefone: ");
        String telefone = input.nextLine();
        System.out.print("CEP: ");
        String cep = input.nextLine();
        System.out.print("Bairro: ");
        String bairro = input.nextLine();
        System.out.print("Rua: ");
        String rua = input.nextLine();
        
        ClienteBean cb = new ClienteBean(id, nome, sobrenome, telefone, cep, bairro, rua);
        ClienteModel.update(cb, con);
        System.out.println("Cliente alterado com sucesso!!");
    }

    public void deleteCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarClientes(con);
        System.out.print("Informe o ID do cliente a ser removido: ");
        int id = input.nextInt();
        ClienteModel.remove(id, con);
        System.out.println("Cliente removido com sucesso!!");
    }
}
