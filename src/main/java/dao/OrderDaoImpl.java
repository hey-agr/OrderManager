package dao;

import model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext(name = "myUnit")
    private EntityManager em;

    public void addOrder(Order order) {
        em.persist(order);
    }

    public void updateOrder(Order order) {
        em.merge(order);
        em.flush();
    }

    public void removeOrder(Integer id) {
        Order order = (Order) em.find(Order.class, new Integer(id));

        if (order!=null) {
            em.remove(order);
        }
    }

    public Order getOrderById(Integer id) {
        Order order = (Order) em.find(Order.class, new Integer(id));
        return order;
    }

    @SuppressWarnings("unchecked")
    public List<Order> listOrders() {
        List<Order> orderList = em.createQuery("SELECT o FROM ORDERS o").getResultList();
        return orderList;
    }
}
