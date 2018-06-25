package br.ufms.application;

import br.ufms.controller.BancoController;
import br.ufms.model.bean.Banco;
import br.ufms.model.bean.ContaCorrente;
import br.ufms.model.bean.ContaPoupanca;
import br.ufms.model.dao.BancoDAO;
import br.ufms.model.dao.ContaCorrenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setCorrentista("Joaquim Cego");
        contaCorrente.setLimite(5000.);
        contaCorrente.setSaldo(5000.);
        contaCorrente.setNomeUsuario("joaquimcego");
        contaCorrente.setSenha("senha");

        try {
            new ContaCorrenteDAO().salvar(ContaCorrente.class, contaCorrente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
