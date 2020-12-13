package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    @Override
    public List<Country> getAllCountry() {
        EntityManager em = em();
        Query query = em.createNamedQuery("Country.findAll", Country.class);
        List<Country> list = query.getResultList();
        close();
        return list;
    }

    @Override
    public Country getCountryById(int id) {
        String jpql = "select c Country c where c.id=:id";
        EntityManager em = em();
        Query query = em.createQuery(jpql);
        Country country = (Country) query.getSingleResult();
        return country;
    }

    @Override
    public boolean removeCountry(int id) {
        EntityManager em = em();
        em.getTransaction().begin();
        Country country = em.find(Country.class, id);
        em.remove(country);
        em.getTransaction().commit();
        close();
        return false;
    }

    @Override
    public boolean addCountry(Country country) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();
        return true;

    }

    @Override
    public boolean updateCountry(Country country) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(country);
        em.getTransaction().commit();
        return true;
    }

}
