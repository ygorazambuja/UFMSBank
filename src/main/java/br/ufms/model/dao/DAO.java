package br.ufms.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/*
    @author Ygor Azambuja
    @date 23/06/2018
 */
public abstract class DAO<B extends EntidadeBase> {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ufmsbank");

    protected EntityManager getEM() {
        return emf.createEntityManager();
    }

    public B salvar(Class<B> clazz, B bean) throws Exception {
       EntityManager em = getEM();
       try {
           em.getTransaction().begin();
           if(bean.getId() == null) {
               em.persist(bean);
           } else {
               if(!em.contains(bean)) {
                   if(em.find(clazz, bean.getId()) == null) {
                       throw new Exception("Erro!");
                   }
               }
               bean = em.merge(bean);
           }
           em.getTransaction().commit();
       } catch (PersistenceException pe) {
           System.err.println(pe.getMessage());
       } finally {
           em.close();
       }
       return bean;
    }

    public void delete(Class<B> clazz, B bean) {

        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.remove(bean);
            em.getTransaction().commit();

        } catch (PersistenceException pe) {
            System.err.println(pe.getMessage());
        } finally {
            em.close();
        }
    }
    public B getPorId(Class<B> clazz, Integer id) {
        EntityManager em = getEM();
        B b = null;
        try {
            b = em.find(clazz, id);
        } finally {
            em.close();
        }
        return b;
    }

    public void deleteComReferencia(Class<B> clazz, B bean) {

        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.remove(em.getReference(clazz, bean.getId()));
            em.getTransaction().commit();

        } catch (PersistenceException pe) {
            System.err.println(pe.getMessage());
        } finally {
            em.close();
        }
    }


}
