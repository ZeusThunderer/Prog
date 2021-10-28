package exchange;


import stdgroup.StudyGroup;

import java.io.Serializable;

public class Response implements Serializable {
    private CommandStatus status;
    private String response;
    private StudyGroup updGroup;
    public Response(CommandStatus status, String response){
        this.status = status;
        this.response = response;
    }
    public Response(CommandStatus status, String response,StudyGroup updGroup){
        this.status = status;
        this.response = response;
        this.updGroup = updGroup;
    }
    public Response(CommandStatus status){
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public CommandStatus getCommandStatus() { return status; }
    public void setCommandStatus(CommandStatus status) { this.status = status; }

    public void setResponse(String response) {
        this.response = response;
    }
    public void addResponse(String response){
        this.response += response;
    }

    public StudyGroup getUpdGroup() {
        return updGroup;
    }

    public void setUpdGroup(StudyGroup updGroup) {
        this.updGroup = updGroup;
    }
}
