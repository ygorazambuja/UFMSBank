package br.ufms.application;


import br.ufms.controller.BancoController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ufmsbank");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
    }

}
