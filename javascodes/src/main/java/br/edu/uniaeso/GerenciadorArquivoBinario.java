package br.edu.uniaeso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Funcionario implements Serializable {
    private int id;
    private String nome;
    private double salario;

    public Funcionario(int id, String nome, double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Salário: " + salario;
    }
}

public class GerenciadorArquivoBinario {
    private static final String ARQUIVO_FUNCIONARIOS = "funcionarios.dat";

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        carregarFuncionarios(funcionarios);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar funcionário");
            System.out.println("2. Listar funcionários");
            System.out.println("3. Atualizar salário de funcionário");
            System.out.println("4. Excluir funcionário");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarFuncionario(funcionarios, scanner);
                    break;
                case 2:
                    listarFuncionarios(funcionarios);
                    break;
                case 3:
                    atualizarSalario(funcionarios, scanner);
                    break;
                case 4:
                    excluirFuncionario(funcionarios, scanner);
                    break;
                case 5:
                    salvarFuncionarios(funcionarios);
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void carregarFuncionarios(List<Funcionario> funcionarios) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ARQUIVO_FUNCIONARIOS))) {
            while (true) {
                Funcionario funcionario = (Funcionario) objectInputStream.readObject();
                funcionarios.add(funcionario);
            }
        } catch (EOFException e) {
            System.out.println("Funcionários carregados do arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar funcionários: " + e.getMessage());
        }
    }

    private static void salvarFuncionarios(List<Funcionario> funcionarios) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ARQUIVO_FUNCIONARIOS))) {
            for (Funcionario funcionario : funcionarios) {
                objectOutputStream.writeObject(funcionario);
            }
            System.out.println("Funcionários salvos no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar funcionários: " + e.getMessage());
        }
    }

    private static void adicionarFuncionario(List<Funcionario> funcionarios, Scanner scanner) {
        System.out.println("Informe o ID do funcionário:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Informe o nome do funcionário:");
        String nome = scanner.nextLine();

        System.out.println("Informe o salário do funcionário:");
        double salario = scanner.nextDouble();

        Funcionario novoFuncionario = new Funcionario(id, nome, salario);
        funcionarios.add(novoFuncionario);
        System.out.println("Funcionário adicionado com sucesso.");
    }

    private static void listarFuncionarios(List<Funcionario> funcionarios) {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    private static void atualizarSalario(List<Funcionario> funcionarios, Scanner scanner) {
        System.out.println("Informe o ID do funcionário para atualizar o salário:");
        int id = scanner.nextInt();

        boolean encontrado = false;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                System.out.println("Informe o novo salário:");
                double novoSalario = scanner.nextDouble();
                funcionario.setSalario(novoSalario);
                System.out.println("Salário atualizado com sucesso.");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void excluirFuncionario(List<Funcionario> funcionarios, Scanner scanner) {
        System.out.println("Informe o ID do funcionário para excluir:");
        int id = scanner.nextInt();
    
        Funcionario funcionarioParaRemover = null;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                funcionarioParaRemover = funcionario;
                break;
            }
        }
        
        if (funcionarioParaRemover != null) {
            funcionarios.remove(funcionarioParaRemover);
            System.out.println("Funcionário removido com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
}