package br.edu.uniaeso;

import java.io.*;

public class AdicionarFrase {
    public static void main(String[] args) {

        String nomeArquivo = "meuarquivo.txt";

        adicionar(nomeArquivo, "isso é uma adição!");

        exibir(nomeArquivo);
    }

    public static void adicionar(String nomeArquivo, String mensagem) {
        try (FileWriter arquivoEscrita = new FileWriter(nomeArquivo, true);
             BufferedWriter escritor = new BufferedWriter(arquivoEscrita)) {
            escritor.write(mensagem);
            escritor.newLine();
            System.out.println("Mensagem adicionada com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao adicionar a mensagem. " + e);
        }
    }

    public static void exibir(String nomeArquivo) {
        try (FileReader arquivoLeitura = new FileReader(nomeArquivo);
             BufferedReader leitor = new BufferedReader(arquivoLeitura)) {
            String linha;
            System.out.println("Conteúdo do arquivo:");
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo. " + e);
        }
    }
}
