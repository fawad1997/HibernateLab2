package bean;

import entities.StudentEntity;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Student;
import sun.applet.Main;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class StudentBean implements Serializable {
    private Student student;

    public StudentBean() {
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    public String saveToDb(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(student.getName());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setPassword(student.getPassword());
        Session session = HibernateUtil.openSession();
        session.save(studentEntity);
        session.beginTransaction().commit();
        session.close();
        return "index";
    }
}
