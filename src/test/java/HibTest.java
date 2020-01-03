import bean.Book;
import bean.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pojo.User;

import java.util.HashMap;
import java.util.List;

public class HibTest {
    private HashMap hashMap = new HashMap<String, String>();

    public static void main(String[] args) {
        //  Book book= session.get(Book.class,2L);
        //    session.save(new Card("abc",123));
        //   session.beginTransaction().commit();
        //   Card card= session.get(Card.class,2L);


        String s = "18855323708";
        String hql2 = "from User where phone =" + s;
        Query query2 = getSession().createQuery(hql2);
        List<User> list2 = query2.list();
        User user1 = list2.get(0);
        String touuid = user1.getToken();
        setHash(touuid);
        System.out.println(touuid);
        //System.out.println(query.list().toString());

    }

    public static Session getSession() {
        Configuration configuration = new Configuration();
        Configuration configure = configuration.configure("hibernate.xml");
        SessionFactory sessionFactory = configure.buildSessionFactory();
        return sessionFactory.openSession();
    }

    public  static  void setHash(String string) {

    }
}
