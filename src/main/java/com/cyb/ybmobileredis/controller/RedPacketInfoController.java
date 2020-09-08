package com.cyb.ybmobileredis.controller;

import com.cyb.ybmobileredis.Request.GetPacketReq;
import com.cyb.ybmobileredis.Request.SaveRedPacketReq;
import com.cyb.ybmobileredis.domain.RedPacketInfo;
import com.cyb.ybmobileredis.domain.RedPacketRecord;
import com.cyb.ybmobileredis.service.RedPacketInfoService;
import com.cyb.ybmobileredis.service.RedPacketRecordService;
import com.cyb.ybmobileredis.service.RedisService;
import com.cyb.ybmobileredis.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

/**
 * @ClassName：RedPacketInfoController
 * @Description：抢红包
 * @Author：chenyb
 * @Date：2020/8/24 10:39 下午
 * @Versiion：1.0
 */
@RestController
@RequestMapping("red_packet_info")
public class RedPacketInfoController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedPacketInfoService redPacketInfoService;
    @Autowired
    private RedPacketRecordService redPacketRecordService;
    private static final String TOTAL_NUM = "_totalNum";
    private static final String TOTAL_AMOUNT = "_totalAmount";

    /**
     * 发红包
     *
     * @param req 用户信息
     * @return
     */
    @RequestMapping(value = "/addPacket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonData saveRedPacket(@RequestBody SaveRedPacketReq req) {
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.setUid(req.getUid());
        redPacketInfo.setTotalAmount(req.getTotalAmount());
        redPacketInfo.setTotalPacket(req.getTotalNum());
        redPacketInfo.setRemainingAmount(req.getTotalAmount());
        redPacketInfo.setRemainingPacket(req.getTotalNum());
        redPacketInfo.setCreateTime(new Date());
        redPacketInfo.setUpdateTime(new Date());
        //分布式情况下，最好使用雪花算法生成
        Long redPacketId = System.currentTimeMillis();
        redPacketInfo.setRedPacketId(redPacketId);
        redPacketInfoService.insert(redPacketInfo);
        //往redis插入2条记录
        //红包个数
        redisService.set(redPacketId + TOTAL_NUM, req.getTotalNum() + "");
        //红包金额
        redisService.set(redPacketId + TOTAL_AMOUNT, req.getTotalAmount() + "");
        return JsonData.buildSuccess();
    }

    /**
     * 抢红包
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "getPacket", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonData getRedPacket(@RequestBody GetPacketReq req) {
        Integer randomAmount=0;
        String redPacketName = req.getRedPacketId() + TOTAL_NUM;
        //红包总金额
        String totalAmountName = req.getRedPacketId() + TOTAL_AMOUNT;
        if (redisService.exists(redPacketName)) {
            Integer num = Integer.valueOf(redisService.get(redPacketName) + "");
            if (num != null && num > 0) {
                //红包数减一
                redisService.decr(redPacketName);

                //拿到红包总金额
                if (redisService.exists(totalAmountName)){
                    Integer totalAmount=Integer.valueOf(redisService.get(totalAmountName)+"");
                    //红包最大金额
                    Integer maxMoney=totalAmount/Integer.valueOf(num)*2;
                    Random random=new Random();
                    //随机抢到的红包
                    randomAmount =num==1?totalAmount:random.nextInt(maxMoney);
                    //redis红包金额减指定值
                    redisService.decr(totalAmountName,randomAmount);
                }
                updateRedPacketInDB(req.getUid(),req.getRedPacketId(),randomAmount);

                // 程序编程，保证redis DB事务一致性，数据一致
                RedPacketInfo packetInfo = redPacketInfoService.getPacketInfoByPacketId(req.getRedPacketId());
                if (packetInfo.getTotalAmount() != redisService.get(totalAmountName)
                        || packetInfo.getTotalPacket() != redisService.get(redPacketName)) {
                    redisService.set(totalAmountName, String.valueOf(packetInfo.getTotalAmount()));
                    redisService.set(redPacketName, String.valueOf(packetInfo.getTotalPacket()));
                }
                return JsonData.buildSuccess("抢到红包:"+randomAmount);
            } else {
                return JsonData.buildError("红包被抢完啦");
            }
        } else {
            return JsonData.buildError("红包不存在，请重试");
        }
    }

    /**
     * 更新用户抢到红包记录
     * @param uid 用户id
     * @param redPacketId 红包id
     * @param amount 金额
     */
    private void updateRedPacketInDB(int uid,Long redPacketId,int amount){
        RedPacketRecord redPacketRecord=new RedPacketRecord();
        redPacketRecord.setUid(uid);
        redPacketRecord.setNickName("陈彦斌");
        redPacketRecord.setImgUrl("https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg");
        redPacketRecord.setRedPacketId(redPacketId);
        redPacketRecord.setAmount(amount);
        redPacketRecord.setCreateTime(new Date());
        redPacketRecord.setUpdateTime(new Date());
        redPacketRecordService.insert(redPacketRecord);
        //这里还需要更新发红包的数据，红包总个数-1；总金额减去已抢红包数，这里不实现了，自行实现功能

        redPacketInfoService.updatePacketInfo(amount, redPacketId);
    }
}
