package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "ORDERS")
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name="allOrders", query="SELECT o FROM ORDERS o")
})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    @Column(name="ordertime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;

    @PrePersist
    protected void onCreate() {
        orderTime = new Date();
    }

    @Column(name="customeremail", length=255)
    private String customerEmail;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderContent> orderContents = new ArrayList<OrderContent>();

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<OrderContent> getOrderContents() {
        return orderContents;
    }

    public void setOrderContents(List<OrderContent> orderContents) {
        this.orderContents = orderContents;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

//    public void addOrderContent(OrderContent orderContent) {
//        this.orderContents.add(orderContent);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id != null ? id.equals(order.id) : order.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
