package com.example.web.dao.cart;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartPainting> items;
    public Cart() {
        items = new HashMap<>();
    }
    public void addToCart(CartPainting painting){
        String key = painting.getProductId()+"_"+painting.getSize();
        if(items.containsKey(key)){
            items.get(key).updateQuantity(painting.getQuantity());
        }else{
            items.put(key,painting);
        }
    }
    public void removeFromCart(String key){
        items.remove(key);
    }
    public Map<String, CartPainting> getItems() {
        return items;
    }

    public static void main(String[] args) {
        Cart cart = new Cart();
        for (CartPainting painting : cart.getItems().values()) {
            System.out.println(painting);
        }
    }

}
