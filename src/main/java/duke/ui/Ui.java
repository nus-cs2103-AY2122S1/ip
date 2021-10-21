package duke.ui;

import static java.util.AbstractMap.SimpleImmutableEntry;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import duke.Duke;
import duke.exception.DukeException;
import duke.logic.CommandParser;
import duke.logic.CommandsEnum;
import duke.logic.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Returns all the output of the duke bot as Strings to be parsed by the GUI or CLI UI.
 */
public class Ui {
    private final String name;
    private boolean isExiting = false;

    /**
     * Creates a new instance of a user interface by creating a new scanner and querying for the user's name.
     */
    public Ui(String name) {
        this.name = name;

    }

    public boolean willExit() {
        return isExiting;
    }

    /**
     * Checks the user input from the source.
     *
     * @param taskList the task list that the user is using
     * @param storage  the storage that the user wants the data to be stored into
     * @return the output string
     */
    public String checkInput(String userInput, TaskList taskList, Storage storage) {
        String output = "";
        CommandParser cmdParser;
        try {
            cmdParser = new CommandParser(userInput, taskList, storage, this);
            isExiting = cmdParser.willExit();
            output += cmdParser.getOutput() + "\n";
        } catch (DukeException e) {
            output += e.getMessage() + "\n";
            return output;
        }
        return output;
    }

    public String checkInput(String userInput, Duke duke) {
        return checkInput(userInput, duke.getTaskList(), duke.getStorage());
    }

    /**
     * Returns the goodbye message. Called when the user wants to exit the program.
     *
     * @return the output string
     */
    public String getGoodByeMessage() {
        return "Bye, " + name + "! Hope to see you again soon.";
    }

    /**
     * Returns the successful add task message.
     * Called when the user successfully adds the task to tasklist.
     *
     * @param task       the task that is added
     * @param sizeOfList the number of tasks so far
     * @return the output string
     */
    public String addTaskMessage(Task task, int sizeOfList) {
        return ("Got it, " + name + ". I have added this task:\n"
            + "    " + task + "\n"
            + "Now you have " + sizeOfList + " task"
            + (sizeOfList <= 1 ? " in the list" : "s in the list"));
    }

    /**
     * Returns the successful remove task message.
     * Called when the user removes a task from the task list.
     *
     * @param task the task that is removed or deleted
     * @param size the final size of the task list
     * @return the output string
     */
    public String removeTaskMessage(Task task, int size) {
        return String.format("Noted. I've removed this task:\n%s\n%s", displayTaskMessage(task),
            displayNumberOfTasks(size));
    }

    /**
     * Displays the message when the task is done.
     *
     * @param task the task that is done
     * @return the output string
     */
    public String markAsDoneMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s", displayTaskMessage(task));
    }

    /**
     * Displays the help message that contains documentations of the commands.
     *
     * @return the output string
     */
    public String displayHelpMessage(CommandsEnum input) {
        CommandsEnum cleanedInput = Optional.ofNullable(input).orElse(CommandsEnum.HELP);
        if (cleanedInput == CommandsEnum.HELP) {
            StringBuilder helpMessageBuilder = new StringBuilder();
            helpMessageBuilder.append(cleanedInput.helpMessage());
            Arrays.stream(CommandsEnum.values()).forEach(commandsEnum ->
                helpMessageBuilder.append(commandsEnum.toString().toLowerCase()).append("\n"));
            return helpMessageBuilder.toString().trim();
        } else {
            return cleanedInput.helpMessage();
        }
    }

    /**
     * Outputs all tasks from a list of tasks and task numbers.
     *
     * @param tasksWithTaskNumbers the list of tasks and task numbers, each in a SimpleImmutableEntry
     * @param maxTaskNumber        the size of the task list. This will ensure proper padding of numbers.
     * @return the output string
     */
    public String getAllTasksMessage(List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
                                     int maxTaskNumber) {
        return (String.format("Ok, %s. I am getting all your tasks:\n%s", name,
            getMultipleTasksMessage(tasksWithTaskNumbers, maxTaskNumber)));
    }

    /**
     * Outputs all upcoming tasks from a list of tasks and task numbers.
     *
     * @param tasksWithTaskNumbers the list of tasks and task numbers, each in a SimpleImmutableEntry
     * @param maxTaskNumber        the size of the task list. This will ensure proper padding of numbers.
     * @return the output string
     */
    public String getUpcomingTasksMessage(
        List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
        int maxTaskNumber) {
        return String.format("Ok, %s. I am getting all your upcoming tasks:\n%s", name,
            getMultipleTasksMessage(tasksWithTaskNumbers, maxTaskNumber));
    }

    /**
     * Outputs all tasks from a list of tasks and task numbers containing a pattern.
     *
     * @param pattern              the string pattern that the user is searching for
     * @param tasksWithTaskNumbers the list of tasks and task numbers, each in a SimpleImmutableEntry
     * @param maxTaskNumber        the size of the task list. This will ensure proper padding of numbers.
     * @return the output string
     */
    public String getTasksWithPatternMessage(String pattern,
                                             List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
                                             int maxTaskNumber) {
        return (String.format("Ok, %s. I am getting all tasks containing %s:\n%s", name, pattern,
            getMultipleTasksMessage(tasksWithTaskNumbers, maxTaskNumber)));
    }

    private String getMultipleTasksMessage(
        List<SimpleImmutableEntry<? extends Task, Integer>> tasksWithTaskNumbers,
        int maxTaskNumber) {
        StringBuilder result = new StringBuilder();
        for (SimpleImmutableEntry<? extends Task, Integer> task : tasksWithTaskNumbers) {
            result.append(getSingleTaskMessage(task.getKey(), task.getValue(), maxTaskNumber)).append("\n");
        }
        return result.toString().trim();
    }

    private String getSingleTaskMessage(Task task, int number, int max) {
        String leadingSpace = " ".repeat((int) Math.log10(max) - (int) Math.log10(number));
        // For better formatting if numbers exceed 9
        return String.format("%s%d: %s", leadingSpace, number, task);
    }

    private String displayTaskMessage(Task task) {
        return ("    " + task);
    }

    private String displayNumberOfTasks(int num) {
        return ("Now you have " + num + " task"
            + (num <= 1 ? " in the list" : "s in the list"));
    }
}
