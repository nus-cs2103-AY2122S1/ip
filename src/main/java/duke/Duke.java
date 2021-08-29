package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.UserInputError;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * The Duke class that runs the Duke program.
 */
public class Duke {

    private static final String LINE =
        "     ____________________________________________________________\n";
    public static TaskList taskList;
    private Ui ui;

    /**
    * Run Duke and parse user input.
    */
    private void run() {
        ui = new Ui();
        ui.greet();
        taskList = new TaskList(Storage.readDatabase());

        while (ui.hasInput()) {
            String input = ui.nextInput();
            Parser parser = new Parser(input);

            try {
                directInput(parser.parse());
            } catch (UserInputError e) {
                Duke.renderOutput(e.getMessage());
            }
            if (ui.hasEnded()) {
                break;
            }
        }

        Storage.writeDatabase(taskList.toArrayList());
        ui.end();
    }

    /**
    * Direct input array to the correct Ui method to execute with user command.
    *
    * @param input Array string of user command information.
    */
    public void directInput(String[] input) {
        try {
            String cmd = input[0];
            String details = input.length == 1 ? "" : input[1];
            if (cmd.equals("bye")) {
                ui.setEndChat();
            } else if (cmd.equals("list")) {
                ui.renderList();
            } else if (cmd.equals("done")) {
                ui.markTaskComplete(Integer.parseInt(details));
            } else if (cmd.equals("todo")) {
                ui.addNewTask(details, Task.Type.TODO);
            } else if (cmd.equals("deadline")) {
                ui.addNewTask(details, Task.Type.DEADLINE);
            } else if (cmd.equals("event")) {
                ui.addNewTask(details, Task.Type.EVENT);
            } else if (cmd.equals("delete")) {
                ui.deleteTask(Integer.parseInt(details));
            } else {
                throw new InvalidInputException();
            }
        } catch (UserInputError e) {
            Duke.renderOutput(e.getMessage());
        }
    }

    /**
     * Wrapper to render Duke output in a consistent format
     *
     * @param op String representing Duke ouput.
     */
    public static void renderOutput(String op) {
        System.out.println(LINE);
        op.lines().forEach(line -> System.out.println("      " + line));
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }
}
