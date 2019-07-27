package dao;

import model.Order;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext(name = "myUnit")
    private EntityManager em;

    public void addOrder(Order order) {
        em.persist(order);
    }

    public void updateOrder(Order order) {
        em.merge(order);
    }

    public void removeOrder(Integer id) {
        Order order = getOrderById(id);

        if (order!=null) {
            em.remove(order);
        }
    }

    public Order getOrderById(Integer id) {
        Order order = (Order) em.find(Order.class, new Integer(id));
        return order;
    }

    public List<Order> getListOfOrders() {
        TypedQuery<Order> result = em.createNamedQuery("allOrders", Order.class);
        return result.getResultList();
    }
}
