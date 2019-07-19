package controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ApplicationEJB {

    @PersistenceContext(unitName = "myUnit")
    EntityManager entityManager;

}
