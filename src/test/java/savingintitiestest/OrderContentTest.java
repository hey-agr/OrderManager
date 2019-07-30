package savingintitiestest;

import model.Order;
import model.OrderContent;
import model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderContentTest {
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
    public void shouldCreateOrderContent() throws Exception {

        OrderContent orderContent = new OrderContent();
        orderContent.setCount(new BigDecimal(1));
        orderContent.setPrice(new BigDecimal(100.10));
        orderContent.setSum(new BigDecimal(100.10));
        em.getTransaction().begin();
        em.persist(orderContent);
        em.getTransaction().commit();
        assertNotNull(orderContent.getId());
    }

    @Test
    public void shouldFindOrderContent() throws Exception {
        Order newOrder = new Order();
        newOrder.setNumber(58483);
        newOrder.setCustomerEmail("newemail@gmail.com");

        OrderContent newOrderContent = new OrderContent();
        newOrderContent.setCount(new BigDecimal(1));
        newOrderContent.setPrice(new BigDecimal(100.10));
        newOrderContent.setSum(new BigDecimal(100.10));
        newOrderContent.setOrder(newOrder);

        em.getTransaction().begin();
        em.persist(newOrder);
        em.persist(newOrderContent);
        em.getTransaction().commit();

        Query result = em.createQuery("select o from ORDERSCONTENT o where o.order.number = :number",OrderContent.class);
        result.setParameter("number",58483);
        OrderContent orderContent = (OrderContent) result.getSingleResult();

        assertNotNull(orderContent);
    }


}