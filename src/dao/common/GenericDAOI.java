package dao.common;

import org.hibernate.Session;

import java.io.Serializable;

public interface GenericDAOI<T,ID extends Serializable> {
    Session getSession();
    Session getCurrentSession();
    String saveOrUpdate(T instance);
    String delete(T instance);
    String delete(Class<T> instance,ID id);
    String delete(T[] instances);
    T findById(Class<T> clas,ID id);
    T findById(Session session,Class<T> clas,ID id);
    int countRecords(Class<T> clas);
}
