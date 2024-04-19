package br.edu.uniaeso;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Produto4 {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto4(String nome, double preco, int quantidade) {
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

    @Override
    public String toString() {
        return "Nome: " + nome + ", Preço: " + preco + ", Quantidade: " + quantidade;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto4> produtos = new ArrayList<>();

        String arquivoCSV = "produtos.csv";
        try (CSVReader reader = new CSVReader(new FileReader(arquivoCSV))) {
            String[] linha;

            reader.readNext();
            while ((linha = reader.readNext()) != null) {
                String nome = linha[0];
                double preco = Double.parseDouble(linha[1]);
                int quantidade = Integer.parseInt(linha[2]);
                Produto4 produto = new Produto4(nome, preco, quantidade);
                produtos.add(produto);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        System.out.println("Produtos disponíveis:");
        for (Produto4 produto : produtos) {
            System.out.println(produto);
        }

        System.out.println("\nExcluir produto:");
        System.out.println("Digite o nome do produto a ser excluído (ou 'sair' para encerrar):");
        String nomeProdutoExcluir = scanner.nextLine();
        if (!nomeProdutoExcluir.equalsIgnoreCase("sair")) {
            Iterator<Produto4> iterator = produtos.iterator();
            boolean produtoEncontrado = false;
            while (iterator.hasNext()) {
                Produto4 produto = iterator.next();
                if (produto.getNome().equalsIgnoreCase(nomeProdutoExcluir)) {
                    iterator.remove();
                    produtoEncontrado = true;
                    break;
                }
            }
            if (produtoEncontrado) {

                try (CSVWriter writer = new CSVWriter(new FileWriter(arquivoCSV))) {
                    
                    writer.writeNext(new String[] { "Nome", "Preço", "Quantidade" });

                    for (Produto4 produto : produtos) {
                        writer.writeNext(new String[] { produto.getNome(), String.valueOf(produto.getPreco()),
                                String.valueOf(produto.getQuantidade()) });
                    }
                    System.out.println("Produto excluído e informações atualizadas no arquivo 'produtos.csv'.");
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
