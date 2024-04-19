package br.edu.uniaeso;

import java.io.*;
//criar arquivo de texto e escrever uma mensagem nele.
public class CriaArquivo {
    public static void main(String[] args) {
        
        String nomeArquivo = "meuarquivo.txt";

        FileWriter arquivoEscrita = null;
        
        try {           
            arquivoEscrita = new FileWriter(nomeArquivo);
            
            arquivoEscrita.write("Olá, mundo!");
            
            System.out.println("Arquivo criado com sucesso!");

        } catch (IOException e) {
            System.out.println("Arquivo não pode ser criado" + e.getMessage());
        } finally {
            try {
                if (arquivoEscrita != null)
                    arquivoEscrita.close();
            } catch (IOException e) {
                System.out.println("Erro ao fechar o arquivo. " + e.getMessage());
            }
        }
    }
}
