package genie;

import genie.common.Message;
import genie.parser.Parser;
import genie.storage.Storage;
import genie.tasks.TaskList;

import java.io.IOException;

/**
 * A chat bot named Genie that is responsive to commands and keeps track of all the tasks that 
 * the user has to do.
 * 
 * @author Raveen Prabhu 
 */
public class Genie {
    private TaskList taskList;
    private Parser parser;

    public Genie() throws IOException {
        taskList = Storage.readFile();
        parser = new Parser(taskList);
        System.out.println("Duke constructor called");
    }
    
    /**
     * Generate a response to the user input
     * 
     * @param input Input provided by user.
     * @return Response message by system.
     */
    public String getResponse(String input) {
        assert input.length() != 0;
        String output;
        if (input.equals("bye")) {
            output = Message.GOODBYEMESSAGE;
        } else {
            output = parser.run(input);
        } 
        return output;
    }
}

