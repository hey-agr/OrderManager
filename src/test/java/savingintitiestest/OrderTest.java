package savingintitiestest;

import model.Order;
import org.hibernate.Hibernate;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderTest {

    private EntityManager em;

    @Before
    public void setUp() {
        em = Persistence.createEntityManagerFactory("testUnit").createEntityManager();
    }

    @After
    public void tearDown() {
        this.em.clear();
        this.em.close();
    }

    @Test
    public void shouldCreateOrder() throws Exception {
        Order order = new Order();
        order.setNumber(102837);
        order.setCustomerEmail("myemail@gmail.com");
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        assertNotNull(order.getId());
    }

    @Test
    public void shouldFindOrder() throws Exception {
        Order newOrder = new Order();
        newOrder.setNumber(939843);
        newOrder.setCustomerEmail("newemail@gmail.com");
        em.getTransaction().begin();
        em.persist(newOrder);
        em.getTransaction().commit();

        Query result = em.createQuery("select o from ORDERS o where o.number = :number",Order.class);
        result.setParameter("number",939843);
        Order order = (Order) result.getSingleResult();

        assertNotNull(order);
    }

}
