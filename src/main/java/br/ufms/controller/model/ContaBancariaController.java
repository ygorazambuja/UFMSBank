package br.ufms.controller.model;

import br.ufms.model.bean.ContaBancaria;

public class ContaBancariaController {

    public void transfere(ContaBancaria remetente, ContaBancaria destinatario, double valor) {
        if (saque(remetente, valor)) {
            deposita(destinatario, valor);
        } else {
            throw new IllegalArgumentException("Não pude realizar essa ação");
        }
    }

    public boolean saque(ContaBancaria contaBancaria, double valor) {
        if (valor <= contaBancaria.getSaldo()) {
            contaBancaria.setSaldo(contaBancaria.getSaldo() - valor);
            return true;
        } else {
            throw new IllegalArgumentException("Saldo Insuficente");
        }
    }

    public void deposita(ContaBancaria contaBancaria, double valor) {
        contaBancaria.setSaldo(contaBancaria.getSaldo() + valor);
    }
}
