package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import java.util.List;
import javax.persistence.EntityManager;

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    @Override
    public List<EmploymentHistory> getAllEmploymentHistory() {
        EntityManager em = em();
        return em.createQuery("select e employment_history e ").getResultList();
    }

    @Override
    public EmploymentHistory getAllEmploymentHistoryById(int id) {
        EntityManager em = em();
        EmploymentHistory u = em.find(EmploymentHistory.class, id);
        close();
        return u;
    }

    @Override
    public boolean removeEmploymentHistory(int id) {
        EntityManager em = em();
        em.getTransaction().begin();
        EmploymentHistory employmentHistory = em.find(EmploymentHistory.class, id);
        em.remove(employmentHistory);
        em.getTransaction().commit();
        close();
        return false;
    }

    @Override
    public boolean addEmploymentHistory(EmploymentHistory employmentHistory) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(employmentHistory);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean updateEmploymentHistory(EmploymentHistory employmentHistory) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(employmentHistory);
        em.getTransaction().commit();
        return true;
    }

}
