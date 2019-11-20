package category.service;

import book.dao.BookDao;
import category.dao.CategoryDao;
import category.domain.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao=new CategoryDao();
    private BookDao bookDao=new BookDao();
    public List<Category> findAll(){
        return categoryDao.findAll();
    }

    public void add(Category category) {
         categoryDao.add(category);
    }

    public void delete(String cid) throws CategoryException {
        int count=bookDao.getCountByCid(cid);
        if(count>0){
            throw new CategoryException("该分类下有图书，不能删除！");
        }
        categoryDao.delete(cid);
    }

    public Category load(String cid) {
        return categoryDao.findBycid(cid);
    }

    public void edit(Category category) {
        categoryDao.update(category);
    }
}
