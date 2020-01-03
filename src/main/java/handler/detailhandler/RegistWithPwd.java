package handler.detailhandler;

import constant.ProtoConst;
import io.netty.channel.ChannelHandlerContext;
import pojo.User;

import java.util.HashMap;
import java.util.UUID;

import static handler.session.SessionAndCommit.getSessionAndCommit;

public class RegistWithPwd {
    public RegistWithPwd(String[] str, HashMap hashMap, ChannelHandlerContext channelHandlerContext) {
        String uuid = UUID.randomUUID().toString();
        //TODO 执行保存数据库操作
        User user = new User();
        user.setName(str[1]);
        user.setPwd(str[2]);
        user.setPhone(str[3]);
        user.setToken(uuid);
        getSessionAndCommit(user);


        hashMap.put(uuid, channelHandlerContext.channel().id());
        channelHandlerContext.channel().writeAndFlush(ProtoConst.REGISTER_WITH_PWD_BACK + "|" + uuid + "\r\n");
    }
}
