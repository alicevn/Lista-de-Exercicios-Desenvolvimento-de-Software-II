package br.edu.uniaeso;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ContarPalavra {
    public static void main(String[] args) {
        String nomeArquivo = "meuarquivo.txt";
        String palavraAlvo = "Java";

        int cont = contarQuantidade(nomeArquivo, palavraAlvo);

        System.out.println(" Quantidade de vezes que " + palavraAlvo + " aparece no texto: " + cont);
    }

    public static int contarQuantidade(String nomeArquivo, String palavraAlvo) {
        int cont = 0;
        try (FileReader arquivoLeitura = new FileReader(nomeArquivo);
             BufferedReader leitor = new BufferedReader(arquivoLeitura)) {

            String linha;
            while ((linha = leitor.readLine()) != null) {

                String[] palavras = linha.split(" ");
                
                for (String palavra : palavras) {
                    
                    palavra = palavra.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (palavra.equals(palavraAlvo.toLowerCase())) {
                        
                        cont++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e);
        }
        return cont;
    }
}

