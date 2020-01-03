package handler.detailhandler;

import constant.ProtoConst;
import constant.State;
import io.netty.channel.ChannelHandlerContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.UnReadMessage;

import java.util.List;

import static handler.session.SessionAndCommit.getSession;
import static handler.session.SessionAndCommit.getSessionAndCommit;

public class SendPullUnreadMsg {
    public SendPullUnreadMsg(String[] str, ChannelHandlerContext channelHandlerContext) {
        //逻辑要清楚这里的str[1]是客户端发送自身的uuid来索取未读信息的，所以对应未读信息表中的接收者uuid
        String sendId = str[1];
        String msgPacket = ProtoConst.SEND_PULL_UNREAD_MSG_BACK + "|";
        if (!("".equals(sendId))) {
            //从数据库获取未读信息
            //未读消息状态为
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from UnReadMessage unReadMessage");
            if (q.list().size() > 0) {
                List<UnReadMessage> unreadList = q.list();
                for (int i = 0; i < unreadList.size(); i++) {
                    //如果客户端发送来的uuid与数据库接收者uuid相同，并且是状态是未读过的，则将信息组包发送
                    //由客户端的chatClientHandler接收信息并且解包存储，在contractFragment里oncrate方法查询未读信息表，所以不需要eventbus
                    if ((unreadList.get(i).getReceiverId().equals(sendId)
                            &&(unreadList.get(i).getReadState()== State.UN_READ))) {
                        //组包  senduuid ,sendtime sendmsg
                        msgPacket += (unreadList.get(i).getSenderId() + "|" + unreadList.get(i).getSendTime() + "|" + unreadList.get(i).getMsg()) + "|";


                    }

                }
                String backmsgpack = msgPacket.substring(0, msgPacket.length() - 1);
                backmsgpack += "\r\n";

                System.out.println("组包返回信息为："+backmsgpack);
                channelHandlerContext.channel().writeAndFlush(backmsgpack);
                //组包完成，发送完成之后，将消息标记为已发送
                for (int i = 0; i < unreadList.size(); i++) {
                    unreadList.get(i).setReadState(State.READ);
                    getSessionAndCommit(unreadList);
                }
            }
        }
    }
}
