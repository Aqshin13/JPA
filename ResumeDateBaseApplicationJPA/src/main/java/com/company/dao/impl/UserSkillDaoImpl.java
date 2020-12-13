package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.UserSkill;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    @Override
    public List<UserSkill> getAllUserSkill() {
        EntityManager em = em();
        Query query = em.createNamedQuery("UserSkill.findAll", UserSkill.class);
        List<UserSkill> list = query.getResultList();
        return list;
    }

    @Override
    public UserSkill getUserSkillById(int id) {
        EntityManager em = em();
        UserSkill u = em.find(UserSkill.class, id);
        close();
        return u;
    }

    @Override
    public boolean removeUserSkill(int id) {
        EntityManager em = em();
        em.getTransaction().begin();
        UserSkill userSkill = em.find(UserSkill.class, id);
        em.remove(userSkill);
        em.getTransaction().commit();
        close();
        return false;
    }

    @Override
    public boolean updateUserSkill(UserSkill userSkills) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(userSkills);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean addUserSkill(UserSkill skills) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(skills);
        em.getTransaction().commit();
        return true;
    }

}
