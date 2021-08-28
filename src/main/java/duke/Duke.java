package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.util.DataManager;
import duke.util.Parser;
import duke.util.ToDoList;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    private final Parser parser;

    public Duke(String filePath) {
        DataManager dataManager = new DataManager(filePath);
        ToDoList list = new ToDoList(dataManager.readData(), dataManager);
        parser = new Parser(list, dataManager);
    }

    public String getResponse(String input) {
        Command command = parser.parse(input);

        return command.getResponse(input);
    }
}
