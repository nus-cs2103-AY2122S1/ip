import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import tasks.*;

import java.io.*;

/**
 * A chat bot named Duke that is responsive to commands.
 * 
 * @author Raveen Prabhu 
 */


public class Duke {
    private TaskList taskList;
    private Parser parser;
    
    private  Duke() throws IOException {
        taskList = Storage.readFile();
        parser = new Parser(taskList);
    }
    
    private void load() {
        Ui.intro();
        parser.run();
    }  
    
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.load();
    }
}

