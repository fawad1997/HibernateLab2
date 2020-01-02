package bean;

import dao.DAOImplementation.StudentDAO;
import dao.common.DaoFactory;
import entities.StudentEntity;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pojo.Student;
import sun.applet.Main;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class StudentBean implements Serializable {
    private Student student;
    private List<Student> students;

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

    public List<Student> getAllStudents(){

        try {
            List<StudentEntity> entities = DaoFactory.getStudentDAO().findAll();
            students = new ArrayList<Student>();
            for (int i=0;i<entities.size();i++){
                Student std = new Student();
                std.setId(entities.get(i).getId());
                std.setName(entities.get(i).getName());
                std.setEmail(entities.get(i).getEmail());
                students.add(std);
            }
            //connection.getConnection().close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
    public List<Student> getDataTable() {
        if (students == null)
            students = getAllStudents();
        return students;
    }
}
