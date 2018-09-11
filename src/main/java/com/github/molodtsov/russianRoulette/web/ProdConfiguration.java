package com.github.molodtsov.russianRoulette.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "com.github.molodtsov.russianRoulette")
public class ProdConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory( "ProdPersistenceUnit");

    }
    @Bean
    public EntityManager getEntityManager(@Autowired EntityManagerFactory emf){
        return emf.createEntityManager();
    }
}
