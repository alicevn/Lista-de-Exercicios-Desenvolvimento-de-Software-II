package br.edu.uniaeso;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenarArquivo {
    public static void main(String[] args) {

        String ArquivoOriginal = "meuarquivo.txt";
        String ArquivoOrdenado = "meuarquivo_ordenado.txt";

        ordenarArquivo(ArquivoOriginal, ArquivoOrdenado);
    }

    public static void ordenarArquivo(String ArquivoOriginal, String ArquivoOrdenado) {
        try (FileReader arquivoLeitura = new FileReader(ArquivoOriginal);
             BufferedReader leitor = new BufferedReader(arquivoLeitura)) {
            List<String> linhas = new ArrayList<>();
            
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha);
            }
            ordenarLista(linhas);

            try (FileWriter arquivoEscrita = new FileWriter(ArquivoOrdenado);
                 BufferedWriter escritor = new BufferedWriter(arquivoEscrita)) {
                for (String linhaOrdenada : linhas) {
                    escritor.write(linhaOrdenada);
                    escritor.newLine();
                }
                System.out.println("Arquivo ordenado criado com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao escrever no arquivo ordenado. " + e);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo original. " + e);
        }
    }
    public static void ordenarLista(List<String> lista) {
        int n = lista.size();
        boolean trocado;
        do {
            trocado = false;
            for (int i = 1; i < n; i++) {
                if (lista.get(i - 1).compareTo(lista.get(i)) > 0) {
                    String temp = lista.get(i - 1);
                    lista.set(i - 1, lista.get(i));
                    lista.set(i, temp);
                    trocado = true;
                }
            }
            n--;
        } while (trocado);
    }
}
