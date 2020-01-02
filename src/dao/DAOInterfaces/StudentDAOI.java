package dao.DAOInterfaces;

import dao.common.GenericDAOI;
import entities.StudentEntity;

import java.util.List;

public interface StudentDAOI extends GenericDAOI<StudentEntity,Integer> {
    public List<StudentEntity> findAll();
}
