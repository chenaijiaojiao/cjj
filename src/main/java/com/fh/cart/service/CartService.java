package com.fh.cart.service;

import com.fh.member.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    ServerResponse buy(Integer productId, Integer count, HttpServletRequest request);
}
