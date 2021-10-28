package utils;



import exchange.Request;
import stdgroup.GroupEnter;


import java.io.*;
import java.util.NoSuchElementException;

public class UserHandler {
    private GroupEnter groupEnter;

    public UserHandler( BufferedInputStream stream) {
        this.groupEnter = new GroupEnter( stream );
    }
    public GroupEnter getGroupEnter(){
        return groupEnter;
    }
    /**
     * Mode for catching server.commands from user input.
     */
    public Request handle()  {
        String[] userCommand = {"", ""};
        Request request = null;
        do {
            System.out.print('>');
            userCommand[1] = "";
            String str = groupEnter.read();
            if (str.contains(" "))
                userCommand = str.split(" ", 2);
            else
                userCommand[0] = str;
            userCommand[1] = userCommand[1].trim();
           request = new Request(userCommand[0]);
           request.setObject( userCommand[1] );
        } while (request.getCommandType().equals( "" ));
        return request;
    }



}
