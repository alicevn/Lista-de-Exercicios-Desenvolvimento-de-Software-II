package br.edu.uniaeso;

import java.io.*;

public class CopiaArquivoBinario {
    public static void main(String[] args) {
        String arquivoOrigem = "arquivo_grande.bin";
        String arquivoDestino = "copia_arquivo_grande.bin";

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(arquivoOrigem));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(arquivoDestino))) {

            byte[] buffer = new byte[4096];
            int bytesLidos;

            while ((bytesLidos = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesLidos);
            }

            System.out.println("Arquivo copiado com sucesso.");

        } catch (IOException e) {
            System.err.println("Erro ao copiar o arquivo: " + e.getMessage());
        }
    }
}
