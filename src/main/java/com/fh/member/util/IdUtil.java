package com.fh.member.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {

    public static String createId(){
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmm");
        String format = sim.format(new Date());
        return format+ IdWorker.getId();
    }

    //本地时间生成订单号
    public static void main(String[] args) {

        System.out.println(createId());
        //System.out.println(IdWorker.getId());
    }
}
