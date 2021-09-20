/**
 * This class handles the outputs for logging purposes and responding to the user.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.ui;

import java.util.ArrayList;

import duke.tasklist.TaskList;
import duke.tasks.Task;

public class Ui {

    /**
     * Constructs an Ui object, and prints a message when it does.
     */
    public Ui() {}

    /**
     * Prints out a welcome message when a user starts using the chatbot, and prints the possible order
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Haro, I'm Taro! Taro is short for Notaro because I'm Not-a-ro-bot!! \n"
                + "What can I do for you today? :>";
    }

    /**
     * Prints out a message when a 'bye' command is entered  (when the program ends).
     *
     * @return The bot's output for the bye command.
     */
    public String showBye() {
        return "Bye bye!! It was nice meeting you!";
    }

    /**
     * Logs the addition of a Task.
     *
     * @param taskAdded The string representation of the task which has been added.
     * @param taskListSize The size of the current list of tasks.
     * @return The bot's output for the done command.
     */
    public String showAddition(String taskAdded, int taskListSize) {
        return String.format("Okay! I've added the following: \n%s\n" + "%d more tasks to go!",
                taskAdded, taskListSize);
    }

    /**
     * Logs the removal of a Task.
     *
     * @param taskRemoved The task removed by the user.
     * @param taskListSize The size of the current list of tasks
     * @return The bot's output for the delete command.
     */
    public String showDeletion(String taskRemoved, int taskListSize) {
        return String.format("Oki! I have removed this task:\n%s\n%d more tasks to go!",
                taskRemoved,
                taskListSize);
    }

    /**
     * Prints out the taskList in a neat manner.
     *
     * @param tasks The current list of tasks.
     * @return The bot's output for the list command.
     */
    public String showList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "Yay! Nothing on your list right now :>";
        } else {
            StringBuilder tasksString = new StringBuilder();
            for (int count = 0; count < tasks.size(); count++) {
                tasksString.append((count + 1) + ". " + tasks.get(count).toString() + "\n");
            }
            return "Here are your tasks:\n" + tasksString.toString();
        }
    }

    /**
     * Logs the completion of a Task.
     *
     * @param command The task which has been completed.
     * @return The bot's output for the done command if a task has been completed.
     */
    public String showCompletion(String command) {
        return String.format("Yay good job! %s has been completed", command);
    }

    /**
     * Logs the completion of a Task.
     *
     * @param wordList The list of commands with matches to the searched word.
     * @return The bot's output for the find command.
     */
    public String showSearch(ArrayList<String> wordList) {

        if (wordList.isEmpty()) {
            return "You don't have anything in your todo list with that word!";

        } else {
            StringBuilder words = new StringBuilder();
            for (String s : wordList) {
                words.append(s).append("\n");
            }
            return String.format("Here are the matching tasks in your list:\n%sGood luck!", words.toString());
        }
    }

    /**
     * Logs the completion of a Task.
     *
     * @param tagInfo The information of the tag.
     * @param task The task that the added tag is associated with.
     * @return The bot's output for the tag command.
     */
    public String showTag(String tagInfo, Task task) {
        return String.format("Oki! The tag %s has been added. Here is the modified task!\n %s",
                tagInfo, task.toString());
    }
}
