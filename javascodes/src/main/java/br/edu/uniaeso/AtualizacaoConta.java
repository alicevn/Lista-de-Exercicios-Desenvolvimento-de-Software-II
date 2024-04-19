package br.edu.uniaeso;

import java.io.*;

class ContaBancaria implements Serializable {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
}

public class AtualizacaoConta {
    public static void main(String[] args) {

        ContaBancaria conta = null;
        try {
            FileInputStream arquivoEntrada = new FileInputStream("conta.dat");
            ObjectInputStream objetoEntrada = new ObjectInputStream(arquivoEntrada);

            conta = (ContaBancaria) objetoEntrada.readObject();

            objetoEntrada.close();
            arquivoEntrada.close();

            System.out.println("Conta bancária carregada com sucesso.");
            System.out.println("Titular: " + conta.getTitular());
            System.out.println("Saldo atual: " + conta.getSaldo());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar a conta bancária. " + e.getMessage());
        }

        if (conta != null) {
            conta.depositar(500); 

            try {
                FileOutputStream arquivoSaida = new FileOutputStream("conta.dat");
                ObjectOutputStream objetoSaida = new ObjectOutputStream(arquivoSaida);

                objetoSaida.writeObject(conta);

                objetoSaida.close();
                arquivoSaida.close();

                System.out.println("Conta bancária atualizada e serializada com sucesso.");

            } catch (IOException e) {
                System.err.println("Erro ao serializar a conta bancária. " + e.getMessage());
            }
        }
    }
}
