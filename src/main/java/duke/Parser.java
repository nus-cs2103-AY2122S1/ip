package duke;

import duke.exceptions.DukeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingKeywordException;
import duke.exceptions.MissingTaskNumberException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Parser class includes all methods relevant to making sense of the user command.
 * All methods are static.
 *
 */
public class Parser {

    /**
     * Gets the command keyword from the user input.
     *
     * @param input The string input provided by the user.
     * @return The command keyword.
     */
    public static String getCommand(String input) {
        String[] strArr = input.split(" ", 2);
        return strArr[0];
    }

    /**
     * Takes in user input as a string and returns the task number for commands like
     * delete and done.
     *
     * @param input The string input provided by the user.
     * @return Task number of the task with reference to its number on the list.
     * @throws MissingTaskNumberException If the task number is missing.
     */
    public static int taskNumber(String input) throws MissingTaskNumberException {
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            Ui.showWarningMissingNumber(); //show in log
            Response.showWarningMissingNumber(); //show in app
            throw new MissingTaskNumberException();
        } else {
            String number = strArr[1];
            return Integer.parseInt(number);
        }
    }

    /**
     * Takes in user input and returns the keyword after 'find' command.
     *
     * @param input The string input provided by the user.
     * @return Keyword after 'find' command.
     * @throws MissingKeywordException If the keyword is missing.
     */
    public static String getKeyword(String input) throws MissingKeywordException{
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            Ui.showWarningMissingKeyword();
            Response.showWarningMissingKeyword();
            throw new MissingKeywordException();
        } else {
            return strArr[1];
        }
    }

    /**
     * Gets the description of the task after the command word from user's input.
     *
     * @param input The string input provided by the user.
     * @return Description of the task.
     * @throws MissingDescriptionException If the description is missing.
     */
    public static String getDescription(String input) throws MissingDescriptionException {
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            Ui.showWarningMissingDescription(strArr[0]);
            Response.showWarningMissingDescription(strArr[0]);
            throw new MissingDescriptionException();
        } else {
            return strArr[1];
        }
    }

    /**
     * Identifies the type of task and creates an instance of the subclasses of Task
     * such as Todo, Deadline and Event.
     *
     * @param input The string input provided by the user.
     * @return New instance of subclass of task.
     * @throws DukeException If there is any missing information, or if there is an invalid input.
     */
    static Task identifyType(String input) throws DukeException {
        String command = Parser.getCommand(input);
        String description = Parser.getDescription(input);

        switch (command) {
        case "todo":
            return new Todo(description);
        case "deadline":
            String deadlineDescription = Deadline.getDeadlineDescription(description);
            Deadline deadline = new Deadline(deadlineDescription, Deadline.getDate(input), Deadline.getTime(input)); //todo
            return deadline;
        case "event":
            String eventDescription = Event.getEventDescription(description);
            Event event = new Event(eventDescription, Event.getEventDetails(input));
            return event;
        default:
            return new Task("NA");
        }
    }

    /**
     * This method is for CLI application for duke.
     * Parses the string input of the user to get the command keyword in order to
     * carry out the action such as add, delete, complete and exit.
     *
     * @param taskList The arraylist of tasks.
     * @param file The file that the data is written to.
     * @param writer The printwriter.
     * @throws DukeException If format is wrong.
     */
    public static void parseCommand(TaskList taskList, File file, PrintWriter writer) throws DukeException {
        Scanner input = new Scanner(System.in);

        while (true) {
            String action = input.nextLine();
            String command = Parser.getCommand(action);

            switch (command) {
            case "done": // mark task as done
                int taskNum = Parser.taskNumber(action);
                String oldDescription = taskList.getIndividualTask(taskNum - 1).toString();
                taskList.completeTask(taskNum);
                Storage.saveAsCompleted(file, taskList.getIndividualTask(taskNum - 1), oldDescription);
            case "todo": // add todo
            case "deadline": // add deadline
            case "event": // add event
                taskList.addTask(identifyType(action));
                Storage.addData(writer, identifyType(action));
            case "list": // list all items
                taskList.listItems();
            case "bye": // exit
                Ui.showGoodbyeMessage();
                break;
            case "delete": // delete task
                int num = Parser.taskNumber(action);
                Storage.markAsDeleted(file, taskList.getIndividualTask(num - 1));
                taskList.deleteTask(num);
            case "find": // find tasks
                String keyword = getKeyword(action);
                taskList.findTasks(keyword);
            default: // if there is an invalid input
                Ui.showInvalidInputMessage();
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Parses user input to find the command and generate the corresponding response.
     *
     * @param taskList The arraylist of tasks.
     * @param file The file that data is written to.
     * @param writer The printwriter to edit the file.
     * @param action The string input provided by the user.
     * @return Corresponding response for the user input.
     * @throws DukeException If format is wrong.
     */
    public static String getCommandResponse(TaskList taskList, File file,
                                            PrintWriter writer, String action) throws DukeException {
        String command = Parser.getCommand(action);

        switch (command) {
        case "done":
            int taskNum = Parser.taskNumber(action);
            String oldDescription = taskList.getIndividualTaskInString(taskNum - 1);
            taskList.completeTask(taskNum);
            Storage.saveAsCompleted(file, taskList.getIndividualTask(taskNum - 1), oldDescription);
            return Response.showCompletedMessage(taskList.getIndividualTask(taskNum - 1));
        case "todo":
        case "deadline":
        case "event":
            Task task = identifyType(action);
            taskList.addTask(task);
            Storage.addData(writer, identifyType(action));
            return Response.showAddedTask(task, taskList.getLength());
        case "list":
            return Response.showList(taskList);
        case "delete":
            int num = Parser.taskNumber(action);
            Task taskToDelete = taskList.getIndividualTask(num - 1);
            Storage.markAsDeleted(file, taskToDelete);
            taskList.deleteTask(num);
            return Response.showSuccessfulDelete(taskToDelete, taskList.getLength());
        case "find":
            String keyword = getKeyword(action);
            return taskList.findTasks(keyword);
        default:
            return Response.showInvalidInputMessage();
        }
    }
}
