package com.cyb.ybmobileredis.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @ClassName：ClusterLockV2Job
 * @Description：TODO
 * @Author：chenyb
 * @Date：2020/8/14 10:25 下午
 * @Versiion：1.0
 */
@Service
public class ClusterLockV2Job {
    //秒分时日月年
    //每隔5秒：0/5 * * * * *
    //每隔5分钟:* 0/5 * * * *
   // @Scheduled(cron = "0/5 * * * * *")
    public void lock(){
        System.out.println(getHostIp());
        System.out.println("enter job");
    }
    /**
     * 获取本机内网IP地址方法
     * @return
     */
    private static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
