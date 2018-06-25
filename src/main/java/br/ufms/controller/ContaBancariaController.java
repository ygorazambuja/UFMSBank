package br.ufms.controller;

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

}
