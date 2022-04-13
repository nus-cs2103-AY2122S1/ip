package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Duke is a chatbot application for CS2103T individual project.
 */
public class Duke {
    /** The local file path. */
    private static final String filePath = System.getProperty("user.dir") + "/data/duke.txt";
    /** The storage instance. */
    private final Storage storage;
    /** The task list. */
    private final TaskList taskList;

    /**
     * Constructs a duke instance.
     */
    public Duke() {
        storage = new Storage(filePath);
        taskList = new TaskList(storage.readFromTaskTxt());
    }

    /**
     * Receives input from the user and executes Duke's actions
     *
     * @param sc       The given scanner instance
     */
    public void run(Scanner sc) {
        Ui.greet();
        boolean isExit = false;
        while (!isExit && sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);
                command.executeAndShow(taskList, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Executes the main method.
     *
     * @param args the given args.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new Duke().run(sc);
        sc.close();
    }

    /**
     * Returns the result string based on the given input.
     */
    public Response getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String message = command.execute(taskList, storage);
            return new Response(message, command.isExit());
        } catch (DukeException e) {
            return new Response(e.getMessage(), false);
        }
    }
}
