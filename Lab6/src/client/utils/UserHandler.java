package client.utils;



import common.exchange.Request;


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
    private void readCommand(String userCommand[]){
        try {
                userCommand[1] = "";
                String str = groupEnter.read();
                if (str.contains(" "))
                    userCommand = str.split(" ", 2);
                else
                    userCommand[0] = str;
                userCommand[1] = userCommand[1].trim();
        } catch (NoSuchElementException exception) {
            System.err.println("Пользовательский ввод не обнаружен!");
        }
    }
    /**
     * Mode for catching server.commands from user input.
     */
    public Request handle()  {
        String[] userCommand = {"", ""};
        Request request;
        do {
            System.out.print('>');
            readCommand(userCommand);
            request = new Request( userCommand[0] );
            if (!userCommand[1].isEmpty())
                request.setObject( userCommand[1] );
        } while (request.getCommandType().equals( "" ));
        return request;
    }



}
