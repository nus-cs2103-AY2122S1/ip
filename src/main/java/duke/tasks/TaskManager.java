package duke.tasks;

import duke.PrintType;
import duke.RegexType;
import duke.exceptions.*;

import java.util.ArrayList;

/**
 * Manages all the operations on list of tasks. Central class to manage tasks
 */
public class TaskManager {
    public ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints the current status of the task
     *
     * @param line  - the message to be displayed
     * @param index - the index of the task in the taskList
     * @return - the message to be displayed in the String form along with the index of the task
     */
    public String getAcknowledgement(String line, int index) {
        return line + "\n " + tasks.get(index).showTask();
    }

    /**
     * To get the number of the tasks in the taskList
     *
     * @return - it returns the number of tasks in String form
     */
    public String getNumOfTask() {
        return "Now you have " + tasks.size() + " tasks in the list";
    }

    /**
     * Gives the list of tasks
     *
     * @return - list of tasks in the String form
     * @throws EmptyListException - when the size of the taskList is zero
     */
    public String getList() throws EmptyListException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        }
        StringBuilder fullList = new StringBuilder(PrintType.LIST_INTRO_LINE.getPrintType());

        // Add each item to listLines
        for (int i = 1; i <= tasks.size(); ++i) {
            fullList.append("\n").append(i).append(". ").append(tasks.get(i - 1).showTask());
        }

        return fullList.toString();
    }

    /**
     * Adds the Todo task to the taskList
     *
     * @param userInput - the user input for the todo task
     * @return - the message of acknowledgement in String form
     * @throws TodoException - if the description is empty
     */
    public String addTodo(String userInput) throws TodoException {

        if (userInput.equals("todo") || userInput.equals("")) {
            throw new TodoException();
        }
        // Create new duke.tasks.Todo instance an add it to end taskList
        tasks.add(new Todo(userInput));

        return getAcknowledgement(PrintType.TASK_ADDED_LINE.getPrintType(), tasks.size() - 1)
                + "\n" + getNumOfTask();
    }

    /**
     * Adds the Deadline task to the list of tasks
     *
     * @param userInput - the user input for the deadline task
     * @return - the message of acknowledgement in String form
     * @throws DeadlineException - if the format is incorrect
     */
    public String addDeadline(String userInput) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!userInput.matches(RegexType.DEADLINE_REGEX.getRegexType())) {
            throw new DeadlineException();
        }

        // get description and by from line
        String description = userInput.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String by = userInput.replaceAll(RegexType.GET_BY_REGEX.getRegexType(), "");

        // Create new duke.tasks.Deadline instance an add it to end taskList
        tasks.add(new Deadline(description, by));

        return getAcknowledgement(RegexType.TASK_ADDED_LINE.getRegexType(), tasks.size() - 1)
                + "\n" + getNumOfTask();
    }

    /**
     * Adds the Event task to the list of tasks
     * @param userInput - the user input of the Event task
     * @return - the message of acknowledgement in String form
     * @throws EventException - if the format is incorrect
     */
    public String addEvent(String userInput) throws EventException {
        // Check if userInput follows the format "<description> /at <time/date>"
        if (!userInput.matches(RegexType.EVENT_REGEX.getRegexType())) {
            throw new EventException();
        }

        // get description and by from userInput
        String description = userInput.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String at = userInput.replaceAll(RegexType.GET_AT_REGEX.getRegexType(), "");


        // Create new duke.tasks.Event instance an add it to end taskList
        tasks.add(new Event(description, at));

        return getAcknowledgement(PrintType.TASK_ADDED_LINE.getPrintType(), tasks.size() - 1)
                + "\n" + getNumOfTask();
    }

    /**
     * Returns the message of deletion of task after deleting the task.
     * The userInput argument gives the index and the details of the task
     * @param userInput - the user input to delete which task
     * @return - the message of acknowledgement in String form
     * @throws DeleteFormatException - the format of delete command is incorrect
     * @throws DeleteRangeException  - the index is incorrect
     */
    public String deleteTask(String userInput)
            throws DeleteFormatException, DeleteRangeException {

        if (!userInput.matches(RegexType.DIGITS_REGEX.getRegexType())) {
            throw new DeleteFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;

        if (index >= tasks.size()) {
            throw new DeleteRangeException();
        }


        String acknowledgement = getAcknowledgement(PrintType.TASK_DELETED_LINE.getPrintType(), index);

        tasks.remove(index);

        return acknowledgement + "\n" + getNumOfTask();
    }


    /**
     * Returns the completed status of the task.
     * The userInput argument gives the index and the details of the particular task to be
     * changed from incomplete to complete
     * @param userInput - the user input of the task that has to be added in the list
     * @return - the message of about the status of the task in the String format
     * @throws DoneFormatException  - the format is incorrect
     * @throws DoneAlreadyException - the task has already been completed
     * @throws DoneRangeException   - the index is incorrect
     */
    public String markAsDone(String userInput)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException {

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(RegexType.DIGITS_REGEX.getRegexType())) {
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= tasks.size()) {
            throw new DoneRangeException();
        }
        // Check if task is already done
        if (tasks.get(index).isDone()) {
            throw new DoneAlreadyException();
        }

        // Mark the index as done
        tasks.get(index).markAsDone();

        // Acknowledge task is done
        return getAcknowledgement(PrintType.TASK_DONE_LINE.getPrintType(), index);

    }


    /**
     * Returns the list of similar tasks which the user wants in String format.
     * The arguments argument helps in finding the list of tasks
     * @param arguments - the user input
     * @return - the list of tasks found in the String form
     * @throws NotFoundException - the task has not been found
     */
    public String findTask(String arguments) throws NotFoundException {
        String foundList = PrintType.FIND_INTRO_LINE.getPrintType() + arguments + "'";

        int numFound = 0;

        // Add each item to foundList
        for (int i = 1; i <= tasks.size(); ++i) {
            if (tasks.get(i - 1).getDescription().toLowerCase().contains(arguments.toLowerCase())) {
                numFound += 1;
                foundList = foundList + "\n" + i + ". " + tasks.get(i - 1).showTask();
            }
        }

        if (numFound == 0){
            throw new NotFoundException();
        }
        return foundList;
    }

    /**
     * Clear the list of all the tasks
     */
    public void clearList() {
        tasks.clear();
    }


}
