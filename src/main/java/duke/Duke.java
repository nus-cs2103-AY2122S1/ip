package duke;

import duke.commands.Command;
import duke.exceptions.UserInputError;
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
    public final TaskList taskList = new TaskList(Storage.readDatabase());
    private final Ui ui = new Ui(taskList);

    public String getResponse(String input) {
        Parser parser = new Parser(input, taskList);
        String output;
        try {
            Command command = parser.parse();
            output = command.execute(taskList, ui);
        } catch (UserInputError e) {
            output = ui.formatOutput(e.getMessage());
        }
        return output;
    }

//    /**
//    * Direct input array to the correct Ui method to execute with user command.
//    *
//    * @param input Array string of user command information.
//    */
//    public String directInput(String[] input) {
//        System.out.println("direct input");
//        String output;
//        try {
//            System.out.println("enter try");
//            String cmd = input[0];
//            String details = input.length == 1 ? "" : input[1];
//            switch (cmd) {
//            case "bye":
//                output = "bye";
//                break;
//            case "list":
//                output = ui.renderList();
//                break;
//            case "done":
//                output = ui.markTaskComplete(Integer.parseInt(details));
//                break;
//            case "todo":
//                System.out.println("case todo");
//                output = ui.addNewTask(details, Task.Type.TODO);
//                break;
//            case "deadline":
//                output = ui.addNewTask(details, Task.Type.DEADLINE);
//                break;
//            case "event":
//                output = ui.addNewTask(details, Task.Type.EVENT);
//                break;
//            case "delete":
//                output = ui.deleteTask(Integer.parseInt(details));
//                break;
//            default:
//                throw new InvalidInputException();
//            }
//        } catch (UserInputError e) {
//            output = e.getMessage();
//        }
//        return output;
//    }

    /**
     * Wrapper to render Duke output in a consistent format
     *
     * @param op String representing Duke ouput.
     */
    public static String renderOutput(String op) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE);
        op.lines().forEach(line -> sb.append("      ").append(line));
        sb.append(LINE);
        return sb.toString();
    }
}
