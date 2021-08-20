import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    // constants for special commands
    private static final String CMD_BYE = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_DONE = "done";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DDL = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_DEL = "delete";

    private static final String FILE_URL = "data/storedList.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadStorage());
    }

    public static void main(String[] args) {
        new Duke(FILE_URL).run();
    }

    
    public void run() {
        ui.displayGreetings();

        Scanner sc = new Scanner(System.in);
        String currLine = sc.nextLine();
        String currCommand = currLine.split(" ")[0];

        while (!currCommand.equals(CMD_BYE)) {
            Task newItem = null;

            try {
                switch (currCommand) {
                case CMD_LIST:
                    tasks.displayList();
                    break;
                case CMD_DONE:
                    int doneId = Integer.parseInt(currLine.split(" ")[1]);
                    tasks.markAsDone(doneId);
                    break;
                case CMD_DEL:
                    int delId = Integer.parseInt(currLine.split(" ")[1]);
                    tasks.deleteTask(delId);
                    break;
                case CMD_TODO:
                    if (currLine.length() <= 4 || currLine.substring(5).strip().equals("")) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    newItem = new Todo(currLine.substring(5).strip());
                    break;
                case CMD_DDL:
                    newItem = Deadline.parseNewCommand(currLine);
                    break;
                case CMD_EVENT:
                    newItem = Event.parseNewCommand(currLine);
                    break;
                default:
                    throw new DukeException("Sorry, Yiyang-bot doesn't know what does that mean.");
                }

                // check if any new item added
                if (newItem != null) {
                    tasks.addTask(newItem);
                }
            } catch (IllegalArgumentException | DateTimeParseException | IndexOutOfBoundsException | DukeException e) {
                System.err.println("\tOops :( " + e);
            } finally {
                currLine = sc.nextLine();
                currCommand = currLine.split(" ")[0];
            }
        }

        ui.displayBye();
        storage.saveStorage(tasks.getTasks());
    }
}