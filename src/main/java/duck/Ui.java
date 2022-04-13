package duck;

import duck.exception.DuckException;
import duck.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Deals with user interactions, mostly printing messages to the console following user commands.
 */
public class Ui {
    /**
     * Returns a string containing the program welcome message.
     *
     * @return The program welcome message.
     */
    public String showWelcome() {
        return "Welcome to Duck!";
    }

    /**
     * Returns a string containing the program closing message.
     *
     * @return The program closing message.
     */
    public String showBye() {
        return "See you next time! Program closing...";
    }

    /**
     * Returns a string containing the current task list.
     *
     * @param taskList The task list to be displayed.
     * @return The task list in string representation.
     */
    public String displayList(ArrayList<Task> taskList) {
        return "Here are the tasks in your list:\n"
                + IntStream.range(0, taskList.size())
                .mapToObj(i -> (i + 1) + "." + taskList.get(i).listEntry() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Returns a string containing a message to show that the given task has been set to done.
     *
     * @param toSetDone The task that has been set to done.
     * @return String representing the task being done.
     */
    public String showDone(Task toSetDone) {
        return "Nice! I've marked this task as done:\n"
                + toSetDone.listEntry() + "\n";
    }

    /**
     * Returns a string containing a message to show that the given task has been deleted.
     *
     * @param deleted The task that has been deleted.
     * @param listLength The new length of the task list after the deletion.
     * @return String representing the task being deleted.
     */
    public String showDelete(Task deleted, int listLength) {
        return "Noted. I've removed this task:\n"
                + deleted.listEntry() + "\n"
                + "Now you have " + listLength + " tasks in the list.\n";
    }

    /**
     * Returns a string containing a schedule of tasks for the given date.
     *
     * @param taskList The task list.
     * @param desiredDate The date for which the schedule is found.
     * @return A schedule of tasks taking place/to be done on the given date.
     */
    public String displaySchedule(ArrayList<Task> taskList, LocalDate desiredDate) {
        Stream<Task> timedTasksStream = taskList.stream()
                .filter(t -> t.isTodayTask(desiredDate))
                .filter(t -> !t.isWholeDay());
        Stream<Task> wholeDayTasksStream = taskList.stream()
                .filter(t -> t.isTodayTask(desiredDate))
                .filter(Task::isWholeDay);

        return String.format("Here is the schedule for %s:\n", desiredDate.toString())
                + timedTasksStream
                .sorted(Task::compareTo)
                .map(t -> t.listEntry() + "\n")
                .collect(Collectors.joining())
                + "\n"
                + "Here are tasks without specific timing:\n"
                + wholeDayTasksStream
                .map(t -> t.listEntry() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Returns a string containing a list of tasks in the current task list that contain the given keyword.
     *
     * @param taskList The task list.
     * @param keyword The keyword to look for in the task names.
     * @return A list of tasks containing the given keyword.
     */
    public String showKeywordFind(ArrayList<Task> taskList, String keyword) {
        return "Here are the tasks with that keyword:\n"
                + IntStream.range(0, taskList.size())
                .filter(i -> taskList.get(i).containsKeyword(keyword))
                .mapToObj(i -> (i + 1) + "." + taskList.get(i).listEntry() + "\n")
                .collect(Collectors.joining());
    }

    /**
     * Returns a string containing a message to show that the given task has been added to the task list.
     *
     * @param newTask The task that has been added to the task list.
     * @param listLength The new length of the task list after the addition.
     * @return String representing the task being added.
     */
    public String showAdd(Task newTask, int listLength) {
        return "Got it. I've added this task:\n"
                + newTask.listEntry() + "\n"
                + "Now you have " + listLength + " tasks in the list.\n";
    }

    /**
     * Returns a string containing the error message of the given DuckException.
     *
     * @param e The DuckException whose error message is to be displayed.
     * @return The DuckException error message.
     */
    public String showException(DuckException e) {
        return e.getMessage();
    }
}
