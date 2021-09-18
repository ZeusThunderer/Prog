package exchange;

import java.io.Serializable;

public class Request implements Serializable {
    private Serializable object;
    private String commandType;

    public Request(String commandType){
        this.commandType=commandType;
    }
    public Request(String commandType, Serializable object){
        this.commandType=commandType;
        this.object=object;
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

    public boolean isEmpty() {
        return commandType.isEmpty() && object == null;
    }
}
