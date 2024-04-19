package br.edu.uniaeso;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
//contar as linhas que existem dentro de um arquivo de texto.
public class ContarLinha {
    public static void main(String[] args) {

        String nomeArquivo = "meuarquivo.txt";
        FileReader arquivoLeitura = null;
        BufferedReader leitor = null;
        
        try {
            arquivoLeitura = new FileReader(nomeArquivo);
            leitor = new BufferedReader(arquivoLeitura);
            int cont = 0;
            while (leitor.readLine() != null) {
                cont++;
            }
            System.out.println("Número de linhas no arquivo: " + cont);
        } catch (IOException e) {
            System.out.println("Arquivo não pode ser lido. " + e);
        } finally {
            try {
                if (leitor != null)
                    leitor.close();
                if (arquivoLeitura != null)
                    arquivoLeitura.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo. " + e);
            }
        }
    }
}
