package com.example.web.dao.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
    private Map<String, CartPainting> items;
    public Cart() {
        items = new HashMap<>();
    }
    public List<CartPainting> getItems() {
        return new ArrayList<>(items.values());
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
    public double getTotalPrice(){
        double total = 0;
        for(CartPainting painting : items.values()){
            total += painting.getTotalPrice();
        }
        return total;
    }


    public static void main(String[] args) {
        Cart cart = new Cart();
        System.out.println(cart.getItems());
    }

}
