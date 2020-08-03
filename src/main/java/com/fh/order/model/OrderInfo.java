package com.fh.order.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName("t_OrderInfo")
public class OrderInfo {
    private  long id;

    private  String name;

    private String orderId;

    private Integer prductId;

    private long count;

    private  String filePath;

    private BigDecimal price;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getPrductId() {
        return prductId;
    }

    public void setPrductId(Integer prductId) {
        this.prductId = prductId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
