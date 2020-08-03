package com.fh.order.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TableName("t_order")
public class Order {
    private  Integer id;
    @TableField("merderId")
    private  Integer merderId;
    @TableField("purdctId")
    private Integer purdctId;
    @TableField("filePath")
    private  String filePath;
    private long count;
    private BigDecimal price;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private List<OrderInfo> orderInfoList=new ArrayList<>();
    public Order() {
    }


    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerderId() {
        return merderId;
    }

    public void setMerderId(Integer merderId) {
        this.merderId = merderId;
    }

    public Integer getPurdctId() {
        return purdctId;
    }

    public void setPurdctId(Integer purdctId) {
        this.purdctId = purdctId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPayType(Integer payType) {
    }

    public void setAddressId(Integer addressId) {
    }
}
