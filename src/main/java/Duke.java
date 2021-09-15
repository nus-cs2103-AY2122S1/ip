import Common.Message;
import duke.Parser;
import duke.Storage;
import tasks.TaskList;

import java.io.IOException;

/**
 * A chat bot named Duke that is responsive to commands.
 * 
 * @author Raveen Prabhu 
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;

    public Duke() throws IOException {
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
    String getResponse(String input) {
        String output;
        if (input.equals("bye")) {
            output = Message.GOODBYEMESSAGE;
        } else {
            output = parser.run(input);
        } 
        return output;
    }
}

