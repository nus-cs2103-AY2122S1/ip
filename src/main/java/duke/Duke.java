package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import java.io.File;

public class Duke {
    private final Parser PARSER;
    private static final String STORAGE_FILEPATH = "data" + File.separatorChar + "duke-storage.txt";

    public Duke() {
        Storage dukeStore = new Storage(STORAGE_FILEPATH);
        TaskList taskList = TaskList.of(dukeStore);
        PARSER = Parser.initialize(taskList);
    }

    public String getResponse(String userInput) {
        String reply;
        try {
            reply = PARSER.parseCommand(userInput);
        } catch (DukeException e) {
            reply = e.toString();
        } catch (Exception e) {
            e.printStackTrace();
            reply = e.getMessage();
        }
        return reply;
    }
}

