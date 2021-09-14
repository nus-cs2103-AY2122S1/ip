package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * TaskList is a series of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Adds task to list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    private void checkUserInputValid(String userInput) throws DukeException {
        String[] tokens = userInput.split(" ");
        int taskNumber;
        for (int i = 1; i < tokens.length; i++) {
            try {
                taskNumber = Integer.parseInt(tokens[i]);
                if (taskNumber > tasks.size()) {
                    throw new DukeException("OOPS!!! The task to " + tokens[0] + " does not exists. Please try again!");
                }
            } catch (NumberFormatException err) {
                throw new DukeException("OOPS!!! The task to + " + tokens[0] + " does not exists. Please try again!");
            }
        }
    }
    /**
     * Marks all tasks specified by userInput as complete and return confirmation message,
     * return error message if done command is not properly formatted.
     *
     * @param userInput the index of the task in lis.
     * @throws DukeException exception thrown by Duke
     */
    public String completeTask(String userInput) throws DukeException {
        String[] tokens = userInput.split(" ");
        checkUserInputValid(userInput);
        StringBuilder completedTasksInString = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            int taskNumber = Integer.parseInt(tokens[i]);
            tasks.get(taskNumber - 1).completeTask();
            completedTasksInString.append(tasks.get(taskNumber - 1).toString()).append("\n");
        }
        return "You have completed the following task: \n" + completedTasksInString;
    }

    /**
     * Deletes all tasks specified by userInput and return string message for deleting tasks,
     * else return error message if done command is not properly formatted.
     *
     * @param userInput the index of the task in list
     * @throws DukeException exception thrown by Duke
     */
    public String deleteTask(String userInput) throws DukeException {
        String[] tokens = userInput.split(" ");
        checkUserInputValid(userInput);
        StringBuilder removedTasksInString = new StringBuilder();
        Integer[] removedTaskNumbers = new Integer[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            int taskNumber = Integer.parseInt(tokens[i]);
            removedTaskNumbers[i - 1] = taskNumber;
            removedTasksInString.append(tasks.get(taskNumber - 1).toString()).append("\n");
        }
        Arrays.sort(removedTaskNumbers, Collections.reverseOrder());
        for (int taskNumber: removedTaskNumbers) {
            tasks.remove(tasks.get(taskNumber - 1));
        }
        return "You have removed the following task:\n" + removedTasksInString
                + "You have " + tasks.size() + " task/s left.";
    }

    /**
     * Prints all the tasks in the list.
     */
    public String listToString() {
        StringBuilder listInString = new StringBuilder();
        listInString.append("Your list contains the following task/s: \n");
        for (int i = 0; i < tasks.size(); i++) {
            listInString.append(i + 1).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return listInString.toString();
    }

    /**
     * Returns newly created ToDo task otherwise throw an appropriate error.
     *
     * @param userInput user input String
     * @return Todo task.
     * @throws DukeException exception thrown by Duke
     */
    public ToDo makeToDoTask(String userInput) throws DukeException {
        if (userInput.length() == "todo".length()) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
        return new ToDo(userInput.substring("todo".length() + 1));
    }

    /**
     * Returns newly created Event task otherwise throw an appropriate error.
     *
     * @param userInput user input String
     * @return Event task
     * @throws DukeException exception thrown by Duke
     */
    public Task makeEventTask(String userInput) throws DukeException {
        int indexOfAt = userInput.indexOf("/at");
        int endIndexForEvent = "event".length();
        boolean doesAtExist = indexOfAt != -1;
        boolean doesTimeExist = userInput.length() > indexOfAt + "/at".length() + 1;
        boolean doesEventDescriptionExist = indexOfAt != endIndexForEvent + 1;

        if (!doesAtExist) {
            throw new DukeException("OOPS!!! You are missing /at before your date");
        }
        if (!doesTimeExist) {
            throw new DukeException("OOPS!!! The date of a event cannot be empty.");
        }
        if (!doesEventDescriptionExist) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

        return new Event(userInput.substring("event".length() + 1, indexOfAt - 1),
                userInput.substring(indexOfAt + "/at".length() + 1));
    }

    /**
     * Returns newly created Deadline task otherwise throw an appropriate error.
     *
     * @param userInput user input String
     * @return Deadline task
     * @throws DukeException exception thrown by Duke
     */
    public Deadline makeDeadlineTask(String userInput) throws DukeException {
        int indexOfBy = userInput.indexOf("/by");
        int endIndexForDeadline = "deadline".length();
        boolean doesByExist = indexOfBy != -1;
        boolean doesDateExist = userInput.length() > indexOfBy + "/by".length() + 1;
        boolean doesDeadlineDescriptionExist = indexOfBy != endIndexForDeadline + 1;

        if (!doesByExist) {
            throw new DukeException("OOPS!!! You are missing /by before your date");
        }
        if (!doesDateExist) {
            throw new DukeException("OOPS!!! The date of a deadline cannot be empty.");
        }

        if (!doesDeadlineDescriptionExist) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String stringDate = userInput.substring(indexOfBy + "/by".length() + 1);
        return new Deadline(userInput.substring("deadline".length() + 1, indexOfBy - 1), stringDate);

    }

    /**
     * Returns the number of tasks.
     *
     * @return number of tasks
     */
    public int noOfTasks() {
        return tasks.size();
    }

    /**
     * Returns encoded string list for saving.
     */
    public List<String> encodeTasks() {
        ArrayList<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(task.toEncodedString());
        }
        return encodedTasks;
    }


    /**
     * Returns string representation of the tasks matching the searched description,
     * if searching description is empty, it will throw an error.
     *
     * @param userInput user input String
     * @throws DukeException exception thrown by Duke
     */
    public String findTasks(String userInput) throws DukeException {
        boolean isSearchSuccess = false;
        StringBuilder foundTasksString = new StringBuilder();
        int startingIndex = 1;
        try {
            foundTasksString.append("Here are the matching tasks in your list: \n");
            String searchTerm = userInput.substring(5);
            for (Task task : tasks) {
                if (task.toString().contains(searchTerm)) {
                    isSearchSuccess = true;
                    foundTasksString.append(startingIndex).append(". ").append(task.toString()).append("\n");
                    startingIndex++;
                }
            }
            if (!isSearchSuccess) {
                return "You have no tasks matching the description. Pls try again.";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS! The task description to find cannot be empty.");
        }
        return foundTasksString.toString();
    }
}
