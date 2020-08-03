package com.fh.order.service;

import com.fh.cart.model.Cart;
import com.fh.member.common.ServerResponse;
import com.fh.member.model.Member;

import java.util.List;

public interface OrderService {
    ServerResponse buildOrder(List<Cart> cartList, Integer payType, Integer addressId, Member member);
}
