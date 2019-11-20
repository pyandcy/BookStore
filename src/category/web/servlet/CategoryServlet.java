package category.web.servlet;

import category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryService();
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryList",categoryService.findAll());
        return "f:/jsps/left.jsp";
    }
}
