package com.cyb.ybmobileredis.Request;

/**
 * @ClassName：SaveRedPacketReq
 * @Description：TODO
 * @Author：chenyb
 * @Date：2020/8/25 9:31 下午
 * @Versiion：1.0
 */
public class SaveRedPacketReq {
    private Integer uid;
    private Integer totalNum;
    private Integer totalAmount;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "SaveRedPacketReq{" +
                "uid=" + uid +
                ", totalNum=" + totalNum +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
