package com.app.blog.service;

import com.app.blog.entity.Blog;
import com.app.blog.entity.Category;
import com.app.blog.util.PageQueryUtil;
import com.app.blog.util.PageResult;

import java.util.List;

/**
 * @author Lete乐特
 * @createDate 2020- 11-13 11:28
 */
public interface CategoryService {

    List<Category> selCategory();

    Category selectByCategoryName(String name);

    List<Blog> selectByBlogCategoryName(String name);

    /*查询全部信息 and 分页*/
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    /*批量删除*/
    Boolean deleteBatch(Integer[] ids);

    /*修改*/
    Boolean updateCategory(Integer categoryId, String categoryName);

    /*添加*/
    Boolean saveCategory(String categoryName);

    /*查询记录数*/
    int getTotalCategories();


}
