package clinica;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AnimalController {
    public void createAnimal(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo animal:");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        Date dataNascimento = Date.valueOf(input.nextLine());

        // Listar Clientes para seleção
        new ClienteController().listarClientes(con);
        System.out.print("ID do Cliente (Dono): ");
        int idCliente = input.nextInt();

        // Listar Espécies para seleção
        new EspecieController().listarEspecies(con);
        System.out.print("ID da Espécie: ");
        int idEspecie = input.nextInt();

        AnimalBean ab = new AnimalBean(nome, dataNascimento, idEspecie, idCliente);
        AnimalModel.create(ab, con);
        System.out.println("Animal criado com sucesso!!");
    }

    public void listarAnimais(Connection con) throws SQLException {
        List<AnimalBean> animais = AnimalModel.listAll(con);
        System.out.println("\n--- Lista de Animais ---");
        for (AnimalBean ab : animais) {
            System.out.println(ab);
        }
        System.out.println("------------------------\n");
    }

    public void updateAnimal(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarAnimais(con);
        System.out.print("Informe o ID do animal que deseja alterar: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Insira os novos dados:");
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        Date dataNascimento = Date.valueOf(input.nextLine());
        
        new ClienteController().listarClientes(con);
        System.out.print("ID do Cliente (Dono): ");
        int idCliente = input.nextInt();

        new EspecieController().listarEspecies(con);
        System.out.print("ID da Espécie: ");
        int idEspecie = input.nextInt();

        AnimalBean ab = new AnimalBean(id, nome, dataNascimento, idEspecie, idCliente);
        AnimalModel.update(ab, con);
        System.out.println("Animal alterado com sucesso!!");
    }

    public void deleteAnimal(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarAnimais(con);
        System.out.print("Informe o ID do animal a ser removido: ");
        int id = input.nextInt();
        AnimalModel.remove(id, con);
        System.out.println("Animal removido com sucesso!!");
    }
}
