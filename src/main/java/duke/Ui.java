package duke;

/**
 *  This class handles interactions with the user
 *
 * @author Ryan Tian Jun.
 */
public class Ui {
    private static TaskList commands = new TaskList();
    private String userInput;
    private Parser parsedUserInput;
    private String inputResult;

    public Ui(String command) {
        try {
            Parser parser = new Parser(command);
            this.parsedUserInput = parser;
            this.userInput = command;
            Parser.Command commandType = parser.getCommandType();
            executeCommand(commandType);
        } catch (DukeException dukeException) {
            this.inputResult = dukeException + "\n";
        }
    }

    private void executeCommand(Parser.Command commandType) {
        try {
            if (commandType == Parser.Command.LIST) {
                this.inputResult = TaskList.listCommand();
            } else if (commandType == Parser.Command.DONE) {
                int taskNumberDone = parsedUserInput.getTaskNumber();
                this.inputResult = commands.markDone(taskNumberDone);
            } else if (commandType == Parser.Command.TODO) {
                Task taskTodo = new ToDo(userInput.substring(5).trim(), Task.Type.T);
                Ui.commands.add(taskTodo);
                this.inputResult = printTask(taskTodo);
            } else if (commandType == Parser.Command.DEADLINE) {
                Task taskDeadline = new DeadLine(userInput.substring(9).trim(),
                        Task.Type.D, returnDeadline(userInput).trim());
                Ui.commands.add(taskDeadline);
                this.inputResult = printTask(taskDeadline);
            } else if (commandType == Parser.Command.EVENT) {
                Task taskEvent = new Event(userInput.substring(6).trim(),
                        Task.Type.E, returnTimeline(userInput).trim());
                Ui.commands.add(taskEvent);
                this.inputResult = printTask(taskEvent);
            } else if (commandType == Parser.Command.DELETE) {
                int taskNumberDelete = parsedUserInput.getTaskNumber();
                this.inputResult = commands.deleteTask(taskNumberDelete);
            } else if (commandType == Parser.Command.FIND) {
                String query = parsedUserInput.getQuery();
                this.inputResult = commands.search(query);
            } else {
                // Unknown Command, throw error
                throw new DukeException("Error, Invalid Command" + "\n ");
            }
        } catch (DukeException dukeException) {
            this.inputResult = dukeException + "\n";
        }
    }

    /**
     * Returns results of commands to be output to the GUI.
     *
     * @return command result.
     */
    public String getInputResult() {
        return this.inputResult;
    }

    /**
     * Sets saved list history read from hard drive.
     */
    public static void setList(Task task) {
        commands.add(task);
    }

    /**
     * Echos user commands.
     * Level-1 Increment, not used anymore.
     *
     * @return returns an echo of the users commands.
     */
    private String echoCommand() {
        System.out.println(userInput);
        newLine();
        return userInput;
    }

    /**
     * Adds user commands to history ArrayList commands.
     * Level-2 Increment, not used anymore.
     *
     * @return Notifies the user that their command has been added.
     */
    private String addCommand() {
        System.out.println("added: " + userInput);
        newLine();
        return "added: " + userInput;
    }

    // Simply creates a new line in the terminal
    private static String newLine() {
        System.out.println("\n");
        return "\n";
    }

    // Parses out Deadline from Deadline Command
    private String returnDeadline(String command) {
        int startNumber = command.indexOf("/by");

        if (startNumber > 0 && command.length() > 3) {
            return command.substring(startNumber + 3);
        } else {
            return "No Deadline";
        }
    }

    // Parses out Timeline from Event Command
    private String returnTimeline(String command) {
        int startNumber = command.indexOf("/at");

        if (startNumber > 0 && command.length() > 3) {
            return command.substring(startNumber + 3);
        } else {
            return "No Timeline";
        }
    }

    /**
     * Prints a Task Object neatly on the command line.
     *
     * @param task Takes in a task object, invoking its toString method.
     */
    private String printTask(Task task) {
        String result = "";
        result += "Got it. I've added this task:\n";
        result += task + "\n";
        result +="Now you have " + commands + " tasks in the list.\n";
        return result;
    }

}
