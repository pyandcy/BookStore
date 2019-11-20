package order.dao;

import book.domain.Book;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import order.domain.Order;
import order.domain.OrderItem;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private QueryRunner qr=new TxQueryRunner();
//    public void addOrder(Order order){
//        String sql="insert into orders values(?,?,?,?,?,?)";
//        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
//        Object[] params={order.getOid(),timestamp,order.getTotal(),order.getState(),order.getOwner().getUid(),order.getAddress()};
//        try {
//            qr.update(sql,params);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void addOrder(Order order){
        String sql="insert into orders values(?,?,?,?,?,?)";
        Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
        Object[] params={order.getOid(),timestamp,order.getTotal(),order.getState(),order.getOwner().getUid(),order.getAddress()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public void addOrderItemList(List<OrderItem> orderItemList){
//        String sql="insert into orderitem values(?,?,?,?,?)";
//        Object[][] params=new Object[orderItemList.size()][];
//        for (int i=0;i<orderItemList.size();i++){
//            OrderItem orderItem = orderItemList.get(i);
//            params[i]=new Object[]{orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getOrder().getOid(),orderItem.getBook().getBid()};
//        }
//        try {
//            qr.batch(sql,params);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void addOrderItemList(List<OrderItem> orderItemList) {
        String sql="insert into orderitem values(?,?,?,?,?)";
        Object[][] params=new Object[orderItemList.size()][];
        for (int i=0;i<orderItemList.size();i++){
            OrderItem orderItem = orderItemList.get(i);
            params[i]=new Object[]{orderItem.getIid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getOrder().getOid(),orderItem.getBook().getBid()};
        }
        try {
            qr.batch(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    public List<Order> findByUid(String uid) {
//        String sql="select * from orders where uid=?";
//        try {
//            List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
//            for (Order order :orderList ){
//                loadOrderItems(order);
//            }
//                return orderList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
        public List<Order> findByUid(String uid) {
        String sql="select * from orders where uid=?";
            try {
                List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
                for (Order order: orderList){
                    loadOrderItems(order);
                }
                return orderList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    private void loadOrderItems(Order order) {
        String sql="select * from book b,orderitem i where b.bid=i.bid and i.oid=?";
        try {
            List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
            List<OrderItem> orderItemList=toMapList(mapList);
            order.setOrderItemList(orderItemList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<OrderItem> toMapList(List<Map<String,Object>> mapList) {
        ArrayList<OrderItem> orderItemArrayList = new ArrayList<>();
        for (Map<String,Object> map:mapList){
            OrderItem orderItem=toOrderItem(map);
            orderItemArrayList.add(orderItem);
        }
        return orderItemArrayList;
    }

    private OrderItem toOrderItem(Map<String,Object> map) {
        Book book = CommonUtils.toBean(map, Book.class);
        OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
        orderItem.setBook(book);
        return orderItem;
    }
//    private void loadOrderItems(Order order) {
//        String sql="select * from orderitem i,book b where i.bid=b.bid and i.oid=?";
//        try {
//            List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
//            List<OrderItem> orderItemList=toMapList(mapList);
//            order.setOrderItemList(orderItemList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private List<OrderItem> toMapList(List<Map<String,Object>> mapList) {
//        ArrayList<OrderItem> orderItemList= new ArrayList<>();
//        for (Map<String,Object> map: mapList){
//            OrderItem orderItem=toOrderitem(map);
//            orderItemList.add(orderItem);
//        }
//        return orderItemList;
//    }
//
//    private OrderItem toOrderitem(Map<String,Object> map) {
//        OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
//        Book book = CommonUtils.toBean(map, Book.class);
//        orderItem.setBook(book);
//        return orderItem;
//    }

//    public Order load(String oid) {
//        String sql="select * from orders where oid=?";
//        try {
//            Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
//            loadOrderItems(order);
//            return order;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public Order load(String oid){
        String sql="select * from orders where oid=?";
        try {
            Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
            loadOrderItems(order);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getStateByOid(String oid){
        String sql="select state from orders where oid=?";
        try {
            int state = (Integer)qr.query(sql, new ScalarHandler(), oid);
            return state;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public int getStateByOid(String oid){
//        String sql="select state from orders where oid=?";
//        try {
//            Integer state = (Integer) qr.query(sql, new ScalarHandler(), oid);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public void updateState(String oid,int state){
        String sql="update orders set state=? where oid=?";
        try {
            qr.update(sql,state,oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
