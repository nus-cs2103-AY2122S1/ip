package meap;

import meap.exception.DukeException;
import meap.task.TaskList;
import meap.util.Parser;
import meap.util.Storage;
import java.io.File;

public class Meap {
    private final Parser PARSER;
    private static final String STORAGE_FILEPATH = "data" + File.separatorChar + "meap-storage.txt";

    public Meap() {
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

