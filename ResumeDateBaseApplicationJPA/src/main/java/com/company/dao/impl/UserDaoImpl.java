package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAllUser(String name, String surname, String nationalityId) {
        EntityManager em = em();
        String jpql = "select u from User u where 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name ";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname";
        }
        if (nationalityId != null) {
            jpql += " and u.nationality.name=:natName";
        }
        Query query = em.createQuery(jpql);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);

        }
        if (nationalityId != null && !nationalityId.trim().isEmpty()) {
            query.setParameter("natName", nationalityId);

        }

        List<User> list = query.getResultList();
        close();
        return list;
    }

    @Override
    public User getUserById(int id) {
        EntityManager em = em();
        User u = em.find(User.class, id);
        close();
        return u;
    }

    @Override
    public boolean removeUser(int id) {
        EntityManager em = em();
        em.getTransaction().begin();
        User u = em.find(User.class, id);
        em.remove(u);
        em.getTransaction().commit();
        close();
        return true;
    }

    @Override
    public boolean addUser(User u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        close();
        return true;

    }

    public User getEmail(String email) {
        EntityManager em = em();
        Query query = em.createQuery("select u from User u where u.email=:email");
        query.setParameter("email", email);
        List<User> list = query.getResultList();
        close();
        return list.get(0);
    }

    @Override
    public boolean updateUser(User u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        close();
        return true;

    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.removeUser(3);
        System.out.println(userDao.getAllUser(null, null, null));
    }
}
