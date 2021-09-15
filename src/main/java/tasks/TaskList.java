package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.DukeException;

/**
 * The TaskList class stores the list of tasks.
 */
public final class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs the TaskList object.
     *
     * @param inputTasks the list of tasks to be stored
     */
    public TaskList(ArrayList<Task> inputTasks) {
        this.tasks = inputTasks;
    }

    /**
     * Gets the list of tasks stored.
     *
     * @return the stored list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the String representation of the date/deadline as input by user.
     *
     * @param userInput the String array representation of the input by user
     * @return the concatenated String that is the date specified by user
     */
    public String filterInfo(ArrayList<String> userInput) {
        String temp = "";
        for (int i = 1; i < userInput.size(); i++) {
            if (!(userInput.get(i).equals("/at") || userInput.get(i).equals("/by"))
                    || userInput.get(0).equalsIgnoreCase("todo")) {
                temp += userInput.get(i) + " ";
            } else {
                break;
            }
        }
        return temp;
    }

    /**
     * Identifies the start of String representation of date of Event
     * by finding the "/at" expression.
     *
     * @param userInput the String array representation of the input by user
     * @return the String representation of the date by passing it to method
     * getDate which requires a starting index as an argument
     * @throws DukeException thrown when encountering a String Array without
     * the presence of "/at" expression, which is not a valid input for Event
     */
    public String getEventDay(ArrayList<String> userInput) throws DukeException {
        for (int i = 2; i < userInput.size(); i++) {
            if (userInput.get(i).equals("/at")) {
                if (i + 1 >= userInput.size()) {
                    throw new DukeException("Oh no, date is missing :(");
                } else {
                    return getDate(userInput, i + 1);
                }
            }
        }
        throw new DukeException("event");
    }

    /**
     * Identifies the start of String representation of date for Deadline
     * by finding the "/by" expression.
     *
     * @param userInput the String array representation of the input by user
     * @return the String representation of the date by passing it to method
     * getDate which requires a starting index as an argument
     * @throws DukeException thrown when encountering a String Array without
     * the presence of "/by" expression, which is not a valid input for Deadline
     */
    public String getDeadline(ArrayList<String> userInput) throws DukeException {
        for (int i = 2; i < userInput.size(); i++) {
            if (userInput.get(i).equals("/by")) {
                if (i + 1 >= userInput.size()) {
                    throw new DukeException("Uh oh, deadline is missing :(");
                } else {
                    return getDate(userInput, i + 1);
                }
            }
        }
        throw new DukeException("deadline");
    }

    /**
     * Creates a String representation of date by
     * concatenating a String from the starting index to the last index.
     *
     * @param userInput the String array representation of the input by user
     * @param start the starting index
     * @return String representation of date as input by user
     */
    public String getDate(ArrayList<String> userInput, int start) {
        String temp = "";
        for (int i = start; i < userInput.size(); i++) {
            if (i + 1 < userInput.size()) {
                temp += userInput.get(i) + " ";
            } else {
                temp += userInput.get(i);
            }
        }
        return temp;
    }

    /**
     * Adds tasks to the stored list of tasks.
     *
     * @param task the task to be added
     */
    public String addTask(Task task) {
        assert (task instanceof DeadLineTask) || (
                task instanceof EventTask) || (task instanceof ToDoTask) : "incorrect task created";
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        return "     Successfully added:\n" + "     " + task.getType() + task.getStatus() + " " + task.getDescription();
    }

    /**
     * Displays the entire list of tasks sequentially.
     */
    public String getList() {
        if (tasks != null && !tasks.isEmpty()) {
            String temp = "     The current list has these items:\n";
            for (int i = 0; i < tasks.size(); i++) {
                temp += "     " + (i + 1) + "." + tasks.get(i).getType() + tasks.get(i).getStatus() + " "
                        + tasks.get(i).getDescription() + "\n";
            }
            return temp + "\n" + "     There are " + tasks.size() + " task(s) now, keep up!";
        } else {
            return "     There are no items in your list, keep adding them!";
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param index the index of task to be deleted
     */
    public String deleteTask(int index) {
        Task task = tasks.remove(index);
        return "     Noted, the following task has been deleted: \n"
                + "     " + task.getType() + task.getStatus() + " " + task.getDescription() + "\n"
                + "     Nice! there are " + tasks.size() + " task(s) left.";
    }

    /**
     * Checks if any tasks are due on a particular day.
     *
     * @param date the date which user wants to check
     */
    public ArrayList<Task> findTasksDue(LocalDate date) {
        ArrayList<Task> excludeIrrelevantTasks = (ArrayList<Task>) tasks.stream()
                .filter(task -> task.getLocalDate() != null).collect(Collectors.toList());
        return (ArrayList<Task>) excludeIrrelevantTasks.stream().filter(
            task -> task.getLocalDate().equals(date)).collect(Collectors.toList());
    }

    /**
     * Finds tasks according to keywords.
     *
     * @param target the keywords as per input by user
     * @return list containing all tasks that match input keywords
     */
    public ArrayList<Task> findTask(String target) {
        return (ArrayList<Task>) tasks.stream().filter(
            task -> task.getDescription().toLowerCase().contains(target.toLowerCase())).collect(Collectors.toList());
    }

    /**
     * Changes the date of a task to another, specific date.
     * @param localDate date to change to
     * @param localTime time to change to
     * @param index index of task in stored list
     * @return result of reschedule attempt, successful or otherwise
     */
    public String reschedule(LocalDate localDate, LocalTime localTime, int index) {
        if (index < 0 || index >= tasks.size()) {
            return "     Please use a valid index!\n"
                    + "     Note: 'list' can be used to see the current tasks.";
        }
        if (tasks.get(index) instanceof ToDoTask) {
            return "     I cannot reschedule a todo Task!";
        }
        String previous = tasks.get(index).getType() + tasks.get(index).getStatus() + " "
                + tasks.get(index).getDescription();
        tasks.get(index).setLocalDate(localDate);
        tasks.get(index).setLocalTime(localTime);
        return "     Noted, the following task have been rescheduled:\n"
                + "     From " + previous + "\n"
                + "     To " + tasks.get(index).getType() + tasks.get(index).getStatus() + " "
                + tasks.get(index).getDescription();
    }
}
