package am.ak.university.entities;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @Column(name = "department", length = 15, unique = true, nullable = false)
    private String departmentType; /* = DepartmentType.GEOGRAPHY.departmentType;*/

    public Department() {

    }

    public Department(String departmentType) {
        this.departmentType = departmentType;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return departmentType != null ? departmentType.equals(that.departmentType) : that.departmentType == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departmentType != null ? departmentType.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department='" + departmentType + '\'' +
                '}';
    }
}
