package com.fh.product.service;

import com.fh.product.common.ServerResponse;
import com.fh.product.model.Product;

public interface ProductService {

    ServerResponse queryIsHotProductList();

    ServerResponse queryProductList();

    ServerResponse queryListPage(long CurrentPage, long pageSize);

    Product selectProductById(Integer productId);

    Long updateStock(int id, long count);
}
