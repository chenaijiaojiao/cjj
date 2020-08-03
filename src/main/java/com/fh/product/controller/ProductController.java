package com.fh.product.controller;

import com.fh.member.common.Ignore;
import com.fh.product.common.ServerResponse;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("queryIsHotProductList")
    @Ignore
    public ServerResponse queryIsHotProductList(){
        return productService.queryIsHotProductList();
    }

    @RequestMapping("queryProductList")
    @Ignore
    public ServerResponse queryProductList(){
        return productService.queryProductList();
    }

    @RequestMapping("queryListPage")
    @Ignore
    public ServerResponse queryListPage(long CurrentPage , long pageSize){
        return productService.queryListPage(CurrentPage,pageSize);
    }

}
