package order.service;

import cn.itcast.jdbc.JdbcUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import order.dao.OrderDao;
import order.domain.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao=new OrderDao();
//    public void add(Order order){
//        try {
//            JdbcUtils.beginTransaction();
//            orderDao.addOrder(order);
//            orderDao.addOrderItemList(order.getOrderItemList());
//            JdbcUtils.commitTransaction();
//        }catch (SQLException e){
//            try {
//                JdbcUtils.rollbackTransaction();
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
    public void add(Order order){
        try {
            JdbcUtils.beginTransaction();
            orderDao.addOrder(order);
            orderDao.addOrderItemList(order.getOrderItemList());
            JdbcUtils.commitTransaction();
        } catch (SQLException e) {
            try {
                JdbcUtils.rollbackTransaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
    public List<Order> myOrders(String uid) {
        return orderDao.findByUid(uid);
    }

    public Order load(String oid) {
        return orderDao.load(oid);
    }
//    public void confirm(String oid) throws OrderException{
//        int state = orderDao.getStateByOid(oid);
//        if (state!=3) throw new OrderException("订单确认失败，您的订单状态不为3！");
//        orderDao.updateState(oid,4);
//    }
    public void confirm(String oid) throws OrderException{
        int state= orderDao.getStateByOid(oid);
        if (state!=3) throw new OrderException("订单确认失败，您的订单状态不为3");
        orderDao.updateState(oid,4);
    }
}
