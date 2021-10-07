package utils;

import exception.GroupNotFoundException;
import exception.NoDataException;
import exception.NoSuchStatementException;
import exchange.Request;
import stdgroup.StudyGroup;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CollectionManager {
    private LinkedHashSet<StudyGroup> studyGroupSet = new LinkedHashSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private DBManager dbManager = new DBManager();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public CollectionManager() throws SQLException {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.loadCollection();
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        lock.writeLock().lock();
        try {
            dbManager.load( studyGroupSet );
            lastInitTime = LocalDateTime.now();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    /**
     * @return studyGroupSet
     */
    public LinkedHashSet<StudyGroup> getStudyGroupSet() {
        return studyGroupSet;
    }

    /**
     * generate next ID
     * @return nex ID
     */
    /*public int generateNextId() {
        if (studyGroupSet == null) return 1L;
        return this.getLast().getId() +1;
    }*/
    public void addToCollection(Request request) {
        lock.writeLock().lock();
        try {
            studyGroupSet.add( dbManager.add( request ) );
            saveCollection(); } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoDataException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public String collectionType() {
        return studyGroupSet.getClass().getName();
    }

    public int collectionSize() {
        lock.readLock().lock();
        try {
            return studyGroupSet.size();
        } finally {
            lock.readLock().unlock();
        }
    }

    public StudyGroup getById(int id) {

        try {
            for (StudyGroup group : studyGroupSet) {
                if (group.getId() == id) return group;
            }
        } finally {

        }
        return null;
    }

    public void removeFromCollection(StudyGroup studyGroup, Request request) {
        lock.writeLock().lock();
        try {
            dbManager.remove( request );
            studyGroupSet.remove( studyGroup );
            saveCollection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoSuchStatementException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clearCollection() {
        lock.writeLock().lock();
        try {
            dbManager.clear();
            studyGroupSet.clear();
            saveCollection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public StudyGroup getByValue(StudyGroup groupToFind) {
        for (StudyGroup group : studyGroupSet) {
            if (group.equals(groupToFind)) return group;
        }
        return null;
    }

    public void removeGreater(Request request) throws GroupNotFoundException{
        studyGroupSet.removeIf(group -> group.compareTo(request.getUpdedGroup()) > 0);
    }

    public void removeLower(Request request) throws GroupNotFoundException {
        studyGroupSet.removeIf(group -> group.compareTo(request.getUpdedGroup()) < 0);
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
                lastSaveTime.equals(that.lastSaveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studyGroupSet, lastInitTime, lastSaveTime);
    }

    @Override
    public String toString() {
        lock.readLock().lock();
        try {
            String str = "";
            for (StudyGroup stg : studyGroupSet){
                str +=stg.toString();
            }
            return str;
        } finally {
            lock.readLock().unlock();
        }
    }
    public String info() {
        LocalDateTime lastInitTime = this.lastInitTime;
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime lastSaveTime = this.lastSaveTime;
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return "\nСведения о коллекции:"+
                "\nТип: " + collectionType() +
                "\nКоличество элементов: " + collectionSize()+
                "\nДата последнего сохранения: " + lastSaveTimeString +
                "\nДата последней инициализации: " + lastInitTimeString;
    }

    public void updateById(Request request) {
        lock.writeLock().lock();
        try {
            dbManager.update(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NoDataException e) {
            e.printStackTrace();
        } catch (NoSuchStatementException e) {
            e.printStackTrace();
        } finally {

        } lock.readLock().lock();
    }

    public DBManager getDbManager() {
        return dbManager;
    }
}
