package model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity(name = "ORDERSCONTENT")
@Table(name = "ordercontent")
@NamedQueries({
        @NamedQuery(name="allOrdersContent", query="SELECT content FROM ORDERSCONTENT content"),
        @NamedQuery(name="allOrdersContentByOrder", query="SELECT content FROM ORDERSCONTENT content WHERE content.order = :order")})
public class OrderContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal price;

    private BigDecimal count;

    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderContent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderContent that = (OrderContent) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
