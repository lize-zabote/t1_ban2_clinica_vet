package clinica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FuncionarioController {
        
    public void createFuncionario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para um novo funcionário:");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = input.nextLine();
        System.out.print("Rua: ");
        String rua = input.nextLine();
        System.out.print("CEP: ");
        String cep = input.nextLine();
        System.out.print("Bairro: ");
        String bairro = input.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = input.nextLine();
        System.out.print("Cargo: ");
        String cargo = input.nextLine();
        FuncionarioBean fb = new FuncionarioBean(nome, sobrenome, rua, cep, bairro, especialidade, cargo);
        FuncionarioModel.create(fb, con);
        System.out.println("Funcionário criado com sucesso!!");
    }

    public void listarFuncionarios(Connection con) throws SQLException {
        List<FuncionarioBean> funcionarios = FuncionarioModel.listAll(con);
        System.out.println("\n--- Lista de Funcionários ---");
        for (FuncionarioBean fb : funcionarios) {
            System.out.println(fb);
        }
        System.out.println("---------------------------\n");
    }

    public void updateFuncionario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarFuncionarios(con);
        System.out.print("Informe o ID do funcionário que deseja alterar: ");
        int id = input.nextInt();
        input.nextLine(); // Consumir nova linha
        
        System.out.println("Insira os novos dados:");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = input.nextLine();
        System.out.print("Rua: ");
        String rua = input.nextLine();
        System.out.print("CEP: ");
        String cep = input.nextLine();
        System.out.print("Bairro: ");
        String bairro = input.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = input.nextLine();
        System.out.print("Cargo: ");
        String cargo = input.nextLine();
        
        FuncionarioBean fb = new FuncionarioBean(id, nome, sobrenome, rua, cep, bairro, especialidade, cargo);
        FuncionarioModel.update(fb, con);
        System.out.println("Funcionário alterado com sucesso!!");
    }
    
    public void deleteFuncionario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarFuncionarios(con);
        System.out.print("Informe o ID do funcionário a ser removido: ");
        int id = input.nextInt();
        FuncionarioModel.remove(id, con);
        System.out.println("Funcionário removido com sucesso!!");
    }
}
