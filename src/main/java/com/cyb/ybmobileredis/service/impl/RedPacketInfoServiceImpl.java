package com.cyb.ybmobileredis.service.impl;

import com.cyb.ybmobileredis.domain.RedPacketInfo;
import com.cyb.ybmobileredis.mapper.RedPacketInfoMapper;
import com.cyb.ybmobileredis.service.RedPacketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName：RedPacketInfoServiceImpl
 * @Description：红包信息
 * @Author：chenyb
 * @Date：2020/8/24 10:36 下午
 * @Versiion：1.0
 */
@Service
public class RedPacketInfoServiceImpl implements RedPacketInfoService {
    @Autowired
    private RedPacketInfoMapper redPacketInfoMapper;

    /**
     * 添加红包
     * @param redPacketInfo
     */
    @Override
    public void insert(RedPacketInfo redPacketInfo) {
        redPacketInfoMapper.insert(redPacketInfo);
    }

    /**
     * 更新红包数量以及金额
     * @param amount
     * @param redPacketId
     */
    @Override
    public void updatePacketInfo(int amount, Long redPacketId) {
        redPacketInfoMapper.updatePacketInfo(amount, redPacketId);
    }

    /**
     * 通过红包ID获取红包信息
     * @param redPacketId
     * @return
     */
    @Override
    public RedPacketInfo getPacketInfoByPacketId(long redPacketId) {
        return redPacketInfoMapper.getPacketInfoByPacketId(redPacketId);
    }
}
