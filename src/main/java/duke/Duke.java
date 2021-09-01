package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.GetDayCommand;
import duke.command.ListCommand;
import duke.command.SearchCommand;
import javafx.scene.layout.VBox;

/**
 * This class is the Duke class to start the whole program
 */
public class Duke {
    private static String ind2 = "     ";

    private Storage storage;
    private Ui ui;
    private TaskList myTasks;
    private Parser parser;
    private MainWindow mainWindow;

    /**
     * Constructs a new duke.Duke instance
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        myTasks = new TaskList();
        parser = new Parser();
        run();
    }

    private void run() {
        storage.loadFile();
//        greeting();
//        while (ui.hasNext()) {
//            String next = ui.getNext();
//            int result = parser.parse(next);
//            Command executeNext;
//            switch (result) {
//            case 0:
//                ExitCommand exitCommand = new ExitCommand();
//                exitCommand.execute();
//                return;
//            case 1:
//                executeNext = new ListCommand(myTasks);
//                executeNext.execute();
//                break;
//            case 2:
//                executeNext = new DoneCommand(myTasks, next);
//                executeNext.execute();
//                break;
//            case 3:
//                executeNext = new DeleteCommand(myTasks, next);
//                executeNext.execute();
//                break;
//            case 4:
//                executeNext = new GetDayCommand(next);
//                executeNext.execute();
//                break;
//            case 6:
//                executeNext = new SearchCommand(myTasks, next);
//                executeNext.execute();
//                break;
//            case 5:
//                executeNext = new AddCommand(myTasks, next);
//                executeNext.execute();
//                break;
//            default:
//                break;
//            }
//        }
    }

    public void processInput(String input) {
        int result = parser.parse(input);
        Command executeNext;
        switch (result) {
        case 0:
            ExitCommand exitCommand = new ExitCommand();
            exitCommand.execute();
            break;
        case 1:
            executeNext = new ListCommand(myTasks);
            executeNext.execute();
            break;
        case 2:
            executeNext = new DoneCommand(myTasks, input);
            executeNext.execute();
            break;
        case 3:
            executeNext = new DeleteCommand(myTasks, input);
            executeNext.execute();
            break;
        case 4:
            executeNext = new GetDayCommand(input);
            executeNext.execute();
            break;
        case 6:
            executeNext = new SearchCommand(myTasks, input);
            executeNext.execute();
            break;
        case 5:
            executeNext = new AddCommand(myTasks, input);
            executeNext.execute();
            break;
        default:
            break;
        }
    }

//    /**
//     * Starts the program
//     *
//     * @param args takes in input
//     */
//    public static void main(String[] args) {
//        greeting();
//        Duke duke = new Duke();
//        duke.run();
//    }


    /**
     * Saves file
     */
    public static void saveFile() {
        Storage.saveFile();
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        ui.setMainWindow(mainWindow);
    }
}
