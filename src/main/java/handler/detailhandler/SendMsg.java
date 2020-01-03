package handler.detailhandler;

import constant.ProtoConst;
import constant.State;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import pojo.UnReadMessage;

import java.util.Date;
import java.util.HashMap;

import static handler.session.SessionAndCommit.getSessionAndCommit;

public class SendMsg {
    public SendMsg(String[] str, HashMap hashMap, ChannelGroup channelGroup) {

        //hashmap存储着<各个user的selfuuid，各个channel的id>

        //从保存在hashmap里的长连接uuid找到所要发送目标的长连接的uuid
        //通过该uuid（key）找到保存在hashmap里的channel
        //通过该目标uuid的channel将数据构建发送
        //构建发送数据

        //str1          str2               str3
        //selfuuid      receiveuuid        content
        System.out.println(str[1]);
        //如果hashmap存有接收者id，即str2
        if (hashMap.containsKey(str[2])) {

            ChannelId channelid = (ChannelId) hashMap.get(str[2]);

            String backmsg = ProtoConst.SEND_MESSAGE_BACK + "|" + str[1] + "|" + str[3] + "\r\n";
            System.out.println("发送消息内容为：" + backmsg);
            //     channelid.writeAndFlush(backmsg);


            channelGroup.find(channelid).writeAndFlush(backmsg);


                /*    for (Channel channel2 : channelGroup) {
                       // if (channel2.id().equals(channelid)) {
                            System.out.println("收到消息，准备回送。。。");
                            channel2.writeAndFlush(backmsg);
                     //   }
                    }*/

            //该用户在线，进行数据库保存操作,状态为未读，但是已经发送
            UnReadMessage unReadMessage = new UnReadMessage();
            unReadMessage.setSenderId(str[1]);
            unReadMessage.setReceiverId(str[2]);
            unReadMessage.setMsg(str[3]);
            unReadMessage.setReadState(State.UN_READ);
            unReadMessage.setSendState(State.SEND);
            Date date = new Date();
            unReadMessage.setSendTime(date.toString());
            getSessionAndCommit(unReadMessage);

        } else {

            //该用户不在线，进行数据库保存操作,状态为未读,状态为未发送
                   UnReadMessage unReadMessage = new UnReadMessage();
                    unReadMessage.setSenderId(str[1]);
                    unReadMessage.setReceiverId(str[2]);
                    unReadMessage.setMsg(str[3]);
                    unReadMessage.setReadState(State.UN_READ);
                    unReadMessage.setSendState(State.UN_SEND);
                    Date date = new Date();
                    unReadMessage.setSendTime(date.toString());
                    getSessionAndCommit(unReadMessage);

            System.out.println("hashmap中没有该key!");
            for (int i=0;i<hashMap.size();i++){
                System.out.println("hashmap当前有key："+hashMap.get(i));
            }
            System.out.println("在数据库中保存未读消息");
        }

    }
}
