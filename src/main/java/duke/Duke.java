package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class Duke {

    /** Relative path to file used in our Duke simulations */
    public static String FILE_PATH = "data/";
    
    TaskList taskList;
    FileController fc;
    UI ui;

    private Duke() {
        fc = new FileController(FILE_PATH, "list.txt");
        String contents = fc.readContentsAsString();
        taskList = new TaskList(contents);
        ui = new UI();
        ui.showWelcome();
    }


    private void start() {
        Parser parser = new Parser();
        System.out.println("Hello, what can I do for you.\n");
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = parser.parseCommand(input);
                c.execute(taskList, ui, fc);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printText(e.getMessage());
            }

        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}
