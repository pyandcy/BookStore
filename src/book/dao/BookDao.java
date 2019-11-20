package book.dao;

import book.domain.Book;
import category.domain.Category;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookDao {
    private QueryRunner qr=new TxQueryRunner();
    public List<Book> findAll(){
        String sql="select * from book where del=false";
        try {
            return qr.query(sql,new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public List<Book> findByCategory(String cid) {
//        String sql="select * from book where cid=? and del=false";
//        try {
//            return qr.query(sql,new BeanListHandler<Book>(Book.class),cid);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public List<Book> findByCategory(String cid){
        String sql="select * from book where cid=? and del=false";
        try {
            return qr.query(sql,new BeanListHandler<Book>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book load(String bid) {
        String sql="select * from book where bid=?";
        try {
            Map<String, Object> map = qr.query(sql, new MapHandler(), bid);
            Category category = CommonUtils.toBean(map, Category.class);
            Book book = CommonUtils.toBean(map, Book.class);
            book.setCategory(category);
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCountByCid(String cid) {
        String sql="select count(*) from book where cid=?";
        try {
           Number count = (Number) qr.query(sql, new ScalarHandler(), cid);
           return count.intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Book book) {
        String sql="insert into book values(?,?,?,?,?,?)";
        Object[] params={book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(String bid) {
        String sql="update book set del=true where bid=?";
        try {
            qr.update(sql,bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void edit(Book book) {
            String sql="update book set bname=?,price=?,author=?,image=?,cid=? where bid=?";
            Object[] params={book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCategory().getCid(),book.getBid()};
            try {
                qr.update(sql,params);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
