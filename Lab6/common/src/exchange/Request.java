package exchange;

import stdgroup.Person;
import stdgroup.RawGroup;
import stdgroup.StudyGroup;

import java.io.Serializable;

public class Request implements Serializable {
    private Serializable object;
    private RawGroup rawGroup;
    private StudyGroup updedGroup;
    private Person person;
    private String commandType;
    private User user;

    public Request(Serializable object, User user){
        this.object=object;
        this.user = user;
    }
    public Request(String commandType){
        this.commandType=commandType;
        this.user = user;
    }
    public Request(String commandType,RawGroup rawGroup, User user){
        this.rawGroup = rawGroup;
        this.commandType=commandType;
        this.user = user;
    }
    public Request(String commandType,Person person, User user){
        this.person = person;
        this.commandType=commandType;
        this.user = user;
    }
    public Request(String commandType, Serializable object,User user){
        this.commandType=commandType;
        this.object=object;
        this.user = user;
    }
    public String getCommandType() {
        return commandType;
    }
    public Serializable getObject() {
        return object;
    }
    public void setObject(Serializable object){
        this.object=object;
    }
    public void setCommandType(String type){
        this.commandType=type;
    }
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
    public boolean isEmpty() {
        return commandType.isEmpty() && object == null;
    }

    public RawGroup getRawGroup() {
        return rawGroup;
    }

    public void setRawGroup(RawGroup rawGroup) {
        this.rawGroup = rawGroup;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public StudyGroup getUpdedGroup() {
        return updedGroup;
    }

    public void setUpdedGroup(StudyGroup updedGroup) {
        this.updedGroup = updedGroup;
    }

    @Override
    public String toString() {
        return "Request{" +
                "object=" + object +
                ", rawGroup=" + rawGroup +
                ", updedGroup=" + updedGroup +
                ", person=" + person +
                ", commandType='" + commandType + '\'' +
                ", user=" + user +
                '}';
    }

    public String getLogin() {
        return user.getLogin();
    }
}
