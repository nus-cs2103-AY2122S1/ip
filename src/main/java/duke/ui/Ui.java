/**
 * This class handles the outputs for logging purposes and responding to the user.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.ui;

import duke.tasklist.TaskList;
import java.util.ArrayList;

public class Ui {

    /**
     * Constructs an Ui object, and prints a message when it does.
     */
    public Ui() {
        ///// Introduction of the robot every time Duke is initialised
        System.out.println("\nHaro, I'm Taro! Taro is short for Notaro because I'm Not-a-ro-bot!!");
    }

    /**
     * Prints out a welcome message when a user starts using the chatbot, and prints the possible order
     */
    public void showWelcome() {
        System.out.println("There are three special tasks you can add: Deadline, Event and Todo");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list :(");
        System.out.println("done [number] : Marks the item corresponding the number in the todo list as complete!");
        System.out.println("find [word] : Searches the todo list for the word inputted and shows all matches");
        System.out.println("delete [number] : Deletes the item corresponding the number in the todo list");
        System.out.println("\nWhat can I do for you today? :>");
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
                tasksString.append((count + 1) + ". " + tasks.get(count).toString());
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
}
