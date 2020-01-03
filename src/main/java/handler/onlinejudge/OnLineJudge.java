package handler.onlinejudge;

import constant.ProtoConst;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import org.hibernate.query.Query;
import pojo.Group;
import pojo.UnReadAddFriendRequest;
import pojo.User;

import java.util.HashMap;
import java.util.List;

import static handler.session.SessionAndCommit.getSession;
import static handler.session.SessionAndCommit.getSessionAndCommit;

public class OnLineJudge {
    private HashMap hashMap;
    private String strings;


    /**
     *
     * @param hashMap hashmap
     * @param str phone number
     */
    public OnLineJudge(HashMap hashMap, String str) {
        this.hashMap = hashMap;
        this.strings = str;


    }


    public String judegOnLineWithPhoneFromUser() {

        String hql2 = "from User where phone =" + strings;
        Query query2 = getSession().createQuery(hql2);
        List<User> list2 = query2.list();
        User user1 = list2.get(0);
        String touuid = user1.getToken();
        System.out.println("收到号码查询到的好友uuid为" + touuid);
        if (hashMap.containsKey(touuid)) {
            System.out.println("hashmap含有该uuid");
            return touuid;
        } else {
            return "";
        }


    }

    public String judegOnLineWithGroupIdfromGroup() {

        String hql = "from Group where groupId =" + strings;
        Query query = getSession().createQuery(hql);
        List<Group> list = query.list();
        Group group = list.get(0);
        String groupuuid = group.getToken();
        System.out.println("收到号码查询到的群uuid为" + groupuuid);
        if (hashMap.containsKey(groupuuid)) {
            System.out.println("hashmap含有该uuid");
            return groupuuid;
        } else {
            return "";
        }

    }

        public void judgeAndSendBack(){

        if (!judegOnLineWithGroupIdfromGroup().equals("")){

        }
        }
  /*      if (hashMap.containsKey(uuid)) {
            System.out.println("hashmap含有该uuid");
            //如果用户在线，channelgroup找到该id所对应的channel发过去
            ChannelId channelid = (ChannelId) hashMap.get(uuid);
            String backmsg = ProtoConst.ADD_FRIEND_BACK + "|" + strings[1] + "|" + uuid + "\r\n";
            channelGroup.find(channelid).writeAndFlush(backmsg);
            System.out.println("addfriends回送内容为" + backmsg);
        } else {
            //该用户不在线，进行数据库保存加好友请求
            UnReadAddFriendRequest unReadAddFriendRequest = new UnReadAddFriendRequest();
            unReadAddFriendRequest.setReceiverId(uuid);
            unReadAddFriendRequest.setSenderId(strings[1]);
            getSessionAndCommit(unReadAddFriendRequest);

            System.out.println("该用户不在线，进行数据库保存加好友请求");

            //用户上线时，需要拉取未读加好友消息，
        }*/

}
