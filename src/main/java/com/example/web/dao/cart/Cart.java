package com.example.web.dao.cart;

import com.google.gson.Gson;

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
    public Map<String, CartPainting> getItemsMap() {
        return items;
    }
    public void addToCart(CartPainting painting){
        String key = painting.getProductId()+"_"+painting.getSizeId();
        if(items.containsKey(key)){
            items.get(key).updateQuantity(painting.getQuantity());
        }else{
            items.put(key,painting);
        }
    }
    public void removeFromCart(String productId, String sizeId){
        String key = productId+"_"+sizeId;
        items.remove(key);
    }

   // public void upDateCartQuantity(int){

    //}
    public double getTotalPrice(){
        double total = 0;
        for(CartPainting painting : items.values()){
            total += painting.getTotalPrice();
        }
        return total;
    }
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this.items);
    }




}
