package dao;

import model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Реализует CRUD операции работы с базой данных сущности "Прайс-лист" (entity Product)
 * Использует контекст базы данных "myUnit"
 *
 * @author Rabadanov A.G.
 */
@Stateless
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext(name = "myUnit")
    private EntityManager em;

    public List<Product> getListOfProducts() {
        TypedQuery<Product> result = em.createNamedQuery("allProducts", Product.class);
        return result.getResultList();
    }

    @Override
    public Product getProductByID(Integer id) {
        TypedQuery<Product> result = em.createNamedQuery("productByID", Product.class);
        result.setParameter("id",id);
        return result.getSingleResult();
    }
}
