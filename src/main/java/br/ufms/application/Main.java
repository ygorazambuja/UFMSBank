package br.ufms.application;


import br.ufms.model.dao.AgenciaDAO;
import br.ufms.model.dao.BancoDAO;
import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;

public class Main {

    public static void main(String[] args) {

        Banco banco = new BancoDAO().getPorId(Banco.class, 4);

        Agencia agencia = new Agencia();
        agencia.setBanco(banco);

        try {
            new AgenciaDAO().salvar(Agencia.class, agencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
