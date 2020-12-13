package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAllSkill() {
        String jpql = "select s skill s";
        EntityManager em = em();
        Query query = em.createQuery(jpql);
        List<Skill> list = query.getResultList();
        return list;
    }

    @Override
    public Skill getSkillById(int id) {
        EntityManager em = em();
        Skill u = em.find(Skill.class, id);
        close();
        return u;
    }

    @Override
    public boolean removeSkill(int id) {
        EntityManager em = em();
        em.getTransaction().begin();
        Skill skill = em.find(Skill.class, id);
        em.remove(skill);
        em.getTransaction().commit();
        close();
        return false;
    }

    @Override
    public boolean updateSkill(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(skill);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean addSkill(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();
        return true;
    }

}
