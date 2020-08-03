package com.fh.member.controller;

import com.fh.member.common.Ignore;
import com.fh.member.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.member.service.MemberService;
import com.fh.member.util.RedisUtil;
import com.fh.member.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("checkMemberName")
    @Ignore
    public ServerResponse checkMemberName(String name){
        return memberService.checkMemberName(name);
    }

    @RequestMapping("checkMemberPhone")
    @Ignore
    public ServerResponse checkMembcheckMemberPhoneerName(String phone){
        return memberService.checkMemberPhone(phone);
    }

    @RequestMapping("redister")
    @Ignore
    public ServerResponse redister(Member member){
        return memberService.redister(member);
    }

    @RequestMapping("login")
    @Ignore
    public ServerResponse login(Member member){

        return memberService.login(member);
    }

    @RequestMapping("checkLogin")

    public ServerResponse checkLogin(HttpServletRequest request){
        Member member = (Member) request.getSession().getAttribute(SystemConstant.SESSION_KEY);
        if (member == null){
            return  ServerResponse.error();
        }
        return  ServerResponse.success();
    }

    @RequestMapping("out")
    @Ignore
    public ServerResponse out(HttpServletRequest request){
        //让token失效
        String token= (String) request.getSession().getAttribute(SystemConstant.TOKEN_KEY);
        RedisUtil.del(SystemConstant.TOKEN_KEY+token);
        return  ServerResponse.success();
    }


}
