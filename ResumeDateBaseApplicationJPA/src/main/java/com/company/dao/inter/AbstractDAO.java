package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractDAO {

    EntityManagerFactory emf = null;

    public Connection connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String userName = "root";
        String password = "54321";
        String url = "jdbc:mysql://localhost:3306/myresume?serverTimezone=" + TimeZone.getDefault().getID();
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

    public EntityManager em() {
        emf = Persistence.createEntityManagerFactory("myresumeJPA");
        EntityManager entityManager = emf.createEntityManager();
        return entityManager;
    }

    public void close() {
        emf.close();
    }
}
