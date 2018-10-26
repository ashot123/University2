package am.ak.university.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id ;

    //@Size(min = 3, message="Длина фамилии должна быть больше трех")
    //@NotNull
    /*@NotEmpty*/
    @NotBlank(message = "{NotEmpty.firs_name.name}" )
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotBlank(message = "{NotEmpty.last_name}")
    @Column(name = "lastName", nullable = false)
    private String lastName;

   /* @NotBlank*/
    //@NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="id_departments")
    /*@JoinTable(name = "student_department",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})*/
    private Department department = new Department();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String
    getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Department getDepartment() {
        return department;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) &&
                Objects.equals(getFirstName(), student.getFirstName()) &&
                Objects.equals(getLastName(), student.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department=" + department +
                '}';
    }
}


