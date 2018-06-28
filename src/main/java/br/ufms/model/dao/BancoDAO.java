package br.ufms.model.dao;

import br.ufms.model.bean.Banco;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class BancoDAO extends DAO<Banco> {


   /* public List<Banco> getAll() {
        List bancos = null;
        EntityManager entityManager = getEM();
        try {
            TypedQuery<Banco> query = entityManager.createQuery("SELECT b FROM Banco b", Banco.class);
            bancos = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }

        return bancos;
    }*/

    public List<Banco> getAll() {

        List bancos = null;
        EntityManager entityManager = getEM();
        try {
            Query query = entityManager.createNamedQuery("Banco.getAll");
            bancos = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
        return bancos;
    }

    public Banco get(Integer id) {
        Banco banco;
        List<Banco> bancos;
        EntityManager entityManager = getEM();
        Query query = entityManager.createNamedQuery("Banco.getPorId").setParameter("parameter", id);
        bancos = query.getResultList();
        if (bancos == null || bancos.isEmpty()) {
            return null;
        }

        return bancos.get(0);
    }


}
