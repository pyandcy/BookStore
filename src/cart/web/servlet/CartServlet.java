package cart.web.servlet;

import book.domain.Book;
import book.service.BookService;
import cart.domain.Cart;
import cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet")
public class CartServlet extends BaseServlet {
//    public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        String bid = req.getParameter("bid");
//        BookService bookService = new BookService();
//        Book book = bookService.load(bid);
//        String count = req.getParameter("count");
//        CartItem cartItem = new CartItem();
//        cartItem.setCount(Integer.parseInt(count));
//        cartItem.setBook(book);
//        cart.add(cartItem);
//        return "f:/jsps/cart/list.jsp";
//    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String bid = request.getParameter("bid");
        BookService bookService = new BookService();
        Book book = bookService.load(bid);
        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        Integer count = Integer.parseInt(request.getParameter("count"));
        cartItem.setCount(count);
        cart.add(cartItem);
        return "f:/jsps/cart/list.jsp";
    }

    public String clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        return "f:/jsps/cart/list.jsp";
    }
    public String delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        String bid = req.getParameter("bid");
        cart.delete(bid);
        return "f:/jsps/cart/list.jsp";
    }
}
