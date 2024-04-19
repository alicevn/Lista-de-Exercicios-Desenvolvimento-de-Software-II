package br.edu.uniaeso;

import java.io.*;
//substitue todos as palavras "Java" por "Python"
public class SubstituirJava {
    public static void main(String[] args) {
        String ArquivoOriginal = "meuarquivo.txt";
        String ArquivoNovo = "meuarquivo_python.txt";

        substituirPalavra(ArquivoOriginal, ArquivoNovo, "Java", "Python");

        exibirConteudo(ArquivoNovo);
    }

    public static void substituirPalavra(String ArquivoOriginal, String ArquivoNovo, String palavraAntiga, String palavraNova)
    {
        try (FileReader arquivoLeitura = new FileReader(ArquivoOriginal);
             BufferedReader leitor = new BufferedReader(arquivoLeitura);
             FileWriter arquivoEscrita = new FileWriter(ArquivoNovo);
             BufferedWriter escritor = new BufferedWriter(arquivoEscrita)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linha = linha.replaceAll(palavraAntiga, palavraNova);
                escritor.write(linha);
                escritor.newLine();
            }
            System.out.println("Substituição concluída com sucesso!");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao realizar a substituição." + e);
        }
    }

    public static void exibirConteudo(String nomeArquivo) {
        try (FileReader arquivoLeitura = new FileReader(nomeArquivo);
             BufferedReader leitor = new BufferedReader(arquivoLeitura)) {
            String linha;
            System.out.println("Conteúdo do arquivo " + nomeArquivo + ":");
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + nomeArquivo + e);
        }
    }
}
