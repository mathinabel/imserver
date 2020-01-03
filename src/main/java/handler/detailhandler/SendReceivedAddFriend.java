package handler.detailhandler;

import constant.ProtoConst;
import constant.State;
import handler.send.SendOrStore;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;

import java.util.HashMap;

public class SendReceivedAddFriend {

    //用户接受/拒绝加好友请求  返回给服务器
    //todo 目前只做了接受的部分，后期还要添加字段做拒绝部分
    public SendReceivedAddFriend(String[] str, HashMap hashMap, ChannelGroup channelGroup, ChannelHandlerContext channelHandlerContext) {
//有返回服务器的数据就证明点击了接受
        System.out.println("uuid为" + str[2]);
        if (hashMap.containsKey(str[2])) {
            System.out.println("hashmap含有该uuid");
            //如果用户在线，channelgroup找到该id所对应的channel发过去
            ChannelId channelid = (ChannelId) hashMap.get(str[2]);
            String backmsg = ProtoConst.SEND_RECEIVED_ADD_FRIEND_BACK + "|" + str[1] + "\r\n";
            channelGroup.find(channelid).writeAndFlush(backmsg);
            System.out.println("addfriendsback回送id为" + str[2] + "内容为" + backmsg);
            //未读好友数据表状态改变
            SendOrStore sendOrStore = new SendOrStore(hashMap, str, channelGroup);
            sendOrStore.storeURAD(str[2], State.READ, State.SEND);
            //存储关系
            sendOrStore.storeUserRelationShip(str[1],str[2]);

        } else {
            //该用户不在线，进行数据库保存加好友请求
            SendOrStore sendOrStore = new SendOrStore(hashMap, str, channelGroup);
            sendOrStore.storeURAD(str[2], State.READ, State.UN_SEND);



        }

    }
}
