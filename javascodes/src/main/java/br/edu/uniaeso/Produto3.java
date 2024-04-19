package br.edu.uniaeso;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto3 {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto3(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String toString() {
        return "Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto3> produtos = new ArrayList<>();

        String arquivoCSV = "produtos.csv";
        try (CSVReader reader = new CSVReader(new FileReader(arquivoCSV))) {
            String[] linha;

            reader.readNext();
            while ((linha = reader.readNext()) != null) {
                String nome = linha[0];
                double preco = Double.parseDouble(linha[1]);
                int quantidade = Integer.parseInt(linha[2]);
                Produto3 produto = new Produto3(nome, preco, quantidade);
                produtos.add(produto);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }

        System.out.println("Produtos disponíveis:");
        for (Produto3 produto : produtos) {
            System.out.println(produto);
        }
        System.out.println("\nAtualizar informações do produto:");
        System.out.println("Digite o nome do produto a ser atualizado (ou 'sair' para encerrar):");
        String nomeProduto = scanner.nextLine();
        if (!nomeProduto.equalsIgnoreCase("sair")) {
            Produto3 produtoParaAtualizar = null;
            for (Produto3 produto : produtos) {
                if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                    produtoParaAtualizar = produto;
                    break;
                }
            }
            if (produtoParaAtualizar != null) {
                System.out.println("Produto encontrado. Digite as novas informações:");

                System.out.println("Novo preço do produto:");
                double novoPreco = Double.parseDouble(scanner.nextLine());
                produtoParaAtualizar.setPreco(novoPreco);

                System.out.println("Nova quantidade em estoque do produto:");
                int novaQuantidade = Integer.parseInt(scanner.nextLine());
                produtoParaAtualizar.setQuantidade(novaQuantidade);

                try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCSV))) {
                    
                    writer.writeNext(new String[] { "Nome", "Preço", "Quantidade" });

                    for (Produto3 produto : produtos) {
                        writer.writeNext(new String[] { produto.getNome(), String.valueOf(produto.getPreco()),
                                String.valueOf(produto.getQuantidade()) });
                    }
                    System.out.println("Informações do produto atualizadas no arquivo 'produtos.csv'.");
                } catch (IOException e) {
                    System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        }

        scanner.close();
    }
}
