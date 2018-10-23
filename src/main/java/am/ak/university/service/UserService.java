package am.ak.university.service;

import am.ak.university.dao.StudentDao;
import am.ak.university.entities.DepartmentType;
import am.ak.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    StudentDao dao;

    //Autowired
    //private static EntityManagerFactory entityManagerFactory;


    public List<Student> findAllUsers() {

        List<Student> all = dao.findAll();
        return all;
    }

    public Student findById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(Student user) {
        Student entity = dao.findById(user.getId());
        if (entity != null) {

            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setDepartment(user.getDepartment());
            entity.setDepartment(user.getDepartment());
        }
    }

    public void deleteUserById(int id) {
        dao.deleteById(Integer.toString(id)); // Todo
    }

    public void saveStudent(Student student) {
        dao.save(student);
    }
}



