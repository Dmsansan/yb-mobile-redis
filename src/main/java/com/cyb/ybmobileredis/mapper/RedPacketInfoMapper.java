package com.cyb.ybmobileredis.mapper;

import com.cyb.ybmobileredis.domain.RedPacketInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RedPacketInfoMapper {
    List<RedPacketInfo> ListRedPacketInfo();
    void insert(RedPacketInfo redPacketInfo);

    void updatePacketInfo(@Param(value = "amount") int amount, @Param(value = "redPacketId") Long redPacketId);

    RedPacketInfo getPacketInfoByPacketId(@Param(value = "redPacketId") long redPacketId);
}
