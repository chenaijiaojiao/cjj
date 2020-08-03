package com.fh.member.service;

import com.fh.member.common.ServerResponse;
import com.fh.member.model.Member;

import java.io.UnsupportedEncodingException;

public interface MemberService {
    ServerResponse checkMemberName(String name);

    ServerResponse checkMemberPhone(String phone);

    ServerResponse redister(Member member);

    ServerResponse login(Member member);
}
