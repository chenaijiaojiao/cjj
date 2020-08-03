package com.fh.cart.service;

import com.alibaba.fastjson.JSONObject;
import com.fh.cart.model.Cart;
import com.fh.member.common.ServerEnum;
import com.fh.member.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.member.util.RedisUtil;
import com.fh.member.util.SystemConstant;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;



@Service
public class CartServiceImpl implements  CartService{
    @Resource
    ProductService productService;

    @Override
    public ServerResponse buy(Integer productId, Integer count, HttpServletRequest request) {
        //验证商品是否存在
        Product product =productService.selectProductById(productId);
        if (product == null){
            return ServerResponse.error(ServerEnum.PRODUCT_NOT_EXIST );
        }
        //验证商品是否上架
        if (product.getStatus()==0){
            return ServerResponse.error(ServerEnum.PRODUCT_IS_DOWN );
        }
        //验证购物车中是否存在该商品
        Member member = (Member) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
        boolean exist = RedisUtil.exist(SystemConstant.CART_KEY+member.getId(), productId.toString());
        if (!exist){
            Cart cart = new Cart();
            cart.setProductId(productId);
            cart.setCount(count);
            cart.setFilePath(product.getFilePath());
            cart.setPrice(product.getPrice());
            cart.setName(product.getName());
            String jsonString = JSONObject.toJSONString(cart);
            RedisUtil.hset(SystemConstant.CART_KEY+member.getId(),productId.toString(),jsonString);
        }else{
            //如果存在 则修改数量
            String productJson = RedisUtil.hget(SystemConstant.CART_KEY+member.getId(),productId.toString());
            Cart cart = JSONObject.parseObject(productJson,Cart.class);
            cart.setCount(cart.getCount()+count);
            //修改完之后 在存入redis中
            String jsonString = JSONObject.toJSONString(cart);
            RedisUtil.hset(SystemConstant.CART_KEY+member.getId(),productId.toString(),jsonString);
        }
        return ServerResponse.success();
    }
}
