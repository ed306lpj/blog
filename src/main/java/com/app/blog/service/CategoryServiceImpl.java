package com.app.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.app.blog.entity.Blog;
import com.app.blog.entity.Category;
import com.app.blog.mapper.BlogMapper;
import com.app.blog.mapper.CategoryMapper;
import com.app.blog.util.PageQueryUtil;
import com.app.blog.util.PageResult;

import java.util.Date;
import java.util.List;

/**
 * @author Lete乐特
 * @createDate 2020- 11-13 11:28
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required=false)
    CategoryMapper mapper;
    @Autowired(required=false)
    BlogMapper blogMapper;


    @Override
    public List<Category> selCategory() {
        return mapper.selCategory();
    }

    @Override
    public List<Blog> selectByBlogCategoryName(String name) {
        return mapper.selectByBlogCategoryName(name);
    }


    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<Category> categoryList = mapper.findCategoryList(pageUtil);
        int total = mapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return mapper.getTotalCategories();
    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        blogMapper.updateBlogCategorys("默认分类", 0, ids);
        //删除分类数据
        return mapper.deleteBatch(ids) > 0;
    }

    @Override
    public Boolean updateCategory(Integer categoryId, String categoryName) {
        Category category = mapper.CategoryById(categoryId);
        if (category != null) {
            category.setCategoryName(categoryName);
            category.setCreateTime(new Date());
            //修改分类实体
            blogMapper.updateBlogCategorys(categoryName, categoryId, new Integer[]{categoryId});
            return mapper.modifyCategorys(category) > 0;
        }
        return false;
    }

    @Override
    public Category selectByCategoryName(String name) {
        return mapper.selectByCategoryName(name);
    }

//    保存分类
    @Override
    public Boolean saveCategory(String categoryName) {
        Category temp = mapper.selectByCategoryName(categoryName);
        if (temp==null) {
        	 Category category = new Category();
             category.setCategoryName(categoryName);
             category.setCreateTime(new Date());
             return mapper.insertCategory(category) > 0;
		}
        if (temp.getCategoryId() == 0) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setCreateTime(new Date());
            return mapper.insertCategory(category) > 0;
        }
        return false;
    }
}
