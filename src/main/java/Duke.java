import java.util.Scanner;

/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    private final Ui ui;
    private final Parser parser;
    private final ToDoList list;
    private final Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new Ui();
        DataManager dataManager = new DataManager(filePath);
        list = new ToDoList(dataManager.readData(), dataManager);
        parser = new Parser(list, dataManager);
    }

    public static void main(String[] args) {
        new Duke("./data/task.txt").run();
    }

    public void run() {
        ui.printWelcomeMessage();

        String input = sc.nextLine();
        Command command = parser.detectCommand(input);

        while (command != Command.EXIT) {
            try {
                switch (command) {
                case TODO:
                    parser.handleTodo(input);
                    break;
                case DEADLINE:
                    parser.handleDeadline(input);
                    break;
                case EVENT:
                    parser.handleEvent(input);
                    break;
                case LIST:
                    list.printList();
                    break;
                case DONE:
                    int index = parser.extractIndex(input);
                    list.markTaskAsDone(index);
                    list.updateData();
                    break;
                case DELETE:
                    index = parser.extractIndex(input);
                    list.removeFromList(index);
                    list.updateData();
                    break;
                case UNRECOGNISED:
                    throw new NoSuchCommandException(input.split(" ", 2)[0]);
                }
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
            input = sc.nextLine();
            command = parser.detectCommand(input);
        }
        ui.printExitMessage();
    }
}


