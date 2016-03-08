package com.github.k3286.one_system.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import com.github.k3286.one_system.dao.UserDao;
import com.github.k3286.one_system.model.User;

public class UserDaoImpl implements UserDao {

    @Override
    public User getById(String id) {
        // TODO 検索条件の絞り込み
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("brycen");
        EntityManager em = fac.createEntityManager();
        CriteriaQuery<User> q = em.getCriteriaBuilder().createQuery(User.class);
        return em.createQuery(q.select(q.from(User.class))).getResultList().get(0);
    }

    @Override
    public List<User> getUserListByName(String name) {
        // TODO 検索条件の絞り込み
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("brycen");
        EntityManager em = fac.createEntityManager();
        CriteriaQuery<User> q = em.getCriteriaBuilder().createQuery(User.class);
        return em.createQuery(q.select(q.from(User.class))).getResultList();
    }

    @Override
    public List<User> getUserList() {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("brycen");
        EntityManager em = fac.createEntityManager();
        CriteriaQuery<User> q = em.getCriteriaBuilder().createQuery(User.class);
        return em.createQuery(q.select(q.from(User.class))).getResultList();
    }

    @Override
    public void add(User user) {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("brycen");
        EntityManager em = fac.createEntityManager();
        try {
            em.getTransaction().begin();

            em.persist(user);

        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("brycen");
        EntityManager em = fac.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO 更新処理
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(String id) {
        EntityManagerFactory fac = Persistence.createEntityManagerFactory("brycen");
        EntityManager em = fac.createEntityManager();
        try {
            em.getTransaction().begin();
            // TODO 削除処理
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.getTransaction().commit();
        }
    }

}
