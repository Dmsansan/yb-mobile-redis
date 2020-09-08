package com.cyb.ybmobileredis.service.impl;

import com.cyb.ybmobileredis.domain.RedPacketRecord;
import com.cyb.ybmobileredis.mapper.RedPacketRecordMapper;
import com.cyb.ybmobileredis.service.RedPacketRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName：RedPacketRecordServiceImpl
 * @Description：TODO
 * @Author：chenyb
 * @Date：2020/8/25 9:56 下午
 * @Versiion：1.0
 */
@Service
public class RedPacketRecordServiceImpl implements RedPacketRecordService {
    @Autowired
    private RedPacketRecordMapper redPacketRecordMapper;
    @Override
    public void insert(RedPacketRecord redPacketRecord) {
        redPacketRecordMapper.insert(redPacketRecord);
    }
}
