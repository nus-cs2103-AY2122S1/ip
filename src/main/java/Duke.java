/**
 * The Bhutu chatbot app
 */

import java.io.IOException;
import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.Parser;
import duke.Items;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.Todo;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DATA_FOLDER = "./data";
    private static final String DATA_FILE = "duke.txt";

    // All UI functionality
    private final Ui ui;

    // All saved data related functionality
    private final Storage storage;

    // All user input related functionality
    private final Parser parser;

    // the list of items
    private Items items;

    /**
     * Duke Constructor
     */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage(DATA_FOLDER, DATA_FILE);
        parser = new Parser();
        try {
            items = new Items(storage.loadData());
        } catch (DukeException e) {
            Ui.printMessage("You have no previously saved items. Creating new list");
            items = new Items();
        }
    }

    /**
     * entry point of the due chatbot.
     * @throws DukeException in case any unexpected input is passed
     * @throws IOException in case of file issues
     */
    public void run() throws DukeException, IOException {
        ui.greet();
        interact();
    }

    /**
     * interacts with the user
     * @throws DukeException if input is wrong
     * @throws IOException thrown if file handling fails
     */
    public void interact() throws DukeException, IOException {
        String[] input;
        List<Task> fileContent = storage.loadData();

        if (fileContent == null) {
            System.out.println("File read error");
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            input = Ui.getInput(sc).split("\\s+");
            String command = input[0];
            String str;
            String output = "";
            try {
                String[] task = parser.compileInput(input);
                switch (command) {
                case "list":
                    output = items.printList();

                    break;
                case "done":
                    int idx = Integer.parseInt(input[1]);
                    output = items.markDone(idx);
                    str = storage.getFileLine(idx);
                    str = str.substring(0, 4) + "1" + str.substring(5);
                    storage.updateListTask(idx, str);

                    break;
                case "bye":
                    flag = false;

                    break;
                case "todo":
                    output = items.addItem(new Todo(task[0]));
                    str = "T | 0 | " + task[0];
                    storage.addToFile(str);

                    break;
                case "event":
                    output = items.addItem(new Event(task[0], task[1]));
                    str = "E | 0 | " + task[0] + " | "+ task[1];
                    storage.addToFile(str);

                    break;
                case "deadline":
                    output = items.addItem(new Deadline(task[0], task[1]));
                    str = "D | 0 | " + task[0] + " | "+ task[1];
                    storage.addToFile(str);

                    break;
                case "delete":
                    int id = Integer.parseInt(input[1]);
                    output = items.deleteItem(id);
                    storage.deleteFromFile(id);

                    break;
                default:
                    output = "I don't recognise this command\n"
                            + "Try 'list', 'todo', 'event', 'deadline', 'done' or 'bye'";

                    break;
                }
            } catch (DukeException dukeException) {
                output = dukeException.getMessage();
            } catch (Exception e) {
                Ui.printMessage(e.getMessage());
                return;
            }
            if (flag) {
                Ui.printMessage(output);
            }
        }
        Ui.printMessage("Going so soon? Hope to see you again soon!");
    }

    /**
     * The main function of Bhutu
     * @param args The command line arguments
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke().run();
    }
}
