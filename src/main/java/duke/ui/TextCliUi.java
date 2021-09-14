package duke.ui;

import java.util.Scanner;

import duke.exception.DukeException;
import duke.logic.CommandParser;
import duke.logic.Storage;
import duke.task.TaskList;

/**
 * The command line user interface of Duke. Deals with printing messages to the console and reading user input.
 */
public class TextCliUi {
    private final Scanner sc;
    private final Ui ui;
    private boolean isExiting;

    /**
     * Creates a new instance of a user interface by creating a new scanner and
     * querying for the user's name.
     */
    public TextCliUi() {
        sc = new Scanner(System.in);
        String name = "";
        isExiting = false;
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello, I am\n" + logo);
        System.out.println("Please enter your name:");
        while (name.equals("")) {
            name = sc.nextLine();
        }
        this.ui = new Ui(name);
        System.out.printf("%s, that is a nice name. What can I do for you today?\n", name);
        System.out.println("----------------------------");

    }

    public boolean willExit() {
        return isExiting;
    }

    /**
     * Checks the user input from stdin.
     *
     * @param taskList the task list that the user is using
     * @param storage  the storage that the user wants the data to be stored into
     */
    public void checkInput(TaskList taskList, Storage storage) {
        String userInput = sc.nextLine();
        try {
            CommandParser cmdParser = new CommandParser(userInput, taskList, storage, ui);
            isExiting = cmdParser.willExit();
            System.out.println(cmdParser.getOutput());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("----------------------------");
        }
    }

}
