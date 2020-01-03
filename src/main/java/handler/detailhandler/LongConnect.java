package handler.detailhandler;

import constant.ProtoConst;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;


public class LongConnect {


    public LongConnect(String[] str, HashMap hashMap, ChannelHandlerContext channelHandlerContext) {
        String uuid2 = str[1];
        //uuid 为key  ctx.channel 为value
        hashMap.put(uuid2, channelHandlerContext.channel().id());

        channelHandlerContext.channel().writeAndFlush(ProtoConst.LONG_CONNECT_BACK + "\r\n");

        System.out.println("新连接建立，hashmap。put：key是" + str[1] + "  value是：" + channelHandlerContext.channel());
        System.out.println("hashmap:" + hashMap.get(str[1]));
        System.out.println("hashmap当前连接个数为：" + hashMap.size());
    }

}
