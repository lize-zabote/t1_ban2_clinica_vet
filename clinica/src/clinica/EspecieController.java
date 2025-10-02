package clinica;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EspecieController {
    public void createEspecie(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira a descrição da nova espécie: ");
        String descricao = input.nextLine();
        EspecieModel.create(new EspecieBean(descricao), con);
        System.out.println("Espécie criada com sucesso!!");
    }

    public void listarEspecies(Connection con) throws SQLException {
        List<EspecieBean> especies = EspecieModel.listAll(con);
        System.out.println("\n--- Lista de Espécies ---");
        for (EspecieBean eb : especies) {
            System.out.println(eb);
        }
        System.out.println("-------------------------\n");
    }

    public void updateEspecie(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarEspecies(con);
        System.out.print("Informe o ID da espécie que deseja alterar: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Insira a nova descrição: ");
        String descricao = input.nextLine();
        EspecieModel.update(new EspecieBean(id, descricao), con);
        System.out.println("Espécie alterada com sucesso!!");
    }

    public void deleteEspecie(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarEspecies(con);
        System.out.print("Informe o ID da espécie a ser removida: ");
        int id = input.nextInt();
        EspecieModel.remove(id, con);
        System.out.println("Espécie removida com sucesso!!");
    }
}
