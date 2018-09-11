package com.github.molodtsov.russianRoulette.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static EntityManagerFactory emf;
    private static EntityManager em;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory( "ProdPersistenceUnit");
        em = emf.createEntityManager();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (em != null) em.close();
        em = null;
        if (emf != null) emf.close();
        emf = null;
    }

    public static EntityManager getEntityManager() {
        return em;
    }
}
