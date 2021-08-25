package duke;

import duke.command.*;
/**
 * This class is the Duke class to start the whole program
 */
public class Duke {
    private static String ind2 = "     ";

    private Storage storage;
    private Ui ui;
    private TaskList myTasks;
    private Parser parser;

    /**
     * Constructs a new duke.Duke instance
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        myTasks = new TaskList();
        parser = new Parser();
    }

    private void run() {
        storage.loadFile();
        while (ui.hasNext()) {
            String next = ui.getNext();
            int result = parser.parse(next);
            Command executeNext;
            switch (result) {
                case 0:
                    ExitCommand exitCommand = new ExitCommand();
                    exitCommand.execute();
                    return;
                case 1:
                    executeNext = new ListCommand(myTasks);
                    executeNext.execute();
                    break;
                case 2:
                    executeNext = new DoneCommand(myTasks, next);
                    executeNext.execute();
                    break;
                case 3:
                    executeNext = new DeleteCommand(myTasks, next);
                    executeNext.execute();
                    break;
                case 4:
                    executeNext = new GetDayCommand(next);
                    executeNext.execute();
                    break;
                case 6:
                    executeNext = new SearchCommand(myTasks, next);
                    executeNext.execute();
                    break;
                case 5:
                    executeNext = new AddCommand(myTasks, next);
                    executeNext.execute();
                    break;
            }
        }
    }

    /**
     * Starts the program
     *
     * @param args takes in input
     */
    public static void main(String[] args) {
        greeting();
        Duke duke = new Duke();
        duke.run();
    }

    private static void greeting() {
        String g = "Hello! I'm duke.Duke";
        String g2 = "What can I do for you?";
        Ui.myPrint(g + "\n" + ind2+ g2 );
    }

    /**
     * Saves file
     */
    public static void saveFile() {
        Storage.saveFile();
    }

}
