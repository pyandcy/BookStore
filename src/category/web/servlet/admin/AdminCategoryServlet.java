package category.web.servlet.admin;

import category.domain.Category;
import category.service.CategoryService;
import category.service.CategoryException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
   private CategoryService categoryService=new CategoryService();
   public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Category> categoryList = categoryService.findAll();
      request.setAttribute("categoryList",categoryList);
      return "f:/adminjsps/admin/category/list.jsp";
   }
   public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
      category.setCid(CommonUtils.uuid());
      categoryService.add(category);
      request.setAttribute("categoryList",categoryService.findAll());
      return "f:/adminjsps/admin/category/list.jsp";
   }
   public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String cid = request.getParameter("cid");
      try {
         categoryService.delete(cid);
         return findAll(request,response);
      }catch (CategoryException e){
         request.setAttribute("msg",e.getMessage());
         return "f:/adminjsps/msg.jsp";
      }
   }
   public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String cid = request.getParameter("cid");
      Category category=categoryService.load(cid);
      request.setAttribute("category",category);
      return "f:/adminjsps/admin/category/mod.jsp";
   }
   public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
      categoryService.edit(category);
      return findAll(request,response);
   }
}
