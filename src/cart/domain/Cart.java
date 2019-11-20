package cart.domain;

import book.domain.Book;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
//    private Map<String,CartItem> map=new LinkedHashMap<>();
    private Map<String,CartItem> map=new LinkedHashMap<>();
//    public double getTotal(){
//        BigDecimal total = new BigDecimal("0");
//        for (CartItem cartItem :map.values()){
//            BigDecimal subtotal = new BigDecimal(cartItem.getSubtotal() + "");
//            total=total.add(subtotal);
//        }
//        return total.doubleValue();
//    }
    public double getTotal(){
        BigDecimal total=new BigDecimal("0");
//        double total=0;
        for(CartItem cartItem: map.values()){
            BigDecimal subTotal = new BigDecimal(cartItem.getSubtotal() + "");
            total = total.add(subTotal);
        }
        return total.doubleValue();
    }
//    public void add(CartItem cartItem){
//        if (map.containsKey(cartItem.getBook().getBid())){
//            CartItem cartItem1 = map.get(cartItem.getBook().getBid());
//            cartItem1.setCount(cartItem1.getCount()+cartItem.getCount());
//            map.put(cartItem.getBook().getBid(),cartItem1);
//        }else {
//            map.put(cartItem.getBook().getBid(),cartItem);
//        }
//    }
    public void add(CartItem cartItem){
        if (map.containsKey(cartItem.getBook().getBid())){
            CartItem cartItem1 = map.get(cartItem.getBook().getBid());
            cartItem1.setCount(cartItem1.getCount()+cartItem.getCount());
            map.put(cartItem.getBook().getBid(),cartItem1);
        }else {
            map.put(cartItem.getBook().getBid(),cartItem);
        }
    }
    public void clear(){
        map.clear();
    }
    public void delete(String bid){
        map.remove(bid);
    }
    public Collection<CartItem> getCartItems(){
        return map.values();
    }
}
