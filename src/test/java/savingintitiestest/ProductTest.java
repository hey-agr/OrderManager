package savingintitiestest;

import model.Order;
import model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {
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
    public void shouldCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("Tomato");
        product.setPrice(new BigDecimal(100.00));
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        assertNotNull(product.getId());
    }

    @Test
    public void shouldFindProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("Potato");
        newProduct.setPrice(new BigDecimal(200.00));
        em.getTransaction().begin();
        em.persist(newProduct);
        em.getTransaction().commit();

        Query result = em.createQuery("select o from PRODUCTS o where o.name = :name",Product.class);
        result.setParameter("name","Potato");
        Product product = (Product) result.getSingleResult();

        assertNotNull(product);
    }

}