package duke;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

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
     * Scans for user input and calls the corresponding method once the command has been processed
     * by Parser.parseCommand()
     * @throws IOException if there is an error in the reading user input or Jarvis's output
     * @throws DukeException if user input is formatted incorrectly
     */
    public void run() throws IOException, DukeException {
        Scanner sc = new Scanner(System.in);
        String instruction = sc.nextLine();

        while (!Parser.parseCommand(instruction).equals("bye")) {
            try {
                if (Parser.parseCommand(instruction).equals("list")) {
                    if (TaskList.getCounter() == 0) {
                        System.out.println("\tThere are currently no tasks on your list :)");
                    } else {
                        Parser.parseList();
                    }
                } else if (Parser.parseCommand(instruction).equals("done")) {
                    Parser.parseDone(instruction);
                } else if (Parser.parseCommand(instruction).equals("delete")) {
                    Parser.parseDelete(instruction);
                } else if (Parser.parseCommand(instruction).equals("todo")) {
                    Parser.parseTodo(instruction);
                } else if (Parser.parseCommand(instruction).equals("deadline")) {
                    Parser.parseDeadline(instruction);
                } else if (Parser.parseCommand(instruction).equals("event")) {
                    Parser.parseEvent(instruction);
                } else if (Parser.parseCommand(instruction).equals("today")) {
                    Parser.parseToday();
                } else {
                    throw new DukeException("\tOOPS!!! I'm sorry, but I don't " +
                            "know what that means :-(");
                }
            } catch (DukeException | IOException e) {
                System.err.println(e);
            }
            System.out.println("----------------------------------");
            instruction = sc.nextLine();
        }
        System.out.println("\t" + "Bye! Hope to see you soon :)");
        System.out.println("----------------------------------");
    }

    /**
     * Runs Jarvis and starts the input/output calls
     * @param args
     * @throws IOException if there is an error in the reading user input or Jarvis's output
     * @throws DukeException if user input is formatted incorrectly
     */
    public static void main (String[]args) throws IOException, DukeException {
        new Duke("data/jarvis.txt").run();
    }
}


