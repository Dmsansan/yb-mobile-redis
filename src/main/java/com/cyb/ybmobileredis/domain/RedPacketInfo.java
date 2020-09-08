package com.cyb.ybmobileredis.domain;

import java.util.Date;

/**
 * @ClassName：RedPacketInfo
 * @Description：抢红包信息
 * @Author：chenyb
 * @Date：2020/8/24 10:21 下午
 * @Versiion：1.0
 */
public class RedPacketInfo {
    /**
     * 自增内码
     */
    private Integer id;
    /**
     * 红包id
     */
    private Long redPacketId;
    /**
     * 红包总个数
     */
    private Integer totalAmount;
    /**
     * 红包总个数
     */
    private Integer totalPacket;
    /**
     * 剩余红包个数
     */
    private Integer remainingAmount;
    /**
     * 新建红包用户的用户标识
     */
    private Integer remainingPacket;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalPacket() {
        return totalPacket;
    }

    public void setTotalPacket(Integer totalPacket) {
        this.totalPacket = totalPacket;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Integer getRemainingPacket() {
        return remainingPacket;
    }

    public void setRemainingPacket(Integer remainingPacket) {
        this.remainingPacket = remainingPacket;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RedPacketInfo{" +
                "id=" + id +
                ", redPacketId=" + redPacketId +
                ", totalAmount=" + totalAmount +
                ", totalPacket=" + totalPacket +
                ", remainingAmount=" + remainingAmount +
                ", remainingPacket=" + remainingPacket +
                ", uid=" + uid +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
