package com.cyb.ybmobileredis.Request;

/**
 * @ClassName：GetPacketReq
 * @Description：TODO
 * @Author：chenyb
 * @Date：2020/8/25 10:07 下午
 * @Versiion：1.0
 */
public class GetPacketReq {
    private Integer uid;
    private long redPacketId;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(long redPacketId) {
        this.redPacketId = redPacketId;
    }

    @Override
    public String toString() {
        return "GetPacketReq{" +
                "uid=" + uid +
                ", redPacketId=" + redPacketId +
                '}';
    }
}
