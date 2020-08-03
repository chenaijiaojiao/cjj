package com.fh.member.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.member.common.ServerResponse;
import com.fh.member.mapper.MemberMapper;
import com.fh.member.model.Member;
import com.fh.member.util.JwtUtil;
import com.fh.member.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class MemberServiceImpl implements  MemberService{
  @Resource
    private MemberMapper memberMapper;

  @Override
  public ServerResponse checkMemberName(String name) {
    QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("name",name);
    Member member = memberMapper.selectOne(queryWrapper);
    if (member == null){
      return ServerResponse.success();
    }
    return ServerResponse.error("用户已存在");
  }

  @Override
  public ServerResponse checkMemberPhone(String phone) {
    QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("phone",phone);
    Member member = memberMapper.selectOne(queryWrapper);
    if (member == null){
      return ServerResponse.success();
    }
    return ServerResponse.error("手机号已存在");
  }

  @Override
  public ServerResponse redister(Member member) {
   String redisCode = RedisUtil.get(member.getPhone());
      if (redisCode == null ){
          return  ServerResponse.error("验证码已失效");
      }

    if (!redisCode.equals(member.getCode())){
      return  ServerResponse.error("验证码错误");
    }
    memberMapper.insert(member);
    return ServerResponse.success();
  }

    @Override
    public ServerResponse login(Member member) {
      //手机号和用户名 都可以登录
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",member.getName());
        queryWrapper.or();
        queryWrapper.eq("phone",member.getName());
        //queryMember
        Member memberDB = memberMapper.selectOne(queryWrapper);
            if ( null == memberDB){
                return  ServerResponse.error("用户名不存在！");
            }

        if (!memberDB.getPassword().equals(member.getPassword())){
            return  ServerResponse.error("密码错误！");
        }
            String token = "";
        //账号密码正确  生成Token 返回前台
        try {
            String jsonstring = JSONObject.toJSONString(memberDB);
            String encodeJson = URLEncoder.encode(jsonstring, "utf-8");
            token = JwtUtil.sign(encodeJson);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ServerResponse.success(token);
    }
}
