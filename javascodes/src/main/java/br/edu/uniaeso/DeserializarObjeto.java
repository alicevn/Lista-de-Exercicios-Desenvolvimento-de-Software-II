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

public class DeserializarObjeto {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream arquivoEntrada = new FileInputStream("pessoa.dat");
        ObjectInputStream objetoEntrada = new ObjectInputStream(arquivoEntrada);

        Pessoa pessoa = (Pessoa) objetoEntrada.readObject();

        objetoEntrada.close();
        arquivoEntrada.close();

        System.out.println("Detalhes da pessoa:");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Idade: " + pessoa.getIdade());
    }
}
