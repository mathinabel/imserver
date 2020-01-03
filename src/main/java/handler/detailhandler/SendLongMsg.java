package handler.detailhandler;

import constant.ProtoConst;
import io.netty.channel.ChannelHandlerContext;
import org.hibernate.query.Query;
import pojo.User;

import java.util.HashMap;
import java.util.List;

import static handler.session.SessionAndCommit.getSession;

public class SendLongMsg {
    public SendLongMsg(String[] str, HashMap hashMap, ChannelHandlerContext channelHandlerContext) {

        String hql = "from User where name =" + str[1] + " and pwd= " + str[2];
        Query query = getSession().createQuery(hql);
        List<User> list = query.list();
        if (list != null) {
            String token = list.get(0).getToken();
            //登陆时回送该用户的uuid
            channelHandlerContext.channel().writeAndFlush(ProtoConst.SEND_LOGIN_MSG_BACK + "|" + token + "\r\n");
            hashMap.put(token, channelHandlerContext.channel().id());
        }
    }
}
