package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static Session openSession()
    {
        return getSessionFactory().openSession();
    }
    public static Session getCurrentSession(){
        if(session==null){
            session = openSession();
        }
        return session;
    }
    public static void closeSession(Session session)
    {
        if (session.isOpen())
        {
            session.close();
        }
    }
}