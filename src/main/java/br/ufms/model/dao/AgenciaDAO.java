package br.ufms.model.dao;

import br.ufms.model.bean.Agencia;
import br.ufms.model.bean.Banco;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

public class AgenciaDAO extends DAO<Agencia> {

    public List getAll(Banco banco) {
        List agencias = null;
        EntityManager entityManager = getEM();
        try {
            Query query = entityManager.createNamedQuery("Agencia.getPorBanco").setParameter("parameter", banco.getId());

            agencias = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
        }
        return agencias;
    }

    public void salvar(Agencia agencia, Integer id) {
        EntityManager entityManager = getEM();
        try {
            entityManager.getTransaction().begin();
            Banco banco = entityManager.getReference(Banco.class, id);
            agencia.setBanco(banco);
            agencia.setId(null);
            entityManager.persist(agencia);
            entityManager.merge(banco);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }

}
