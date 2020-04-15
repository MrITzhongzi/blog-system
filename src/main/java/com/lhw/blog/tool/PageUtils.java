package com.lhw.blog.tool;

import com.github.pagehelper.PageInfo;
import com.lhw.blog.domain.LhwArticles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtils<T> {

    public Map<String, Object> dealPageInfo(List<T> list, int page){
        PageInfo<T> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<>();
        //总条数
        map.put("total_size",pageInfo.getTotal());
        //总页数
        map.put("total_page",pageInfo.getPages());
        //当前页
        map.put("current_page",page);
        //数据
        map.put("data",pageInfo.getList());
        return map;
    }
}
