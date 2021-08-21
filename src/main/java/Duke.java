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

    public void run() {
        ui.printWelcomeMessage();

        String input = sc.nextLine();
        Command command = parser.detectCommand(input);

        while (command != Command.EXIT) {
            switch (command) {
            case TODO:
                try {
                    parser.handleTodo(input);
                } catch (DukeException e) {
                    Ui.prettyPrint(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    parser.handleDeadline(input);
                } catch (DukeException e) {
                    Ui.prettyPrint(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    parser.handleEvent(input);
                } catch (DukeException e) {
                    Ui.prettyPrint(e.getMessage());
                }
                break;
            case LIST:
                list.printList();
                break;
            case DONE:
                try {
                    int index = parser.extractIndex(input);
                    list.markTaskAsDone(index);
                    list.updateData();
                } catch (DukeException e) {
                    Ui.prettyPrint(e.getMessage());
                }
                break;
            case DELETE:
                try {
                    int index = parser.extractIndex(input);
                    list.removeFromList(index);
                    list.updateData();
                } catch (DukeException e) {
                    Ui.prettyPrint(e.getMessage());
                }
                break;
            case UNRECOGNISED:
                Ui.prettyPrint(
                        String.format(
                                "NoSuchCommandError: Unrecognised command `%s`. Perhaps you made a typo?",
                                input.split(" ", 2)[0]));
                break;
            }
            input = sc.nextLine();
            command = parser.detectCommand(input);
        }
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke("./data/task.txt").run();
    }
}
