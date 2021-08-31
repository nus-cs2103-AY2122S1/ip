package duke;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class which operates Jarvis the chat-bot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Retrieves all the tasks stored by Jarvis in the hard disk upon running the main method.
     * @param filePath The file in which the tasks are stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            storage.retrieveFileContents();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        while (!Parser.parseCommand(input).equals("bye")) {
            try {
                if (Parser.parseCommand(input).equals("list")) {
                    if (TaskList.getCounter() == 0) {
                        return "\tThere are currently no tasks on your list :)";
                    } else {
                        return Parser.parseList();
                    }
                } else if (Parser.parseCommand(input).equals("done")) {
                    return Parser.parseDone(input);
                } else if (Parser.parseCommand(input).equals("delete")) {
                    return Parser.parseDelete(input);
                } else if (Parser.parseCommand(input).equals("todo")) {
                    return Parser.parseTodo(input);
                } else if (Parser.parseCommand(input).equals("deadline")) {
                    return Parser.parseDeadline(input);
                } else if (Parser.parseCommand(input).equals("event")) {
                    return Parser.parseEvent(input);
                } else if (Parser.parseCommand(input).equals("today")) {
                    return Parser.parseToday();
                } else if (Parser.parseCommand(input).equals("find")) {
                    return Parser.parseFind(input);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't " +
                            "know what that means :-(");
                }
            } catch (DukeException | IOException e) {
                //System.err.println(e);
                return "" + e;
            }
        }
        return "Bye! Hope to see you soon :)";
    }
}


