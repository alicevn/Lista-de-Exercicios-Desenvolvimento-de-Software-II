package br.edu.uniaeso;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExportarVendasCSV {

    public static void main(String[] args) {

        List<Venda> vendas = new ArrayList<>();
        vendas.add(new Venda("2024-04-20", 150.0, "Produto A"));
        vendas.add(new Venda("2024-04-21", 1400.0, "Produto B"));
        vendas.add(new Venda("2024-04-22", 2000.0, "Produto C"));

        exportarVendasParaCSV(vendas, "vendas.csv");
    }

    public static void exportarVendasParaCSV(List<Venda> vendas, String nomeArquivo) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(nomeArquivo))) {
           
            String[] cabecalhos = { "Data", "Valor", "Produto" };
            writer.writeNext(cabecalhos);

            for (Venda venda : vendas) {
                String[] linha = { venda.getData(), String.valueOf(venda.getValor()), venda.getProduto() };
                writer.writeNext(linha);
            }
            System.out.println("Vendas exportadas para o arquivo '" + nomeArquivo + "' com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao exportar as vendas para o arquivo CSV: " + e.getMessage());
        }
    }
}

class Venda {
    private String data;
    private double valor;
    private String produto;

    public Venda(String data, double valor, String produto) {
        this.data = data;
        this.valor = valor;
        this.produto = produto;
    }

    public String getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public String getProduto() {
        return produto;
    }
}