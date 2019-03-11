package com.zc.wake.wake;

import android.content.Context;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zc.wake.R;
import com.zc.wake.wake.magic.MagicPacket;

public class Wake {

    /**
     * 设置IP
     * @param ip
     * @return
     */
    public static String setIPAddress(String ip){
        //如果没有填写Ip获取本机wifi链接的地址
        if(ip == null || ip.isEmpty()){
           if(!NetworkUtils.isWifiConnected()){
               ToastUtils.showLong("请先连接上WIFI");
               return null;
           }
            return  ConvertIP( NetworkUtils.getIpAddressByWifi());
        }
        return ip;
    }

    /**
     *转换Ip到255向全地址广播
     * @param IP
     * @return
     */
    public static  String ConvertIP(String IP){

        String [] string= IP.split("\\.");

        String NEW_IP =string[0]+"."+string[1]+"."+string[2]+"."+"255";

        return NEW_IP;
    }

    public static String sendPacket(Context context, String title, String mac, String ip, int port)
    {

        String formattedMac = null;
        ip = setIPAddress(ip);
        if(ip == null){
            return null;
        }
        try {
            formattedMac = MagicPacket.send(mac, ip, port);


        }catch(IllegalArgumentException iae) {
            showToast(context.getString(R.string.send_failed)+":\n"+iae.getMessage());
            return null;

        }catch(Exception e) {
            showToast(context.getString(R.string.send_failed));
            return null;
        }

        // display sent message to user
        showToast(context.getString(R.string.packet_sent));
        return formattedMac;
    }

    public static void showToast(String message)
    {
        ToastUtils.showLong(message);
    }

}
