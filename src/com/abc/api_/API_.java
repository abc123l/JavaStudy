package com.abc.api_;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author abc
 * @version 1.0
 * InetAddress 的方法的使用
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        //获取本机的InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);//abc123_immortal/192.168.74.1

        //根据指定的主机名，获取InetAddress对象
        InetAddress host1 = InetAddress.getByName("abc123_immortal");
        System.out.println("host1="+host1);//host1=abc123_immortal/192.168.74.1

        //根据域名返回InetAddress对象
        InetAddress host2 = InetAddress.getByName("www.pornhub.com");
        System.out.println("host2="+host2);//host2=www.pornhub.com/66.254.114.41

        //通过InetAddress对象，获取对应的地址
        String hostAddress = host2.getHostAddress();//IP
        System.out.println("host2对应的ip="+hostAddress);//host2=www.pornhub.com/66.254.114.41

        //通过InetAddress对象，获取对应的主机名/域名
        String hostName = host2.getHostName();
        System.out.println("host2对应的主机名/域名="+hostName);//host2对应的主机名/域名=www.pornhub.com
    }
}
