package dao.common;

import dao.DAOImplementation.StudentDAO;
import dao.DAOInterfaces.StudentDAOI;

public class DaoFactory {
    public static StudentDAOI getStudentDAO(){
        return new StudentDAO();
    }
}
