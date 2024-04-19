package br.edu.uniaeso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Produto implements Serializable {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

public class SerializarProdutos {
    public static void main(String[] args) {
       
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Celular", 1000.0));
        produtos.add(new Produto("Notebook", 1499.99));
        produtos.add(new Produto("Tablet", 500.0));

        try {
            FileOutputStream arquivoSaida = new FileOutputStream("produtos.dat");
            ObjectOutputStream objetoSaida = new ObjectOutputStream(arquivoSaida);

            objetoSaida.writeObject(produtos);

            objetoSaida.close();
            arquivoSaida.close();

            System.out.println("Lista de produtos serializada com sucesso.");

        } catch (IOException e) {
            System.err.println("Erro ao serializar a lista de produtos. " + e.getMessage());
        }

        // Deserialização
        try {
            FileInputStream arquivoEntrada = new FileInputStream("produtos.dat");
            ObjectInputStream objetoEntrada = new ObjectInputStream(arquivoEntrada);

            List<Produto> produtosLidos = (List<Produto>) objetoEntrada.readObject();

            objetoEntrada.close();
            arquivoEntrada.close();

            System.out.println("Lista de produtos deserializada com sucesso. Produtos:");

            for (Produto produto : produtosLidos) {
                System.out.println("Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao deserializar a lista de produtos: " + e.getMessage());
        }
    }
}