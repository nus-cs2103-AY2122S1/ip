import java.util.ArrayList;
import java.util.List;

/**
 *  This class handles interactions with the user
 *
 * @author Ryan Tian Jun.
 */
public class Ui {
    private String currentCommand;
    private static TaskList commands = new TaskList();
    private Parser parsedCommand;

    public Ui(String command) {
        try {
            Parser parser = new Parser(command);
            this.parsedCommand = parser;
            this.currentCommand = command;
            Parser.COMMAND commandType = parser.getCommandType();
            executeCommand(commandType);
        } catch (DukeException dukeException) {
            System.out.println(dukeException + "\n");
        }
    }

    private void executeCommand(Parser.COMMAND commandType) {
        try {
            if (commandType == Parser.COMMAND.LIST) {
                TaskList.userCommands();
            } else if (commandType == Parser.COMMAND.DONE) {
                int taskNumberDone = parsedCommand.getTaskNumber();
                commands.markDone(taskNumberDone);
            } else if (commandType == Parser.COMMAND.TODO) {
                Task taskTodo = new ToDo(currentCommand.substring(5).trim(), Task.TYPE.T);
                Ui.commands.add(taskTodo);
                printTask(taskTodo);
            } else if (commandType == Parser.COMMAND.DEADLINE) {
                Task taskDeadline = new DeadLine(currentCommand.substring(9).trim(),
                        Task.TYPE.D, returnDeadline(currentCommand).trim());
                Ui.commands.add(taskDeadline);
                printTask(taskDeadline);
            } else if (commandType == Parser.COMMAND.EVENT) {
                Task taskEvent = new Event(currentCommand.substring(6).trim(),
                        Task.TYPE.E, returnTimeline(currentCommand).trim());
                Ui.commands.add(taskEvent);
                printTask(taskEvent);
            } else if (commandType == Parser.COMMAND.DELETE) {
                int taskNumberDelete = parsedCommand.getTaskNumber();
                commands.deleteTask(taskNumberDelete);
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
     * @param task Takes in a task objec, invoking its toString method.
     */
    private void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + commands + " tasks in the list.\n");
    }

}
