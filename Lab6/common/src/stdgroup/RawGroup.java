package stdgroup;

import stdgroup.enums.Semester;

import java.io.Serializable;

public class RawGroup implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private int expelledStudents; //Значение поля должно быть больше 0
    private Float averageMark; //Значение поля должно быть больше 0, Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public RawGroup(String name , Coordinates coordinates , Long studentsCount , int expelledStudents , Float averageMark , Semester semesterEnum , Person groupAdmin) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public int getExpelledStudents() {
        return expelledStudents;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public Float getAverageMark() {
        return averageMark;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }
}
