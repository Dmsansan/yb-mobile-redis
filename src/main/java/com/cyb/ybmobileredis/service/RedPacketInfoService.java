package com.cyb.ybmobileredis.service;

import com.cyb.ybmobileredis.domain.RedPacketInfo;

public interface RedPacketInfoService {
    void insert(RedPacketInfo redPacketInfo);

    void updatePacketInfo(int amount, Long redPacketId);

    RedPacketInfo getPacketInfoByPacketId(long redPacketId);
}
