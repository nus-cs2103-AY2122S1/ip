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
    private final String LINEBREAK = "~~~~~~~~~~";

    public Ui() {
        ///// Introduction of the robot every time Duke is initialised
        System.out.println("\nHaro, I'm Taro! Taro is short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println(LINEBREAK);
    }

    /**
     * Prints a linebreak.
     */
    public void showLine() {
        System.out.println(LINEBREAK);
    }

    /**
     * Prints out a welcome message when a user starts using the chatbot, and prints the possible order
     */
    public void showWelcome() {
        System.out.println(LINEBREAK);
        System.out.println("There are three special tasks you can add: Deadline, Event and Todo");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list :(");
        System.out.println("done [number] : Marks the item corresponding the number in the todo list as complete!");
        System.out.println("find [word] : Searches the todo list for the word inputted and shows all matches");
        System.out.println("delete [number] : Deletes the item corresponding the number in the todo list");
        System.out.println("\nWhat can I do for you today? :>");
        System.out.println(LINEBREAK);
    }

    /**
     * Prints out a message when a 'bye' command is entered  (when the program ends).
     */
    public void showBye() {
        System.out.println("Bye bye!! It was nice meeting you!");
        System.out.println(LINEBREAK);
    }

    /**
     * Logs the addition of a Task.
     *
     * @param taskType The type of task which has been added (Deadline, Todo or Event).
     * @param command The command the user inputted.
     */
    public void showAddition(String taskType, String command) {
        System.out.println("added: " + command);
    }

    /**
     * Logs the removal of a Task.
     *
     * @param taskRemoved The task removed by the user.
     * @param taskListSize The size of the current list of tasks
     */
    public void showRemoval(String taskRemoved, int taskListSize) {
        System.out.println("Oki! I have removed this task:");
        System.out.println(taskRemoved);
        System.out.println(taskListSize + " more tasks to go!");
    }

    /**
     * Prints out the taskList in a neat manner.
     *
     * @param tasks The current list of tasks.
     */
    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Yay! Nothing on your list right now :>");
        } else {
            for (int count = 0; count < tasks.size(); count++) {
                System.out.println((count + 1) + ". " + tasks.get(count).toString());
            }
        }
    }

    /**
     * Logs the completion of a Task.
     *
     * @param command The task which has been completed.
     */
    public void showCompletion(String command) {
        System.out.println("Yay good job!");
        System.out.println(command + " has been completed");
    }

    /**
     * Logs the completion of a Task.
     *
     * @param wordList The list of commands with matches to the searched word.
     */
    public void showSearch(ArrayList<String> wordList) {

        if (wordList.isEmpty()) {
            System.out.println("You don't have anything in your todo list with that word!");

        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (String s : wordList) {
                System.out.println(s);
            }
            System.out.println("Good luck!");
        }
    }
}
