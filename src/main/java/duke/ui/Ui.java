/**
 * This class handles the outputs for logging purposes and responding to the user.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.ui;

import duke.tasklist.TaskList;
import duke.tasks.Task;

import java.util.ArrayList;

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
    public String showWelcome1() {
        return "Haro, I'm Taro! Taro is short for Notaro because I'm Not-a-ro-bot!!\n"
                + "There are three special tasks you can add:\nDeadline, Event and Todo\n"
                + "Here are some special keywords! :";
    }

    /**
     * Prints out a welcome message when a user starts using the chatbot, and prints the possible order
     *
     * @return The welcome message.
     */
    public String showWelcome2() {
        return "bye : End our conversation :(\n"
                + "list : Adds stuff into your todo list :(\n"
                + "done [number] : Marks the item corresponding the number in the todo list as complete!\n"
                + "find [word] : Searches the todo list for the word inputted and shows all matches\n"
                + "delete [number] : Deletes the item corresponding the number in the todo list";
    }

    /**
     * Prints out a welcome message when a user starts using the chatbot, and prints the possible order
     *
     * @return The welcome message.
     */
    public String showWelcome3() {
        return "What can I do for you today? :>";
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
     * @param taskType The type of task which has been added (Deadline, Todo or Event).
     * @param command The command the user inputted.
     * @return The bot's output for the done command.
     */
    public String showAddition(String taskType, String command) {
        return "added: " + command;
    }

    /**
     * Logs the removal of a Task.
     *
     * @param taskRemoved The task removed by the user.
     * @param taskListSize The size of the current list of tasks
     * @return The bot's output for the delete command.
     */
    public String showRemoval(String taskRemoved, int taskListSize) {
            return String.format("Oki! I have removed this task: %s \n %d more tasks to go!",
                    taskRemoved,
                    taskListSize); //TODO linebreak
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
            return tasksString.toString();
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
            return String.format("Here are the matching tasks in your list: %s Good luck!", words.toString());
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
