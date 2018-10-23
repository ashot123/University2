package am.ak.university.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class MainTest {

    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("example-unit");

    public static void main(String[] args) {
        try {
            persist();
            //findAllEmployees();
            //deleteById();
            update();
            //em.detach();  ?

        } finally {
            entityManagerFactory.close();
        }
    }


    private static void findAllEmployees() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Student> listPersons = em.createQuery(
                "SELECT p FROM Student p").getResultList();

        for (Student listPerson : listPersons) {
            System.out.println(listPerson);
        }

        em.close();

    }

    private static void persist() {


        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();


        Student student = new Student();
        //student.setId(1);
        student.setFirstName("Karen");
        student.setLastName("MK.");
        Department department = new Department();
        department.setDepartmentType(DepartmentType.BIOLOGY.departmentType);
        //em.persist(department);
        student.setDepartment(department);   //(department.setDepartmentType(department.getDepartmentType())); DepartmentType.HISTORY.geDepartmentTypepe()));


        em.persist(student);
        //em.find()  - select()
        //em.detach();  ?
        //em.merge(); - update()
        //em.persist();
        //update(em);

        em.getTransaction().commit();
        em.close();
    }

    private static void deleteById() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Student student = em.find(Student.class, 25);
        em.remove(student);
        em.getTransaction().commit();
    }


    private static void update() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT s FROM Student s");
        List<Student> resultList = query.getResultList();
        Student student = resultList.get(0);
        student.setFirstName("Ashot");

        student.setDepartment(new Department("Mew "));
        em.merge(student);

        em.getTransaction().commit();
    }

    protected void persist(Student entity, EntityManager em) {

        em.persist(entity);
    }

}
