package book.web.servlet;

import book.domain.Book;
import book.service.BookService;
import cn.itcast.servlet.BaseServlet;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService=new BookService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("bookList",bookService.findAll());
        return "f:/jsps/book/list.jsp";
    }
//    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String cid = request.getParameter("cid");
//        request.setAttribute("bookList",bookService.findByCategory(cid));
//        return "f:/jsps/book/list.jsp";
//    }
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("bookList",bookService.findByCategory(cid));
        return "f:/jsps/book/list.jsp";
    }

    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        request.setAttribute("book",bookService.load(bid));
        return "f:/jsps/book/desc.jsp";
    }
}
