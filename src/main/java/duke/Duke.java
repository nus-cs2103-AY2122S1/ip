package duke;

import duke.command.Command;
import duke.task.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a chatbot application for CS2103T individual project.
 */
public class Duke {
    /** The local file path. */
    private static final String filePath = "data/duke.txt";
    /** The storage instance. */
    private static final Storage storage = new Storage(filePath);

    /**
     * Receives input from the user and executes Duke's actions
     *
     * @param sc The given scanner instance
     * @param taskList The given task list instance.
     */
    public static void run(Scanner sc, TaskList taskList) {
        Ui.greet();
        boolean isExit = false;
        while (!isExit && sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);
                command.execute(taskList, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            TaskList taskList = new TaskList(storage.readFromTaskTxt());
            run(sc, taskList);
        } catch (DukeException e) {
            Ui.showMessage(e.getMessage());
        } finally {
            sc.close();
        }
    }
}
