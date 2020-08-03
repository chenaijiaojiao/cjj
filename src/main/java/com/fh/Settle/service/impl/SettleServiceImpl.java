package com.fh.Settle.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.Settle.mapper.SettleMapper;
import com.fh.Settle.model.Settle;
import com.fh.Settle.service.SettleService;
import com.fh.member.common.ServerResponse;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SettleServiceImpl implements SettleService {

    @Resource
    private SettleMapper settleMapper;

    @Override
    public ServerResponse addSettle(Settle settle) {
        settle.setStatus(2);
        settleMapper.insert(settle);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse queryList() {
        QueryWrapper<Settle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Status",2);
        List<Settle> settleList = settleMapper.selectList(queryWrapper);
        return ServerResponse.success(settleList);
    }

    @Override
    public ServerResponse deleteSettle(Integer id) {
        settleMapper.deleteById(id);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse queryStatusList() {
        QueryWrapper<Settle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Status",1);
        List<Settle> selectList = settleMapper.selectList(queryWrapper);
        return ServerResponse.success(selectList);
    }

    @Override
    public ServerResponse updateStatus(Integer id) {
        QueryWrapper<Settle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Status",1);
        List<Settle> selectList = settleMapper.selectList(queryWrapper);
        for (Settle settle : selectList) {
            settleMapper.updateStatus(settle.getId());
        }

        settleMapper.selectUpdateById(id);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse updSettle(Settle settle) {
        settleMapper.updateById(settle);
        return ServerResponse.success();
    }
}
