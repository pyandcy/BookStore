package user.web.servlet;

import cart.domain.Cart;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import user.domain.User;
import user.service.UserException;
import user.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserServlet extends BaseServlet{
  private UserService userService=new UserService();
  public String regist(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    User form = CommonUtils.toBean(request.getParameterMap(), User.class);
    form.setUid(CommonUtils.uuid());
    form.setCode(CommonUtils.uuid()+CommonUtils.uuid());
    Map<String, String> error = new HashMap<>();
    if (form.getUsername()==null||form.getUsername().trim().isEmpty()){
      error.put("username","用户名不能为空！");
    }else if (form.getUsername().length()<3||form.getUsername().length()>10){
      error.put("username","用户名长度需要在3-10");
    }

    if (form.getPassword()==null||form.getPassword().trim().isEmpty()){
      error.put("password","密码不能为空！");
    }else if (form.getPassword().length()<3||form.getPassword().length()>10){
      error.put("password","密码长度需要在3-10");
    }

    if (form.getVerifyCode()==null||form.getVerifyCode().trim().isEmpty()){
      error.put("verifyCode","verifyCode不能为空！");
    }else if (form.getVerifyCode().length()!=4){
      error.put("verifyCode","verifyCode长度应为4位！");
    }
//    User form = CommonUtils.toBean(request.getParameterMap(), User.class);
//    form.setCode(CommonUtils.uuid());
//    form.setUid(CommonUtils.uuid());
//    Map<String, String> error = new HashMap<>();
//    if (form.getUsername()==null||form.getUsername().trim()==null){
//      error.put("username","用户名不能为空！");
//    }else if (form.getUsername().length()<=3||form.getUsername().length()>=10){
//      error.put("username","用户名长度应在3——10");
//    }
    if (error.size()>0){
      request.setAttribute("error",error);
      request.setAttribute("form",form);
      return "f:/jsps/user/regist.jsp";
    }

    String sessionVcode =(String) request.getSession().getAttribute("session_vcode");
    if (!sessionVcode.equalsIgnoreCase(form.getVerifyCode())){
      request.setAttribute("msg","验证码错误");
      request.setAttribute("user",form);
      return "f:/jsps/user/regist.jsp";
    }
//    String session_vcode = (String)request.getSession().getAttribute("session_vcode");
//    if (!session_vcode.equalsIgnoreCase(form.getVerifyCode())){
//      request.setAttribute("msg","验证码错误！");
//      request.setAttribute("form",form);
//      return "f:/jsps/user/regist.jsp";
//    }
    try {
      userService.regist(form);
    } catch (UserException e) {
      request.setAttribute("msg",e.getMessage());
      request.setAttribute("form",form);
      return "f:/jsps/user/regist.jsp";
    }
//    try {
//      userService.regist(form);
//    }catch (UserException e) {
//      request.setAttribute("msg",e.getMessage());
//      request.setAttribute("form",form);
//      return "f:/jsps/user/regist.jsp";
//    }

    request.setAttribute("msg","恭喜，注册成功!");
    return "f:/jsps/msg.jsp";
  }
  public String login(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    User form = CommonUtils.toBean(request.getParameterMap(), User.class);
    Map<String, String> error = new HashMap<>();
    if (form.getUsername()==null||form.getUsername().trim().isEmpty()){
      error.put("username","用户名不能为空！");
    }else if (form.getUsername().length()<3||form.getUsername().length()>10){
      error.put("username","用户名长度需要在3-10");
    }

    if (form.getPassword()==null||form.getPassword().trim().isEmpty()){
      error.put("password","密码不能为空！");
    }else if (form.getPassword().length()<3||form.getPassword().length()>10){
      error.put("password","密码长度需要在3-10");
    }
    if (error.size()>0){
      request.setAttribute("error",error);
      request.setAttribute("form",form);
      return "f:/jsps/user/login.jsp";
    }
    try {
      User user = userService.login(form);
      request.getSession().setAttribute("session_user",user);
      request.getSession().setAttribute("cart",new Cart());
      return "r:/index.jsp";
    } catch (UserException e) {
      request.setAttribute("msg",e.getMessage());
      request.setAttribute("form",form);
      return "f:/jsps/user/login.jsp";
    }
  }
  public String quit(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      request.getSession().invalidate();
      return "r:/index.jsp";
  }
}
