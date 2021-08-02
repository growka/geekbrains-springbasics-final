package ru.geekbrains.geekbrainsspringdata.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.geekbrainsspringdata.model.entities.OrderItem;


@NoArgsConstructor
@Data
public class OrderItemDto {
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
