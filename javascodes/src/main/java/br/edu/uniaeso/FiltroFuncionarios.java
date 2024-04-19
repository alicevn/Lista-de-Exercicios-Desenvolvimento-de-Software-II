package br.edu.uniaeso;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FiltroFuncionarios {
    public static void main(String[] args) {
        String arquivoCSV = "funcionarios.csv";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(arquivoCSV))) {
            String[] linha;

            reader.readNext();
            while ((linha = reader.readNext()) != null) {
                String nome = linha[0];
                String cargo = linha[1];
                double salario = Double.parseDouble(linha[2]);
                Funcionario funcionario = new Funcionario(nome, cargo, salario);
                funcionarios.add(funcionario);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            return;
        }

        System.out.println("Todos os funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nFiltrar funcionários:");
        System.out.println("1. Filtrar por cargo");
        System.out.println("2. Filtrar por salário mínimo");
        System.out.print("Escolha uma opção (1 ou 2): ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); 
        switch (opcao) {
            case 1:
                System.out.print("Digite o cargo para filtrar: ");
                String cargoFiltrar = scanner.nextLine();
                System.out.println("Funcionários com cargo '" + cargoFiltrar + "':");
                for (Funcionario funcionario : funcionarios) {
                    if (funcionario.getCargo().equalsIgnoreCase(cargoFiltrar)) {
                        System.out.println(funcionario);
                    }
                }
                break;
            case 2:
                System.out.print("Digite o salário mínimo para filtrar: ");
                double salarioMinimo = scanner.nextDouble();
                System.out.println("Funcionários com salário maior ou igual a " + salarioMinimo + ":");
                for (Funcionario funcionario : funcionarios) {
                    if (funcionario.getSalario() >= salarioMinimo) {
                        System.out.println(funcionario);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}

class Funcionario {
    private String nome;
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cargo, double salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cargo: " + cargo + ", Salário: " + salario;
    }
}

