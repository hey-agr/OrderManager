package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Сущность "Прайс-лист" (entity Product)
 * предназначена для сохрания данных в таблицу БД "products",
 * представляет собой единицу прайс-листа,
 * хранит в себе название товара, стоимость,
 * имеет связь многие к одному с сущностью "Состав заказа" (entity OrderContent).
 *
 * @author Rabadanov A.G.
 */
@Entity(name = "PRODUCTS")
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name="allProducts", query="SELECT product FROM PRODUCTS product"),
        @NamedQuery(name="productByID", query="SELECT product FROM PRODUCTS product WHERE id = :id")
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private BigDecimal price;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id == product.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
