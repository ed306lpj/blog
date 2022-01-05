package com.app.blog.service;

import com.app.blog.entity.Blog;
import com.app.blog.entity.Tag;
import com.app.blog.util.PageQueryUtil;
import com.app.blog.util.PageResult;

import java.util.List;

/**
 * @author Lete乐特
 * @createDate 2020- 11-14 18:29
 */
public interface TagService {

    PageResult getBlogTagPage(PageQueryUtil pageUtil);

    Boolean saveTag(String tagName);

    Boolean deleteBatch(Integer[] ids);

    List<Tag> selTag();

    /*根据标签名称查询博客*/
    List<Blog> likeByTagName(String tagName);

    /*根据标签名查询*/
    Tag selectByTagName(String tagName);

    /*查询记录数*/
    int getTotalTags();
}
