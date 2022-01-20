package com.mint.springblog.service.impl;

import com.mint.springblog.entity.Blog;
import com.mint.springblog.mapper.BlogMapper;
import com.mint.springblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mint
 * @since 2022-01-05
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
