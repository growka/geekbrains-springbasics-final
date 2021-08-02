package ru.geekbrains.geekbrainsspringdata.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.geekbrainsspringdata.exceptions.ResourceNotFoundException;
import ru.geekbrains.geekbrainsspringdata.model.entities.OrderItem;
import ru.geekbrains.geekbrainsspringdata.model.entities.Product;
import ru.geekbrains.geekbrainsspringdata.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//Корзину взял из урока, во второй части, так понимаю всё равно перепишем.
@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }

}
