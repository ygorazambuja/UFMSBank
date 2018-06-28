package br.ufms.application;

import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;
import br.ufms.model.dao.AgenciaDAO;
import br.ufms.model.dao.BancoDAO;

public class Main {

    public static void main(String[] args) throws Exception {

        new BancoDAO().salvar(Banco.class, new Banco("UFMS Bank"));
        for (int i = 0; i < 5; i++) {
            new AgenciaDAO().salvar(new Agencia(), 2);
        }

    }
}
