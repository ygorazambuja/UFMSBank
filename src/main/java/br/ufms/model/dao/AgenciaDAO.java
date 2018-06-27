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

}
