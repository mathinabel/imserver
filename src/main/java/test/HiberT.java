package test;

import constant.ProtoConst;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import pojo.UnReadMessage;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HiberT {
    public static void main(String[] args) {
        select();
    }

    public static void select() {
    //    Session session = getSession();
        //3：开启事务
   //     Transaction tx = session.beginTransaction();

        //3.HRL查询，查询全部信息，注意HRL查询的是实体类的名称，不是数据表的名称，特别注意这一点
        //Query q=session.createQuery("from User");
   /*     Query q = session.createQuery("from UnReadMessage unReadMessage ");
          List<UnReadMessage> list = q.list();
          System.out.println(list);
          for (int i =0;i<list.size();i++){
              System.out.println(list.get(i).getReceiverId());
          }*/

        String sendId = "63341ca6-6b90-40e1-80a8-e6604344d1bc";
        String msgPacket = ProtoConst.SEND_PULL_UNREAD_MSG_BACK+"|";
        if (!("".equals(sendId))) {
            //从数据库获取未读信息
            Session session = getSession();
            Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from UnReadMessage unReadMessage");
            if (q.list().size() > 0) {
                System.out.println("查到了，大于零");
                List<UnReadMessage> unreadList = q.list();
                for (int i = 0; i < unreadList.size(); i++) {
                    System.out.println("准备组包");
                    //如果客户端发送来的uuid与数据库接收者uuid相同，则将信息组包发送
                    //由客户端的chatClientHandler接收信息并且解包存储，在contractFragment里oncrate方法查询未读信息表，所以不需要eventbus
                    if (unreadList.get(i).getReceiverId().equals(sendId)) {
                        //组包  senduuid ,sendtime sendmsg
                        System.out.println("有该用户的未读消息");
                        msgPacket += (unreadList.get(i).getSenderId() + "|" + unreadList.get(i).getSendTime() + "|" +unreadList.get(i).getMsg()) + "|";
                        System.out.println("组包完成");


                    }

                }
                String backmsgpack= msgPacket.substring(0,msgPacket.length()-1);
                backmsgpack+="\r\n";
                System.out.println(backmsgpack);
            }
        }
        //4：主键查询，执行查询操作，方法一：get方法，方法2：load方法
    //    UnReadMessage u=(UnReadMessage)session.get(UnReadMessage.class, 1);
      //  UnReadMessage u = (UnReadMessage) session.load(UnReadMessage.class, 1);
    //    System.out.println(u.getReceiverId());



        //5：提交事务
  //      tx.commit();
        //6：关闭事务和session
    //    session.close();

    }

    public static Session getSession() {
        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure("hibernate.xml");
        SessionFactory sessionFactory = configure.buildSessionFactory();
        return sessionFactory.openSession();
    }
}
