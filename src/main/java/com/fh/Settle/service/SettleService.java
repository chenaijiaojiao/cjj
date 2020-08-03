package com.fh.Settle.service;

import com.fh.Settle.model.Settle;
import com.fh.member.common.ServerResponse;



public interface SettleService {
    ServerResponse addSettle(Settle settle);

    ServerResponse queryList();

    ServerResponse deleteSettle(Integer id);

    ServerResponse queryStatusList();

    ServerResponse updateStatus(Integer id);

    ServerResponse updSettle(Settle settle);
}
