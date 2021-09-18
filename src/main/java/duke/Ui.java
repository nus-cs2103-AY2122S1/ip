package duke;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Handles interactions with user through message outputs.
 */
public class Ui {

    /**
     * Returns error message.
     *
     * @param error message
     * @return error message
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns help message showing all the possible commands user can use and how.
     *
     * @return help message
     */
    public String showHelpMessage() {

        String helpMessage = String.join("\n",
                "The available commands are list, bye, find, delete, done, todo, event, deadline.",
                "Enter list to get a list of all the tasks you have.",
                "Enter bye to exit the program.",
                "Enter find in the format: find  <search term> to get a list of all tasks that contain that term.",
                "Enter delete in the format: delete  <task number> to delete the task from the list.",
                "Enter done in the format: done  <task number> to mark the task done in the list.",
                "Enter todo in the format: todo  <description> to enter a Todo task.",
                "Enter event in the format: event  <description>  <date and time> to enter an Event task.",
                "Enter deadline in the format: deadline  <deadline>  <date and time> to enter a Deadline task."
        );

        return helpMessage;
    }

    /**
     * Returns message showing the task marked as done.
     *
     * @param doneTask the task marked as done
     * @return message acknowledging the task is marked as done
     */
    public String showDoneMessage(Task doneTask) {
        return String.format("Nice! I've marked this task as done: %n%s", doneTask);
    }

    /**
     * Returns message showing the task that was deleted.
     *
     * @param deletedTask the task that was deleted
     * @return message acknowledging the task as deleted
     */
    public String showDeletedMessage(Task deletedTask) {
        return String.format("Noted. I've removed this task: %n%s", deletedTask);
    }

    /**
     * Returns message showing the task that was added.
     *
     * @param addedTask the task that was added
     * @return message acknowledging the task as added
     */
    public String showAddMessage(Task addedTask) {
        return String.format("Got it. I've added this task: %n%s", addedTask);
    }

    /**
     * Returns message showing how many tasks are in the task list
     *
     * @param size of the task list
     * @return message showing how many tasks are in the task list
     */
    public String showTaskListSize(int size) {
        if (size == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return String.format("Now you have %d tasks in the list.", size);
        }
    }

    /**
     * Returns goodbye when program ends.
     *
     * @return goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns all the tasks that match the user's search term.
     *
     * @param matchingTaskList task list of tasks that matches the user's search term
     * @return message showing all the tasks in the matching task list
     */
    public String showMatchingTasks(ArrayList<Task> matchingTaskList) {
        if (matchingTaskList.size() == 0) {
            return "There are no tasks containing that term in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");

            IntStream.range(0, matchingTaskList.size())
                    .forEach(i -> sb.append(String.format("%d.%s%n", i + 1, matchingTaskList.get(i))));

            return sb.toString();
        }
    }

    /**
     * Returns all the tasks that the user has.
     *
     * @param taskList list of tasks
     * @return message showing all the tasks the user has
     */
    public String showTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "There are no tasks in your list!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are tasks in your list:\n");

            IntStream.range(0, taskList.size())
                    .forEach(i -> sb.append(String.format("%d.%s%n", i + 1, taskList.get(i))));

            return sb.toString();
        }
    }
}
