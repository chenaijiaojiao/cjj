package com.fh.member.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fh.member.common.ServerResponse;
import com.fh.member.util.MessageVerifyUtils;
import com.fh.member.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sms")
public class sendMsg {
    @RequestMapping("sendMsg")
    public ServerResponse sendMsg(String phone){
        String code  = MessageVerifyUtils.getNewcode();
        try {
            SendSmsResponse sendSmsResponse= MessageVerifyUtils.sendSms(phone,code);
            if (sendSmsResponse != null && "OK".equals(sendSmsResponse.getCode())){
                    //把code 放到 redis
               RedisUtil.set(phone,code);
                return ServerResponse.success();
            }
        } catch (ClientException e) {
            e.printStackTrace();
            return ServerResponse.error(e.getErrMsg());
        }
        return  null;
    }
}
