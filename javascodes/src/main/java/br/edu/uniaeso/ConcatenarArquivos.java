package br.edu.uniaeso;

import java.io.*;

public class ConcatenarArquivos {
    public static void main(String[] args) {
        String Arquivo1 = "arquivo1.txt";
        String Arquivo2 = "arquivo2.txt";
        String ArquivoConcatenado = "arquivo_concatenado.txt";

        String conteudoArquivo1 = "Alice";
        String conteudoArquivo2 = "Emily";

        criarArquivo(Arquivo1, conteudoArquivo1);
        criarArquivo(Arquivo2, conteudoArquivo2);
        concatenarArquivos(Arquivo1, Arquivo2, ArquivoConcatenado);
    }

    public static void criarArquivo(String nomeArquivo, String conteudo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo))) {
            escritor.write(conteudo);
            System.out.println("Arquivo '" + nomeArquivo + "' criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo '" + nomeArquivo + e);
        }
    }

    public static void concatenarArquivos(String Arquivo1, String Arquivo2, String ArquivoConcatenado) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ArquivoConcatenado));
             BufferedReader leitor1 = new BufferedReader(new FileReader(Arquivo1));
             BufferedReader leitor2 = new BufferedReader(new FileReader(Arquivo2))) {

            String linha;
            while ((linha = leitor1.readLine()) != null) {
                escritor.write(linha);
                escritor.newLine();
            }

            while ((linha = leitor2.readLine()) != null) {
                escritor.write(linha);
                escritor.newLine();
            }

            System.out.println("Arquivo '" + ArquivoConcatenado + "' criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao concatenar os arquivos: " + e);
        }
    }
}
