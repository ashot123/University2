package am.ak.university.dao;

import am.ak.university.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentDao {

    @PersistenceContext
    private EntityManager em;


    public List<Student> findAll() {

        List<Student> listPersons = em.createQuery("SELECT p FROM Student p").getResultList();
        return listPersons;
    }

    public Student findById(int id) {
        Student student =  em.find(Student.class, id);
       /* if (student != null) {
            //initializeCollection(user.getUserProfiles());  // TODO
        }*/
        return student;
    }

    public void deleteById(String idStr) {
        Integer id = Integer.parseInt(idStr);
        Student student = (Student) em
                .createQuery("SELECT u FROM Student u WHERE u.id LIKE :id")
                .setParameter("id", id)
                .getSingleResult();
        delete(student);
    }

    protected void delete(Student student) {
        em.remove(student);
    }

    public void save(Student student) {
        em.persist(student);
    }
}
