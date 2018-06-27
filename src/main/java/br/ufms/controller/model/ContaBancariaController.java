package br.ufms.controller.model;

import br.ufms.model.bean.ContaBancaria;
import br.ufms.model.bean.ContaCorrente;
import br.ufms.model.bean.ContaPoupanca;
import br.ufms.model.dao.ContaCorrenteDAO;
import br.ufms.model.dao.ContaPoupancaDAO;

public class ContaBancariaController {

    public void criarContaCorrente(ContaCorrente contaCorrente) {
        try {
            new ContaCorrenteDAO().salvar(ContaCorrente.class, contaCorrente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void criarContaPoupanca(ContaPoupanca contaPoupanca) {
        try {
            new ContaPoupancaDAO().salvar(ContaPoupanca.class, contaPoupanca);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
