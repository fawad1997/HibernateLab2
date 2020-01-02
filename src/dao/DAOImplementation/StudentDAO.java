package dao.DAOImplementation;

import dao.DAOInterfaces.StudentDAOI;
import dao.common.GenericDAO;
import entities.StudentEntity;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAO extends GenericDAO<StudentEntity,Integer> implements StudentDAOI {
    @Override
    public List<StudentEntity> findAll() {
        try {
            Session session = HibernateUtil.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            List<StudentEntity> studentEntityList = session.createQuery("from StudentEntity").list();
            transaction.commit();
            return studentEntityList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
