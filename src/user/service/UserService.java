package user.service;
import user.dao.UserDao;
import user.domain.User;

public class UserService {
   private UserDao userdao= new UserDao();
//   public void regist(User form) throws UserException{
//      User userName = userdao.findByName(form.getUsername());
//      if (userName!=null){
//         throw new UserException("用户名已注册！");
//      }
//      userdao.add(form);
//   }
   public void regist(User form) throws UserException{
      User userName = userdao.findByName(form.getUsername());
      if(userName!=null){
         throw new UserException("用户名已注册！");
      }
      userdao.add(form);
   }
//   public User login(User form) throws UserException {
//      User user = userdao.findByName(form.getUsername());
//      if (user==null){
//         throw new UserException("用户名不存在！");
//      }
//      if (!user.getPassword().equals(form.getPassword())){
//         throw new UserException("密码错误！");
//      }
//      return user;
//   }
   public User login(User form) throws UserException{
      User user = userdao.findByName(form.getUsername());
      if (user==null){
         throw new UserException("用户名不存在！");
      }
      if (!user.getPassword().equals(form.getPassword())){
         throw new UserException("密码错误！");
      }
      return user;
   }
}

