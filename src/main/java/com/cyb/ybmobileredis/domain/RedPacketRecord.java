package com.cyb.ybmobileredis.domain;

import java.util.Date;

/**
 * @ClassName：RedPacketRecord
 * @Description：抢红包记录
 * @Author：chenyb
 * @Date：2020/8/24 10:22 下午
 * @Versiion：1.0
 */
public class RedPacketRecord {
    /**
     * 自增内码
     */
    private Integer id;
    /**
     * 抢到红包的金额
     */
    private Integer amount;
    /**
     * 抢到红包的用户
     */
    private String nickName;
    /**
     * 抢到红包的用户的头像
     */
    private String imgUrl;
    /**
     * 抢到红包用户的用户标识
     */
    private Integer uid;
    /**
     * 红包id
     */
    private Long redPacketId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
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
        return "RedPacketRecord{" +
                "id=" + id +
                ", amount=" + amount +
                ", nickName='" + nickName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", uid=" + uid +
                ", redPacketId=" + redPacketId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
