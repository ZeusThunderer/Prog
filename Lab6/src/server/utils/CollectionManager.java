package server.utils;

import common.exception.IncorrectScriptException;
import common.stdgroup.Coordinates;
import common.stdgroup.Person;
import common.stdgroup.StudyGroup;
import common.stdgroup.enums.Semester;
import client.utils.GroupEnter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.TreeSet;

public class CollectionManager {
    private LinkedHashSet<StudyGroup> studyGroupSet;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;
    public CollectionManager(FileManager fileManager){
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        this.loadCollection();
    }

    /**
     * sort studyGroupSet
     */
    public void sort(){
        TreeSet<StudyGroup> presort = new TreeSet<>(studyGroupSet);
        studyGroupSet = new LinkedHashSet<>();
        for (StudyGroup sg : presort){
            studyGroupSet.add(sg);
        }
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollection(studyGroupSet);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        studyGroupSet = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
        sort();
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    /**
     * @return studyGroupSet
     */
    public LinkedHashSet<StudyGroup> getStudyGroupSet() {
        return studyGroupSet;
    }
    public StudyGroup getFirst() {
        if (studyGroupSet.isEmpty()) return null;
        return (StudyGroup) studyGroupSet.toArray()[0];
    }
    public StudyGroup getLast() {
        if (studyGroupSet.isEmpty()) return null;
        return (StudyGroup) studyGroupSet.toArray()[studyGroupSet.size()-1];
    }

    /**
     * generate next ID
     * @return nex ID
     */
    public Long generateNextId() {
        if (studyGroupSet.isEmpty()) return 1L;
        return this.getLast().getId() +1;
    }

    public void addToCollection(StudyGroup studyGroup) {
        studyGroupSet.add(studyGroup);
        saveCollection();
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public String collectionType() {
        return studyGroupSet.getClass().getName();
    }

    public int collectionSize() {
        return studyGroupSet.size();
    }

    public StudyGroup getById(Long id) {
        for (StudyGroup group : studyGroupSet){
            if (group.getId().equals(id)) return  group;
        }
        return null;
    }

    public void removeFromCollection(StudyGroup groupToRemove) {
        studyGroupSet.remove(groupToRemove);
        saveCollection();
    }

    public void clearCollection() {
        studyGroupSet.clear();
    }

    public StudyGroup getByValue(StudyGroup groupToFind) {
        for (StudyGroup group : studyGroupSet) {
            if (group.equals(groupToFind)) return group;
        }
        return null;
    }

    public void removeGreater(StudyGroup groupByValue) {
        studyGroupSet.removeIf(group -> group.compareTo(groupByValue) > 0);
        saveCollection();
    }

    public void removeLower(StudyGroup groupByValue) {
        studyGroupSet.removeIf(group -> group.compareTo(groupByValue) < 0);
        saveCollection();
    }

    public int getSumOfExStudents() {
        int sum = 0;
        for (StudyGroup group : studyGroupSet)
            sum += group.getExpelledStudents();
        return  sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionManager that = (CollectionManager) o;
        return studyGroupSet.equals(that.studyGroupSet) &&
                lastInitTime.equals(that.lastInitTime) &&
                lastSaveTime.equals(that.lastSaveTime) &&
                fileManager.equals(that.fileManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyGroupSet, lastInitTime, lastSaveTime, fileManager);
    }

    @Override
    public String toString() {
        String str = "";
        for (StudyGroup stg : studyGroupSet){
            str +=stg.toString();
        }
        return str;
    }

    public void info() {
        LocalDateTime lastInitTime = this.lastInitTime;
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime lastSaveTime = this.lastSaveTime;
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("Сведения о коллекции:");
        System.out.println(" Тип: " + collectionType());
        System.out.println(" Количество элементов: " + collectionSize());
        System.out.println(" Дата последнего сохранения: " + lastSaveTimeString);
        System.out.println(" Дата последней инициализации: " + lastInitTimeString);
    }

    public void updateById(StudyGroup oldGroup , GroupEnter groupEnter) throws IncorrectScriptException {
        String name = oldGroup.getName();
        Coordinates coordinates = oldGroup.getCoordinates();
        LocalDateTime creationDate = oldGroup.getCreationDate();
        Long studensCount = oldGroup.getStudentsCount();
        int expelledStudents = oldGroup.getExpelledStudents();
        Float averageMark = oldGroup.getAverageMark();
        Semester semester = oldGroup.getSemesterEnum();
        Person groupAdmin = oldGroup.getGroupAdmin();

        this.removeFromCollection(oldGroup);

        if (groupEnter.change("Хотите изменить имя группы?")) name = groupEnter.getName();
        if (groupEnter.change("Хотите изменить координаты группы?")) coordinates = groupEnter.getCoordinates();
        if (groupEnter.change("Хотите изменить кол-во студентов?")) studensCount = groupEnter.getStudentsCount();
        if (groupEnter.change("Хотите изменить кол-во отчисленных студентов?")) expelledStudents = groupEnter.getExpelledStudents();
        if (groupEnter.change("Хотите изменить среднюю оценку")) averageMark = groupEnter.getAverageMark();
        if (groupEnter.change("Хотите изменить семестр?")) semester = groupEnter.getSemester();
        if (groupEnter.change("Хотите изменить админа группы?")) groupAdmin = groupEnter.getGroupAdmin();
        this.addToCollection(new StudyGroup(
                oldGroup.getId(),
                name,
                coordinates,
                creationDate,
                studensCount,
                expelledStudents,
                averageMark,
                semester,
                groupAdmin
        ));
    }
}
