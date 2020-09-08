package com.cyb.ybmobileredis.schedule;

import com.cyb.ybmobileredis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @ClassName：LockNxExJob
 * @Description：分布式获取锁和释放锁
 * @Author：chenyb
 * @Date：2020/8/16 5:44 下午
 * @Versiion：1.0
 */
@Service
public class LockNxExJob {
    private static final Logger logger = LoggerFactory.getLogger(LockNxExJob.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    private static String LOCK_PREFIX = "prefix_";
    private DefaultRedisScript<Boolean> lockScript;
//一般分布式锁
//    @Scheduled(fixedRate = 8000)
//    public void lockJob() {
//        String lock = LOCK_PREFIX + "LockNxExJob";
//        boolean nxRet = false;
//        try {
//            //redistemplate setnx操作
//            nxRet = redisTemplate.opsForValue().setIfAbsent(lock, getHostIp());
//            Object lockValue = redisService.get(lock);
//            System.out.println(lockValue);
//            //获取锁失败
//            if (!nxRet) {
//                String value = (String) redisService.get(lock);
//                //打印当前占用锁的服务器IP
//                logger.info(System.currentTimeMillis() + " get lock fail,lock belong to:{}", value);
//                return;
//            } else {
//                redisTemplate.opsForValue().set(lock, getHostIp(), 3600000);
//
//                //获取锁成功
//                logger.info(System.currentTimeMillis() + " start lock lockNxExJob success");
//                Thread.sleep(4000);
//            }
//        } catch (Exception e) {
//            logger.error("lock error", e);
//
//        } finally {
//            if (nxRet) {
//                System.out.println("释放锁成功");
//                redisService.remove(lock);
//            }
//        }
//    }

    /**
     * lua脚本方式分布式锁
     */
//    @Scheduled(fixedRate = 8000)
//    public void luaLockJob() {
//        String lock = LOCK_PREFIX + "LockNxExJob";
//        boolean nxRet = false;
//        try {
//            //redistemplate setnx操作
//            //nxRet = luaExpress(lock,getHostIp());
//            nxRet = setLock(lock,600);
//            Object lockValue = redisService.get(lock);
//            System.out.println(lockValue);
//            //获取锁失败
//            if (!nxRet) {
//                String value = (String) redisService.get(lock);
//                //打印当前占用锁的服务器IP
//                logger.info(System.currentTimeMillis() + " lua get lock fail,lock belong to:{}", value);
//                return;
//            } else {
//                redisTemplate.opsForValue().set(lock, getHostIp(), 3600000);
//
//                //获取锁成功
//                logger.info(System.currentTimeMillis() + " lua start lock lockNxExJob success");
//                Thread.sleep(4000);
//            }
//        } catch (Exception e) {
//            logger.error("lua lock error", e);
//
//        } finally {
//            if (nxRet) {
//                System.out.println("lua 释放锁成功");
//                redisService.remove(lock);
//            }
//        }
//    }

    /**
     * setnx和setex连用分布式锁
     */
    //@Scheduled(fixedRate = 8000)
    public void setLockJob() {
        String lock = LOCK_PREFIX + "LockNxExJob";
        boolean nxRet = false;
        try {
            //redistemplate setnx操作
            //nxRet = luaExpress(lock,getHostIp());
            System.out.println("hostIp1="+getHostIp());
            nxRet = setLock(lock, getHostIp(), 8);
            Object lockValue = redisService.get(lock);
            System.out.println(lockValue);
            //获取锁失败
            if (!nxRet) {
                String value = (String) redisService.get(lock);
                //打印当前占用锁的服务器IP
                logger.info(System.currentTimeMillis() + " setnx and setex get lock fail,lock belong to:{}", value);
                return;
            } else {
                //获取锁成功
                logger.info(System.currentTimeMillis() + " setnx and setex start lock lockNxExJob success");
                Thread.sleep(4000);
            }
        } catch (Exception e) {
            logger.error(" setnx and setex lock error", e);

        } finally {
            if (nxRet) {
                System.out.println(" setnx and setex 释放锁成功");
                //redisService.remove(lock);
                //使用lua脚本释放锁
                System.out.println("hostIp2="+getHostIp());
                Boolean result = releaseLock(lock, getHostIp());
                System.out.println("状态:"+result);
            }
        }
    }

    /**
     * 释放锁操作
     *
     * @param key   键
     * @param value 值
     * @return
     */
    private boolean releaseLock(String key, String value) {
        lockScript = new DefaultRedisScript<Boolean>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        lockScript.setResultType(Boolean.class);
        //封装参数
        List<Object> keyList = new ArrayList<>();
        keyList.add(key);
        keyList.add(value);
        Boolean result = (Boolean) redisTemplate.execute(lockScript, keyList);
        return result;
    }

    /**
     * setnx和setex连用
     *
     * @param key    键
     * @param value  值
     * @param expire 超时时间
     * @return
     */
    public boolean setLock(String key, String value, long expire) {
        try {
            Boolean result = (boolean) redisTemplate.execute(new RedisCallback<Boolean>() {

                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.set(key.getBytes(), value.getBytes(), Expiration.seconds(expire), RedisStringCommands.SetOption.ifAbsent());
                }
            });
            return result;
        } catch (Exception e) {
            logger.error("set redis occured an exception", e);
        }
        return false;
    }

    /**
     * 获取lua结果
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public Boolean luaExpress(String key, String value) {
        lockScript = new DefaultRedisScript<>();
        lockScript.setScriptSource(
                new ResourceScriptSource(new ClassPathResource("add.lua"))
        );
        //设置返回值
        lockScript.setResultType(Boolean.class);
        //封装参数
        List<Object> keyList = new ArrayList<>();
        keyList.add(key);
        keyList.add(value);
        Boolean result = (Boolean) redisTemplate.execute(lockScript, keyList);
        return result;
    }

    /**
     * 获取本机内网IP地址方法
     *
     * @return
     */
    private static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":") == -1) {
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
