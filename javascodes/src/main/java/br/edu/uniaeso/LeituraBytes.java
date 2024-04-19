package br.edu.uniaeso;

import java.io.*;

public class LeituraBytes {
    public static void main(String[] args) {
        
        String nomeArquivo = "arquivo.bin";
        int numBytesLidos = 100;

        try (FileInputStream arquivoInput = new FileInputStream(nomeArquivo)) {
            byte[] buffer = new byte[numBytesLidos];
            int bytesLidos = arquivoInput.read(buffer, 0, numBytesLidos);

            if (bytesLidos != -1) {
                System.out.println("Primeiros " + bytesLidos + " bytes do arquivo:");

                for (int i = 0; i < bytesLidos; i++) {
                    System.out.print(buffer[i] + " ");
                }
                System.out.println();
            } else {
                System.out.println("Arquivo vazio ou não foi possível ler os primeiros " + numBytesLidos + " bytes.");
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
