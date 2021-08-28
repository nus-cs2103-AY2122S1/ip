package duke;

/**
 *  This class handles interactions with the user
 *
 * @author Ryan Tian Jun.
 */
public class Ui {
    private static TaskList commands = new TaskList();
    private String currentCommand;
    private Parser parsedCommand;


    public Ui(String command) {
        try {
            Parser parser = new Parser(command);
            this.parsedCommand = parser;
            this.currentCommand = command;
            Parser.Command commandType = parser.getCommandType();
            executeCommand(commandType);
        } catch (DukeException dukeException) {
            System.out.println(dukeException + "\n");
        }
    }

    private void executeCommand(Parser.Command commandType) {
        try {
            if (commandType == Parser.Command.LIST) {
                TaskList.userCommands();
            } else if (commandType == Parser.Command.DONE) {
                int taskNumberDone = parsedCommand.getTaskNumber();
                commands.markDone(taskNumberDone);
            } else if (commandType == Parser.Command.TODO) {
                Task taskTodo = new ToDo(currentCommand.substring(5).trim(), Task.Type.T);
                Ui.commands.add(taskTodo);
                printTask(taskTodo);
            } else if (commandType == Parser.Command.DEADLINE) {
                Task taskDeadline = new DeadLine(currentCommand.substring(9).trim(),
                        Task.Type.D, returnDeadline(currentCommand).trim());
                Ui.commands.add(taskDeadline);
                printTask(taskDeadline);
            } else if (commandType == Parser.Command.EVENT) {
                Task taskEvent = new Event(currentCommand.substring(6).trim(),
                        Task.Type.E, returnTimeline(currentCommand).trim());
                Ui.commands.add(taskEvent);
                printTask(taskEvent);
            } else if (commandType == Parser.Command.DELETE) {
                int taskNumberDelete = parsedCommand.getTaskNumber();
                commands.deleteTask(taskNumberDelete);
            } else if (commandType == Parser.Command.FIND) {
                String query = parsedCommand.getQuery();
                commands.search(query);
            } else {
                // Unknown Command, throw error
                throw new DukeException("Error, Invalid Command");
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException + "\n");
        }


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
        System.out.println(currentCommand);
        newLine();
        return currentCommand;
    }

    /**
     * Adds user commands to history ArrayList commands.
     * Level-2 Increment, not used anymore.
     *
     * @return Notifies the user that their command has been added.
     */
    private String addCommand() {
        System.out.println("added: " + currentCommand);
        newLine();
        return "added: " + currentCommand;
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
    private void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + commands + " tasks in the list.\n");
    }

}
