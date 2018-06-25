package br.ufms.application;

import br.ufms.model.bean.ContaCorrente;
import br.ufms.model.dao.ContaCorrenteDAO;

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
