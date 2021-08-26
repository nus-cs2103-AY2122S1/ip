package duke.ui;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.utils.Constants;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * The Ui class deals with all the interactions with the user. Most of the methods prefixed with 'display' print out
 * the output obtained from their respective Command functions
 */
public class Ui {

    private Scanner sc;

    /**
     * Public constructor which initialises the scanner
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void init() {
        System.out.println(Constants.logo + Constants.HELLO + Constants.LINE);
    }

    /**
     * Returns a string with the contents of the scanner. Throws an exception if the input is missing.
     * @return string with contents of the scanner.
     */
    public String read() {
        try {
            if (sc.hasNext()) {
                return sc.nextLine();
            } else {
                return "bye";
            }
        } catch (DukeException ex) {
            throw new DukeException("Missing Input!");
        }
    }


    public void displayBye() {
        System.out.println(Constants.BYE);
        System.exit(0);
    }

    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        list.print();
    }

    public void displayDone(String taskDetails) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskDetails);
    }


    public void displayDelete(String taskDetails, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskDetails);
        list.printRemainingTasks();

    }

    public void displayTasksOn(LocalDate date, TaskList list) {
        if (!list.isEmpty()) {
            list.print();
        } else {
            System.out.println("No tasks are due on " + date + "!");
        }
    }

    public void displayLoadingError(Exception ex) {
        System.out.println(ex.toString());
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public void displayLine() {
        System.out.println(Constants.LINE);
    }

    public void displayAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        taskList.printRemainingTasks();
    }

}
