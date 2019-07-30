import model.Order;
import org.hibernate.Hibernate;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderTest {

    EntityManager em;
    EntityTransaction tx;

    @Before
    public void setUp() {
        em = Persistence.createEntityManagerFactory("testUnit").createEntityManager();
        tx = em.getTransaction();
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
        em.persist(order);

        assertNotNull(order.getId());
    }

    @Test
    public void shouldFindOrder() throws Exception {

    }
}
