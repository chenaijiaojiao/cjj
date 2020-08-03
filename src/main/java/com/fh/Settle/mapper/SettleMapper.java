package com.fh.Settle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.Settle.model.Settle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface SettleMapper extends BaseMapper<Settle> {
    void updateStatus(Integer id);

    void selectUpdateById(Integer id);
}
