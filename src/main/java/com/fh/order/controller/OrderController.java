package com.fh.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fh.cart.model.Cart;
import com.fh.member.common.Idempotent;
import com.fh.member.common.MemberAnnotation;
import com.fh.member.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
    @RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("buildOrder")
    @Idempotent
    public ServerResponse buildOrder(String listStr, Integer payType, Integer addressId,@MemberAnnotation Member memder){
        List<Cart> cartList=new ArrayList<>();
        if(StringUtils.isNotEmpty(listStr)){
            cartList= JSONObject.parseArray(listStr,Cart.class);
        }else {
            return ServerResponse.error("请选择商品");
        }

        return  orderService.buildOrder(cartList,payType,addressId,memder);
    }
}
