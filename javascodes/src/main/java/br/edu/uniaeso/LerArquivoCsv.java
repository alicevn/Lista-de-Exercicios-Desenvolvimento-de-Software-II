package br.edu.uniaeso;

import java.io.*;
import java.util.Scanner;

//ler um arquivo.csv

public class LerArquivoCsv {
    public static void main(String[] args) {

        String nomeArquivo = "dados.csv";

        lerArquivoCSV(nomeArquivo);
    }

    public static void lerArquivoCSV(String nomeArquivo) {
        try (FileReader arquivoLeitura = new FileReader(nomeArquivo);
             BufferedReader leitor = new BufferedReader(arquivoLeitura)) {
            String linha;
            System.out.println("Conteúdo do arquivo CSV:");
            while ((linha = leitor.readLine()) != null) {
                Scanner scanner = new Scanner(linha);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    System.out.print(scanner.next() + " ");
                }
                System.out.println();
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo CSV: " + e);
        }
    }
}
