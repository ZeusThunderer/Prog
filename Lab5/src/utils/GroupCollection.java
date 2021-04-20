package utils;

import stdgroup.StudyGroup;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.TreeSet;

public class GroupCollection {
    private LinkedHashSet<StudyGroup> studyGroupSet;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;
    public GroupCollection(FileManager fileManager){
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
    }

    public void removeLower(StudyGroup groupByValue) {
        studyGroupSet.removeIf(group -> group.compareTo(groupByValue) < 0);
    }

    public int getSumOfExStudends() {
        int sum = 0;
        for (StudyGroup group : studyGroupSet)
            sum += group.getExpelledStudents();
        return  sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupCollection that = (GroupCollection) o;
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
}
