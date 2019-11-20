package category.dao;

import category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {
    private QueryRunner qr=new TxQueryRunner();
//    public List<Category> findAll(){
//        String sql="select * from category";
//        try {
//            List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
//            return categoryList;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public List<Category> findAll(){
        String sql="select * from category";
        try {
            return qr.query(sql,new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Category category) {
        String sql="insert into category values(?,?)";
        try {
            qr.update(sql,category.getCid(),category.getCname());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String cid) {
        String sql="delete from category where cid=?";
        try {
            qr.update(sql,cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category findBycid(String cid) {
        String sql="select * from category where cid=?";
        try {
            return qr.query(sql,new BeanHandler<>(Category.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Category category) {
        String sql="update category set cname=? where cid=?";
        try {
            qr.update(sql,category.getCname(),category.getCid());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
