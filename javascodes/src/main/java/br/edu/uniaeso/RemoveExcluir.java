package br.edu.uniaeso;

import java.io.*;

public class RemoveExcluir {
    public static void main(String[] args) {
        String ArquivoOriginal = "meuarquivo.txt";
        String ArquivoSemExcluir = "meuarquivo_sem_excluir.txt";

       removerLinhas(ArquivoOriginal, ArquivoSemExcluir);
    }

    public static void removerLinhas(String ArquivoOriginal, String ArquivoSemExcluir) {
        try (FileReader arquivoLeitura = new FileReader(ArquivoOriginal);
             BufferedReader leitor = new BufferedReader(arquivoLeitura);
             FileWriter arquivoEscrita = new FileWriter(ArquivoSemExcluir);
             BufferedWriter escritor = new BufferedWriter(arquivoEscrita)) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                
                if (!linha.contains("excluir")) {
                    
                    escritor.write(linha);
                    escritor.newLine();
                }
            }
            System.out.println("Arquivo editado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao manipular o arquivo: " + e);
        }
    }
}

