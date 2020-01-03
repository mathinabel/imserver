package handler;

import bean.AddFriend;
import bean.User;
import constant.ProtoConst;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.internal.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    private final ChannelGroup channelGroup;
   public static HashMap hashMap = new HashMap<String, ChannelId>();

    public ChatServerHandler(ChannelGroup channels) {
        this.channelGroup = channels;

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

        Channel incoming = ctx.channel();
        for (Channel channel : channelGroup) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");

        }
        System.out.println("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        channelGroup.add(ctx.channel());
        System.out.println("channelgroup当前连接个数为："+channelGroup.size());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        channelGroup.remove(ctx.channel());

        hashMap.remove( getKey(hashMap,ctx.channel().id()));

        System.out.println(ctx.channel().id()+":离线了");
        System.out.println("hashmap当前连接个数为："+hashMap.size());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        if (StringUtil.isNullOrEmpty(s)) {
            return;
        }
        System.out.println(s);
        ProtocolProcessor pp = new ProtocolProcessor(channelHandlerContext, s, channelGroup,hashMap);
        pp.startProcess();




    }
    //根据value值获取到对应的一个key值
    public static String getKey(HashMap<String,ChannelId> map, ChannelId value){
        String key = null;
        //Map,HashMap并没有实现Iteratable接口.不能用于增强for循环.
        for(String getKey: map.keySet()){
            if(map.get(getKey).equals(value)){
                key = getKey;
            }
        }
        return key;
        //这个key肯定是最后一个满足该条件的key.
    }

}

