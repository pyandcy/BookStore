package user.dao;

import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import user.domain.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    private QueryRunner qr=new TxQueryRunner();
//    public User findByName(String username){
//        try {
//            String sql="select * from tb_user where username=?";
//            return qr.query(sql,new BeanHandler<User>(User.class),username);
//        }catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }

//    public void add(User user){
//        String sql="insert into tb_user values(?,?,?,?)";
//        Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getCode()};
//        try {
//            qr.update(sql,params);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public User findByName(String username) {
        String sql="select * from tb_user where username=?";
        try {
            return qr.query(sql,new BeanHandler<>(User.class),username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(User form) {
        String sql="insert into tb_user values(?,?,?,?)";
        Object[] params={form.getUid(),form.getUsername(),form.getPassword(),form.getCode()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
