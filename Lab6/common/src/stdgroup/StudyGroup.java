package stdgroup;

import stdgroup.enums.Semester;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Study group. Is stored in the collection.
 */

public class StudyGroup implements Comparable<StudyGroup>, Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long studentsCount; //Значение поля должно быть больше 0, Поле может быть null
    private int expelledStudents; //Значение поля должно быть больше 0
    private Float averageMark; //Значение поля должно быть больше 0, Поле не может быть null
    private Semester semesterEnum; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(Long id,String name, Coordinates coordinates, LocalDateTime creationDate, Long studentsCount, int expelledStudents, Float averageMark, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup(Long id , RawGroup group) {
        this.id = id;
        this.name = group.getName();
        this.coordinates = group.getCoordinates();
        this.creationDate = LocalDateTime.now();
        this.studentsCount = group.getStudentsCount();
        this.expelledStudents = group.getExpelledStudents();
        this.averageMark = group.getAverageMark();
        this.semesterEnum = group.getSemesterEnum();
        this.groupAdmin = group.getGroupAdmin();
    }

    /**
     * @return ID of the group.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return Name of the group.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Coordinates of the group.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Creation date of the group.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Students count.
     */
    public Long getStudentsCount() {
        return studentsCount;
    }
    /**
     * @return number of expelled students.
     */
    public int getExpelledStudents() {
        return expelledStudents;
    }

    public Float getAverageMark() {
        return averageMark;
    }

    /**
     * @return Group semester.
     */
    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    /**
     * @return Group Admin.
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return expelledStudents == that.expelledStudents &&
                name.equals(that.name) &&
                coordinates.equals(that.coordinates) &&
                Objects.equals(studentsCount, that.studentsCount) &&
                averageMark.equals(that.averageMark) &&
                semesterEnum == that.semesterEnum &&
                groupAdmin.equals(that.groupAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, studentsCount, expelledStudents, averageMark, semesterEnum, groupAdmin);
    }

    @Override
    public String toString() {
        return  "\nУчебная группа: " + id +
                "\nПод названием: " + name +
                "\nНаходящейся на координатах: " + coordinates +
                "\nДобавлена: " + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +
                "\nВсего студентов: " + studentsCount +
                "\nЧисло отчислившихся студентов: " + expelledStudents +
                "\nСредний балл: " + averageMark +
                "\nНомер семестра: " + semesterEnum +
                "\nАдмин группы:\n" + groupAdmin + '\n';
    }

    /**
     * Compares to StudyGroups
     * @param studyGroup
     * @return result of comparision
     */
    public int compareTo(StudyGroup studyGroup){
        return this.studentsCount.intValue() - studyGroup.getStudentsCount().intValue();
    }

}