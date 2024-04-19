package br.edu.uniaeso;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Produto2 {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto2(String nome, double preco, int quantidade) {
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

    public String toString() {
        return "Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto2> produtos = new ArrayList<>();

        while (true) {
            System.out.println("Digite o nome do produto (ou 'sair' para encerrar):");
            String nome = scanner.nextLine();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.println("Digite o preço do produto:");
            double preco = Double.parseDouble(scanner.nextLine());

            System.out.println("Digite a quantidade em estoque:");
            int quantidade = Integer.parseInt(scanner.nextLine());

            Produto2 produto = new Produto2(nome, preco, quantidade);
            produtos.add(produto);
        }

        String arquivoCSV = "produtos.csv";
        try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCSV))) {
            
            writer.writeNext(new String[] { "Nome", "Preço", "Quantidade" });

            for (Produto2 produto : produtos) {
                writer.writeNext(new String[] { produto.getNome(), String.valueOf(produto.getPreco()),
                        String.valueOf(produto.getQuantidade()) });
            }
            System.out.println("Dados dos produtos foram escritos no arquivo 'produtos.csv'.");
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
