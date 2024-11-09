package com.driver;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController {
    private Menu menu;
    private Order currentOrder;

    public FoodController() {
    	// your code goes here
        this.menu= new Menu();
        this.currentOrder =new Order();

        menu.addMenuItem(new Food(1, "Burger", 5.99));
        menu.addMenuItem(new Food(2, "pizza", 7.22));
        menu.addMenuItem(new Food(3, "salad", 4.99));
    }

    @GetMapping("/menu")
    public List<Food> getMenu() {
    	// your code goes here
        return menu.getMenuItems();
    }

    @PostMapping("/order/{itemId}")
    public void placeOrder(@PathVariable int itemId) {
    	// your code goes here
        Food foodItem = menu.getMenuItemById(itemId);
        if (foodItem != null) {
            currentOrder.addItem(foodItem);
        } else {
            throw new IllegalArgumentException("Item with ID " + itemId + " not found in menu.");
        }
    }

    @GetMapping("/order")
    public Order getCurrentOrder() {
    	// your code goes here
        return currentOrder;
    }

    @GetMapping("/order/total")
    public double getTotalBill() {
    	// your code goes here
        return currentOrder.getTotalBill();
    }
}
