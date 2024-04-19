package br.edu.uniaeso;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
//ler o arquivo de texto.
public class LerArquivo {
    public static void main(String[] args) {
        String nomeArquivo = "meuarquivo.txt";

        try (FileReader arquivoLeitura = new FileReader(nomeArquivo);
            BufferedReader leitor = new BufferedReader(arquivoLeitura))
             {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e);
        }
    }
}
