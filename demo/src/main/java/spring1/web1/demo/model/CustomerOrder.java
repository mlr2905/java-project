package spring1.web1.demo.model;

import lombok.Getter;
import lombok.Setter;

public class CustomerOrder {

    @Getter@Setter
    protected Integer id;

    @Getter@Setter
    protected Integer customerId;

    @Getter@Setter
    protected String itemName;

    @Getter@Setter
    protected Double price;

    public CustomerOrder(Integer id, Integer customerId, String itemName, Double price) {
        this.id = id;
        this.customerId = customerId;
        this.itemName = itemName;
        this.price = price;
    }
}
