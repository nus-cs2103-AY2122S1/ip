/**
 * This class handles the outputs for logging purposes, responding to the user
 *
 * @author Megan Wee Rui En
 * @version CS2103 AY21/22 Semester 1
 */

package duke.ui;

import duke.tasklist.TaskList;

import java.util.ArrayList;

public class Ui {
    private String linebreak = "~~~~~~~~~~";

    public Ui() {
        ///// Introduction of the robot every time Duke is initialised
        // The chat bot name is Notaro bc it's Not-a-ro-bot :>
        // This is the introduction of the chat bot, and includes a list of the commands for the user
        System.out.println("\nHaro, I'm Taro! Taro is short for Notaro because I'm Not-a-ro-bot!!");
        System.out.println(linebreak);
    }

    /**
     * This function prints out the error relevant to loading the file for the todo list.
     */
    public void showLoadingError() {
        System.out.println("Sorry! There was an error loading the file :(");
    }

    /**
     * This function prints a linebreak.
     */
    public void showLine() {
        System.out.println(linebreak);
    }

    /**
     * This function prints out a welcome message when a user starts using the chatbot
     */
    public void showWelcome() {
        ///// Introduction of possible commands
        System.out.println("There are three special tasks you can add: Deadline, Event and Todo");
        System.out.println("Here are some special keywords! :");
        System.out.println("bye : End our conversation :(");
        System.out.println("list : Adds stuff into your todo list :(");
        System.out.println("done [number] : Marks the item corresponding the number in the todo list as complete!");
        System.out.println("delete [number] : Deletes the item corresponding the number in the todo list");
        System.out.println("\nWhat can I do for you today? :>");
        System.out.println(linebreak);
    }

    /**
     * This function prints out a message when a 'bye' command is entered  (when the program ends).
     */
    public void showBye() {
        System.out.println("Bye bye!! It was nice meeting you!");
        System.out.println(linebreak);
    }

    /**
     * This function logs the addition of a Task.
     */
    public void showAddition(String taskType, String command) {
        System.out.println("added: " + command);
    }

    /**
     * This function logs the removal of a Task.
     */
    public void showRemoval(String taskRemoved, int taskListSize) {
        System.out.println("Oki! I have removed this task:");
        System.out.println(taskListSize + " more tasks to go!");
    }

    /**
     * This function prints out the taskList in a neat manner.
     */
    public void showList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Yay! Nothing on your list right now :>");
        } else {
            for (int count = 0; count < taskList.size(); count++) {
                System.out.println((count + 1) + ". " + taskList.get(count).toString());
            }
        }
    }

    /**
     * This function logs the completion of a Task.
     */
    public void showCompletion(String s) {
        System.out.println("Yay good job!");
        System.out.println(s + " has been completed");
    }

    public void showSearch(ArrayList<String> wordList) {

        if (wordList.isEmpty()) {
            System.out.println("You don't have anything in your todo list with that word!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int i = 1;
            for (String s : wordList) {
                System.out.println(i + ". " + s);
                i++;
            }
            System.out.println("Good luck!");
        }
    }
}
