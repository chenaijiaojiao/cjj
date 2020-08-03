package com.fh.Settle.controller;

import com.fh.Settle.model.Settle;
import com.fh.Settle.service.SettleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fh.member.common.ServerResponse;


@RestController
@RequestMapping("settle")
public class SettleController {

    @Autowired
    private SettleService settleService;

    @RequestMapping("addSettle")
    public ServerResponse addSettle(Settle settle){
        return settleService.addSettle(settle);
    }

    @RequestMapping("queryList")
    public ServerResponse queryList(){
        return settleService.queryList();
    }

    @RequestMapping("queryStatusList")
    public ServerResponse queryStatusList(){
        return settleService.queryStatusList();
    }

    @RequestMapping("deleteSettle/{id}")
    public ServerResponse deleteSettle(@PathVariable("id") Integer id){
        return settleService.deleteSettle(id);
    }

    @RequestMapping("updateStatus/{id}")
    public ServerResponse updateStatus(@PathVariable("id") Integer id){
        return settleService.updateStatus(id);
    }

    @RequestMapping("updSettle")
    public ServerResponse updSettle(Settle settle){
        return settleService.updSettle(settle);
    }

}
