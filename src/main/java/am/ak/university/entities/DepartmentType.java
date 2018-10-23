package am.ak.university.entities;

import org.springframework.web.bind.annotation.ModelAttribute;

public enum DepartmentType {

    HISTORY("History"),
    PHILOLOGY("Philology"),
    CHEMISTRY("Chemistry"),
    PHYSICS("Physics"),
    ECONOMICS("Economics and Management"),
    MATHEMATICS("Mathematics and Mechanics"),
    BIOLOGY("Biology"),
    GEOGRAPHY("Geography and Geology"),
    LAW("Law"),
    RUSSIAN("Russian Philology"),
    ROMANCE("Romance-Germanic Philology"),
    ORIENTAL("Oriental Studies"),
    JOURNALISM("Journalism"),
    PHILOSOPHY("Philosophy and Psychology"),
    INFORMATICS("Informatics and Applied Mathematics"),
    RADIOPHYSICS("Radiophysics"),
    SOCIOLOGY("Sociology"),
    INTERNATIONAL("International Relations"),
    THEOLOGY("Theology"),
    PHARMACY("Pharmacy");

    String departmentType;

    DepartmentType() {
    }

/*    @ModelAttribute("frequencies")
    public DepartmentType[] frequencies() {
        return DepartmentType.values();
    }*/

    DepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

}
