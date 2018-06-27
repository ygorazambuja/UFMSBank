package br.ufms.controller.model;

import br.ufms.model.bean.Banco;
import br.ufms.model.dao.BancoDAO;

public class BancoController {

    public void adicionar(String nome) {
        Banco banco = new Banco();
        banco.setNome(nome);
        try {
            new BancoDAO().salvar(Banco.class, banco);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void remover(String nome, String id) {
        if (!nome.equals("") || !id.equals("")) {
            Banco banco = new BancoDAO().getPorId(Banco.class, Integer.parseInt(id));
            if (banco != null) {
                new BancoDAO().deleteComReferencia(Banco.class, banco);
            } else {
                new BancoDAO().delete(Banco.class, banco);
            }
        } else {
            throw new IllegalArgumentException("Invalido");
        }
    }
}
