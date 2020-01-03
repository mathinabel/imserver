package handler;

import constant.ProtoConst;
import handler.detailhandler.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;


import java.util.HashMap;

public class ProtocolProcessor {
    private ChannelHandlerContext channelHandlerContext;
    private String s;
    private ChannelGroup channelGroup;
    private HashMap hashMap;


    public ProtocolProcessor(ChannelHandlerContext channelHandlerContext, String s, ChannelGroup channelGroup, HashMap hashMap) {
        this.channelHandlerContext = channelHandlerContext;
        this.s = s;
        this.channelGroup = channelGroup;
        this.hashMap = hashMap;
    }

    public void startProcess() {
        String[] str = splitSWithI(s);
        System.out.println("00888" + str[0]);
        switch (str[0]) {

            case ProtoConst.LONG_CONNECT:
                new LongConnect(str, hashMap, channelHandlerContext);
                break;
            case ProtoConst.REGIST_WITH_PWD:
                new RegistWithPwd(str, hashMap, channelHandlerContext);
                break;
            case ProtoConst.SEND_LOGIN_MSG:
                new SendLongMsg(str, hashMap, channelHandlerContext);
                break;
            case ProtoConst.SEND_MESSAGE:
                new SendMsg(str, hashMap, channelGroup);
                break;
            case ProtoConst.ADD_FRIEND_OR_GROUP:
                new AddFriendOrGroup(str, hashMap, channelGroup);
                break;
            case ProtoConst.SEND_RECEIVED_ADD_FRIEND:
                new SendReceivedAddFriend(str, hashMap, channelGroup, channelHandlerContext);
                break;
            case ProtoConst.PULL_UNREAD_MESSAGE:
                new PullUnreadMsg(channelHandlerContext);
                break;
            case ProtoConst.PULL_UNREAD_ADDFRIEND:
                new PullUnreadAddFriend(str, channelHandlerContext);
                break;
            case ProtoConst.SEND_PULL_UNREAD_MSG:
                new SendPullUnreadMsg(str, channelHandlerContext);
                break;
            default:
                break;
        }
    }

    private String[] splitSWithI(String s) {
        return s.split("\\|");
    }


}
