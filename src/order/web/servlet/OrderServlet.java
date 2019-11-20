package order.web.servlet;

import cart.domain.Cart;
import cart.domain.CartItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import order.domain.Order;
import order.domain.OrderItem;
import order.service.OrderException;
import order.service.OrderService;
import org.omg.CORBA.Request;
import user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService=new OrderService();
//    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cart cart = (Cart)request.getSession().getAttribute("cart");
//        Order order = new Order();
//        order.setOid(CommonUtils.uuid());
//        order.setOrdertime(new Date());
//        order.setTotal(cart.getTotal());
//        order.setState(1);
//        User user = (User) request.getSession().getAttribute("session_user");
//        order.setOwner(user);
//        ArrayList<OrderItem> orderItemList = new ArrayList<>();
//        for (CartItem cartItem :cart.getCartItems()){
//            OrderItem oi = new OrderItem();
//            oi.setIid(CommonUtils.uuid());
//            oi.setBook(cartItem.getBook());
//            oi.setCount(cartItem.getCount());
//            oi.setOrder(order);
//            oi.setSubtotal(cartItem.getSubtotal());
//            orderItemList.add(oi);
//        }
//        order.setOrderItemList(orderItemList);
//        cart.clear();
//        orderService.add(order);
//        request.getSession().setAttribute("order",order);
//        return "f:/jsps/order/desc.jsp";
//    }
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Order order = new Order();
        order.setOid(CommonUtils.uuid());
        order.setOrdertime(new Date());
        order.setState(1);
        order.setTotal(cart.getTotal());
        User user =(User) request.getSession().getAttribute("session_user");
        order.setOwner(user);
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem: cart.getCartItems()){
            OrderItem oi = new OrderItem();
            oi.setBook(cartItem.getBook());
            oi.setSubtotal(cart.getTotal());
            oi.setOrder(order);
            oi.setCount(cartItem.getCount());
            oi.setIid(CommonUtils.uuid());
            orderItemList.add(oi);
        }
        order.setOrderItemList(orderItemList);
        cart.clear();
        orderService.add(order);
        request.getSession().setAttribute("order",order);
        return "f:/jsps/order/desc.jsp";
    }

//    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        User user = (User) request.getSession().getAttribute("session_user");
//        List<Order> orderList=orderService.myOrders(user.getUid());
//        request.getSession().setAttribute("orderList",orderList);
//        return "f:/jsps/order/list.jsp";
//    }
    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("session_user");
        List<Order> orderList = orderService.myOrders(user.getUid());
        request.getSession().setAttribute("orderList",orderList);
        return "f:/jsps/order/list.jsp";
    }
//    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String oid = request.getParameter("oid");
//        request.getSession().setAttribute("order",orderService.load(oid));
//        return "f:/jsps/order/desc.jsp";
//    }
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        request.getSession().setAttribute("order",orderService.load(oid));
        return "f:/jsps/order/desc.jsp";
    }

    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        try {
            orderService.confirm(oid);
            request.setAttribute("msg","恭喜，交易成功！");
        } catch (OrderException e) {
            request.setAttribute("msg",e.getMessage());
        }
        return "f:/jsps/msg.jsp";
    }
}
