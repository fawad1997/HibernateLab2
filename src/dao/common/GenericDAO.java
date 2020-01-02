package dao.common;

import hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;

public class GenericDAO<T, ID extends Serializable> implements GenericDAOI<T,ID> {
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";

    @Override
    public Session getSession() {
        try {
            return HibernateUtil.openSession();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Session getCurrentSession() {
        try {
            return HibernateUtil.getCurrentSession();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String saveOrUpdate(T instance) {
        String msg = SUCCESS;
        if(instance == null){
            msg = FAILURE + "- null instance cann't be saved";
            return msg;
        }
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(instance);
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
            msg=FAILURE;
        }
        return msg;
    }

    @Override
    public String delete(T instance) {
        String msg =SUCCESS;
        if(instance == null){
            msg=FAILURE+"- null object cann't be deleted";
            return msg;
        }
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(instance);
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
            msg=FAILURE;
        }
        return msg;
    }

    @Override
    public String delete(Class<T> instance, ID id) {
        String msg = SUCCESS;
        if(id==null){
            msg = FAILURE + " - null id cannot be deleted";
            return msg;
        }
        if(instance == null){
            msg = FAILURE + " - null instance cannot be deleted";
            return msg;
        }
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            Object object = findById(session,instance,id);
            if(object!=null){
                session.delete(object);
                session.getTransaction().commit();
            }
        }catch (HibernateException e){
            e.printStackTrace();
            msg=FAILURE;
        }
        return msg;
    }

    @Override
    public String delete(T[] instances){
        String msg = SUCCESS;
        if(instances == null){
            msg = FAILURE + " - null instances cannot be deleted";
            return msg;
        }
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            for(Object object:instances){
                session.delete(object);
            }
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
            msg=FAILURE;
        }
        return msg;
    }

    @Override
    public T findById(Class<T> clas, ID id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("null id cannot be retrieved");
            }
            if (clas == null) {
                throw new IllegalArgumentException("Persistent object type cannot be null");
            }
            Session session = getCurrentSession();
            session.getTransaction();
            T t = session.get(clas,id);
            session.getTransaction().commit();
            return t;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T findById(Session session, Class<T> clas, ID id) {
        try {
            if(id == null){
            throw new IllegalAccessException("null id cann't be retrived");
        }
        if(clas == null){
            throw new IllegalAccessException("null object cann't be retrived");
        }
        return (T) session.get(clas,id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countRecords(Class<T> clas) {
        return 0;
    }
}
