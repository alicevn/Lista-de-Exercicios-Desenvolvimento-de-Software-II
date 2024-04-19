package br.edu.uniaeso;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;

public class LerCSV {
    public static void main(String[] args) {
        String arquivoCSV = "alunos.csv";

        try (CSVReader reader = new CSVReader(new FileReader(arquivoCSV))) {
            String[] linha;
            while ((linha = reader.readNext()) != null) {

                for (String coluna : linha) {
                    System.out.print(coluna + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }
}
