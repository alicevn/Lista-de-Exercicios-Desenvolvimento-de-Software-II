package br.edu.uniaeso;

import java.io.*;

class Pessoa implements Serializable {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}

public class SerializarObjeto{
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Alice", 18);

        try {
            FileOutputStream arquivoSaida = new FileOutputStream("pessoa.dat");
            ObjectOutputStream objetoSaida = new ObjectOutputStream(arquivoSaida);

            objetoSaida.writeObject(pessoa);

            objetoSaida.close();
            arquivoSaida.close();

            System.out.println("Objeto serializado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao serializar o objeto: " + e.getMessage());
        }
    }
}

