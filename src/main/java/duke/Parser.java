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
    public static String getCommand(String input) {
        String[] strArr = input.split(" ", 2);
        return strArr[0];
    }

    /**
     * Takes in user input as a string and returns the task number for commands like
     * delete and done.
     *
     * @param input
     * @return task number of the task with reference to its number on the list
     * @throws MissingTaskNumberException
     */
    public static int taskNumber(String input) throws MissingTaskNumberException {
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            Ui.warningMissingNumber();
            throw new MissingTaskNumberException();
        } else {
            String number = strArr[1];
            return Integer.parseInt(number);
        }
    }

    /**
     * Takes in user input and returns the keyword after 'find' command
     *
     * @param input
     * @return Keyword after 'find' command
     * @throws MissingKeywordException
     */
    public static String getKeyword(String input) throws MissingKeywordException{
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            Ui.warningMissingKeyword();
            throw new MissingKeywordException();
        } else {
            return strArr[1];
        }
    }

    /**
     * Gets the description of the task after the command word from user's input
     *
     * @param input
     * @return description of the task
     * @throws MissingDescriptionException
     */
    public static String getDescription(String input) throws MissingDescriptionException {
        String[] strArr = input.split(" ", 2);
        if (strArr.length < 2) {
            Ui.warningMissingDescription(strArr[0]);
            throw new MissingDescriptionException();
        } else {
            return strArr[1];
        }
    }

    /**
     * Identifies the type of task and creates an instance of the subclasses of Task
     * such as Todo, Deadline and Event
     *
     * @param input
     * @return New instance of subclass of task
     * @throws DukeException
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
     * Parses the string input of the user to get the command keyword in order to
     * carry out the action such as add, delete, complete and exit
     *
     * @param taskList
     * @param file
     * @param writer
     * @throws DukeException
     */
    public static void parseCommand(TaskList taskList, File file, PrintWriter writer) throws DukeException {
        Scanner input = new Scanner(System.in);

        while (true) {
            String action = input.nextLine();

            if (Parser.getCommand(action).equals("done")) { //mark task as done
                int taskNum = Parser.taskNumber(action);
                String oldDescription = taskList.getIndividualTask(taskNum - 1).toString();
                taskList.completeTask(taskNum); //todo
                Storage.saveAsCompleted(file, taskList.getIndividualTask(taskNum - 1), oldDescription); //todo
            } else if (Parser.getCommand(action).equals("todo")
                    || Parser.getCommand(action).equals("deadline")
                    || Parser.getCommand(action).equals("event")) { // add task to to-do list
                taskList.addTask(identifyType(action)); //todo
                Storage.addData(writer, identifyType(action));
            } else if (action.equals("list")) { // list all items
                taskList.listItems(); //todo
            } else if (action.equals("bye")) { // exit
                Ui.showGoodbyeMessage();
                break;
            } else if (Parser.getCommand(action).equals("delete")) { // delete task
                int taskNum = Parser.taskNumber(action);
                Storage.markAsDeleted(file, taskList.getIndividualTask(taskNum - 1)); //todo
                taskList.deleteTask(taskNum); //todo
            } else if (Parser.getCommand(action).equals("find")) { // find tasks
                String keyword = getKeyword(action);
                taskList.findTasks(keyword);
            } else { // if there is an invalid input
                Ui.showInvalidInputMessage();
                throw new IllegalArgumentException();
            }
        }
    }
}
