package br.edu.uniaeso;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.opencsv.CSVWriter;

public class CriarCSV {
    public static void main(String[] args) {
        String arquivoCSV = "funcionarios.csv";
        Scanner scanner = new Scanner(System.in);

        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCSV))) {
            while (true) {
                
                System.out.println("Digite o nome do funcionário (ou 'sair' para encerrar):");
                String nome = scanner.nextLine();
                if (nome.equalsIgnoreCase("sair")) {
                    break;
                }

                System.out.println("Digite o cargo do funcionário:");
                String cargo = scanner.nextLine();

                System.out.println("Digite o salário do funcionário:");
                String salario = scanner.nextLine();

                String[] linha = {nome, cargo, salario};
                writer.writeNext(linha);
            }
            System.out.println("Dados dos funcionários foram escritos no arquivo 'funcionarios.csv'.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV. " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
