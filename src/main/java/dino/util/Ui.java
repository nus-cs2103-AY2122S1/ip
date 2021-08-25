package dino.util;

import java.util.Scanner;
import dino.task.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {

    private final Scanner sc;

    /**
     * Constructs an Ui interface by initializing a scanner object
     * which reads input from the user
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input, if exists
     */
    public String readNextLine() {
        return this.sc.nextLine();
    }

    /**
     * Greets the user upon initialization of a Dino object
     */
    public void greeting() {
        System.out.println("Hello! I'm dino, your cute dinosaur bot.\n"
                + "Anything I can do for you?");
    }

    /**
     * Saves any changes to the task list to the storage
     * Greets the user with a farewell message
     * Closes the scanner
     *
     * @param storage the local storage for task list
     * @param taskList the current version of task list
     */
    public void processExit(Storage storage, TaskList taskList) {
        storage.saveToStorage(taskList.getTaskList());
        System.out.println("Goodbye~ \n"
                + "Your cute Dino is always around you :D");
        this.sc.close();
    }

}
