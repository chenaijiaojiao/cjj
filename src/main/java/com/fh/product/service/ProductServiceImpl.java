package com.fh.product.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.product.common.ServerResponse;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{
    @Resource
    private ProductMapper productMapper;

    @Override
    public ServerResponse queryIsHotProductList() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper();
        //查询热销产品为1的产品   1：热销 0；不热销
        queryWrapper.eq("isHot",1);
        List<Product> list = productMapper.selectList(queryWrapper);
        return ServerResponse.success(list);
    }

    @Override
    public ServerResponse queryProductList() {
        List<Product> list = productMapper.selectList(null);
        return ServerResponse.success(list);
    }

    @Override
    public ServerResponse queryListPage(long CurrentPage, long pageSize) {
        //初始值 = 当前页数-1*每页条数
        long start = (CurrentPage-1) * pageSize;
        //总条数
        Long totalCount = productMapper.queryTotalCount();

        List<Product> list = productMapper.queryList(start,pageSize);

        //页数
        long totalPage = totalCount%pageSize ==0?totalCount/pageSize:totalCount/pageSize+1;

        Map map = new HashMap();
        map.put("list",list);
        map.put("totalPage",totalPage);

        return ServerResponse.success(map);
    }


    @Override
    public Product selectProductById(Integer productId) {
        Product product = productMapper.selectById(productId);
        return product;
    }

    @Override
    public Long updateStock(int id, long count) {
        return productMapper.updateStock(id,count);
    }
}
