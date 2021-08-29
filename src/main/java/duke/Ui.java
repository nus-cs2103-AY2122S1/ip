package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with interactions with user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructor.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Welcomes the user when Duke starts up.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads the input by the user.
     *
     * @return input.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays the list of tasks to user.
     *
     * @param taskList list of tasks.
     */
    public void list(TaskList taskList) {
        ArrayList<Task> savedInputs = taskList.getTasks();
        StringBuilder outputList = new StringBuilder();
        outputList.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= savedInputs.size(); i++) {
            outputList.append(i + "." + savedInputs.get(i - 1).toString() + "\n");
        }
        System.out.println(outputList);
    }

    /**
     * Displays the message after a task has been marked as done.
     *
     * @param taskList list of tasks.
     * @param donePos position of task to mark as done.
     */
    public void done(TaskList taskList, int donePos) {
        System.out.println("Nice! I've marked this task as done:\n  "
                + taskList.getTasks().get(donePos - 1).toString());
    }

    /**
     * Displays the message after a task has been deleted.
     *
     * @param taskList list of tasks.
     * @param deletePos position of task to delete.
     */
    public void delete(TaskList taskList, int deletePos) {
        System.out.println("Noted. I've removed this task:\n  " + taskList.getTasks().get(deletePos - 1).toString());
        System.out.println("Now you have " + (taskList.getTasks().size() - 1) + " tasks in the list.");
    }

    /**
     * Displays the list of tasks with dates that match the date given.
     *
     * @param taskList list of tasks.
     * @param localDate date to compare to.
     */
    public void checkDate(TaskList taskList, LocalDate localDate) {
        ArrayList<Task> matchingDates = taskList.checkDate(localDate);

        String output = "";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        System.out.println(output);
    }

    /**
     * Displays the message to the user after an event task has been added.
     *
     * @param taskList list of tasks.
     * @param event to add.
     */
    public void addEvent(TaskList taskList, Event event) {
        System.out.println("Got it. I've added this task:\n  " + event);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    /**
     * Displays the message to the user after a deadline task has been added.
     *
     * @param taskList list of tasks.
     * @param deadline to add.
     */
    public void addDeadline(TaskList taskList, Deadline deadline) {
        System.out.println("Got it. I've added this task:\n  " + deadline);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    /**
     * Displays the message to the user after a todo task has been added.
     *
     * @param taskList list of tasks.
     * @param todo to add.
     */
    public void addTodo(TaskList taskList, Todo todo) {
        System.out.println("Got it. I've added this task:\n  " + todo);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints error message to the screen.
     *
     * @param message to print.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints error message when user enters an invalid input.
     *
     * @throws DukeException
     */
    public void invalidUserInput() throws DukeException {
        throw new DukeException("Oops! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays the exit message to user
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the tasks with descriptions that contain the given word.
     *
     * @param taskList current list of all tasks.
     * @param word given by user.
     */
    public void find(TaskList taskList, String word) {
        ArrayList<Task> matchingDates = taskList.find(word);

        String output = "Here are the matching tasks in your list: \n";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        System.out.println(output);
    }
}
