package br.ufms.application;

import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;
import br.ufms.model.dao.AgenciaDAO;
import br.ufms.model.dao.BancoDAO;

public class Main {

    public static void main(String[] args) throws Exception {

        Banco banco = new Banco();
        banco.setNome("");
        new BancoDAO().salvar(Banco.class, banco);

        Agencia agencia = new Agencia();
        agencia.setBanco(new BancoDAO().getPorId(Banco.class, 2));
        new AgenciaDAO().salvar(Agencia.class, agencia);

    }

}
