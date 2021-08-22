package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.DataManager;
import duke.util.Parser;
import duke.util.ToDoList;
import duke.util.Ui;

import java.util.Scanner;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    private final Parser parser;
    private final Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        DataManager dataManager = new DataManager(filePath);
        ToDoList list = new ToDoList(dataManager.readData(), dataManager);
        parser = new Parser(list, dataManager);
    }

    public static void main(String[] args) {
        new Duke("./data/task.txt").run();
    }

    /** Runs Duke */
    public void run() {
        Ui.printWelcomeMessage();
        String input;
        boolean isExit = false;
        Command command;

        while (!isExit) {
            try {
                input = sc.nextLine();
                command = parser.parse(input);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.printException(e.getMessage());
            }
        }
    }
}


