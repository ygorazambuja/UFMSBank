package br.ufms.model.dao;

import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.ContaBancaria;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ContaBancariaDAO extends DAO<ContaBancaria> {

    public ContaBancaria getPorNome(String nome) {
        ContaBancaria contaBancaria = null;
        EntityManager entityManager = getEM();
        List<ContaBancaria> contaBancarias;

        Query query = entityManager.createNamedQuery("getPorNome").setParameter("nomeUsuario", nome);
        contaBancarias = query.getResultList();
        if (contaBancarias == null || contaBancarias.isEmpty()) {
            return null;
        }
        return contaBancarias.get(0);
    }

    public void salvar(ContaBancaria contaBancaria, Integer agenciaId) {
        EntityManager entityManager = getEM();
        try {
            entityManager.getTransaction().begin();
            Agencia agencia = entityManager.find(Agencia.class, agenciaId);
            contaBancaria.setAgencia(agencia);
            entityManager.persist(contaBancaria);

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

}
