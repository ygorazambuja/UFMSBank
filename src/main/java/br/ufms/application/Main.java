package br.ufms.application;

import br.ufms.controller.BancoController;
import br.ufms.model.bean.Banco;
import br.ufms.model.dao.BancoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Banco> bancos = new BancoDAO().getAll();

        bancos.forEach(banco -> System.out.println(banco.getNome() + " " + banco.getId()));

    }

}
