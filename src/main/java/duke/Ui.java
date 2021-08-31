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
    public String showWelcome() {

        return "Hello! I'm Duke\nWhat can I do for you?";
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
    public String list(TaskList taskList) {
        ArrayList<Task> savedInputs = taskList.getTasks();
        StringBuilder outputList = new StringBuilder();
        outputList.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= savedInputs.size(); i++) {
            outputList.append(i + "." + savedInputs.get(i - 1).toString() + "\n");
        }
        return outputList.toString();
    }

    /**
     * Displays the message after a task has been marked as done.
     *
     * @param taskList list of tasks.
     * @param donePos position of task to mark as done.
     */
    public String done(TaskList taskList, int donePos) {
        return "Nice! I've marked this task as done:\n  " + taskList.getTasks().get(donePos - 1).toString();
    }

    /**
     * Displays the message after a task has been deleted.
     *
     * @param taskList list of tasks.
     * @param deletePos position of task to delete.
     */
    public String delete(TaskList taskList, int deletePos) {
        return "Noted. I've removed this task:\n  " + taskList.getTasks().get(deletePos - 1).toString()
                + "\nNow you have " + (taskList.getTasks().size() - 1) + " tasks in the list.";
    }

    /**
     * Displays the list of tasks with dates that match the date given.
     *
     * @param taskList list of tasks.
     * @param localDate date to compare to.
     */
    public String checkDate(TaskList taskList, LocalDate localDate) {
        ArrayList<Task> matchingDates = taskList.checkDate(localDate);

        String output = "";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        return output;
    }

    /**
     * Displays the message to the user after an event task has been added.
     *
     * @param taskList list of tasks.
     * @param event to add.
     */
    public String addEvent(TaskList taskList, Event event) {
        return "Got it. I've added this task:\n  " + event + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
    }

    /**
     * Displays the message to the user after a deadline task has been added.
     *
     * @param taskList list of tasks.
     * @param deadline to add.
     */
    public String addDeadline(TaskList taskList, Deadline deadline) {
        return "Got it. I've added this task:\n  " + deadline + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
    }

    /**
     * Displays the message to the user after a todo task has been added.
     *
     * @param taskList list of tasks.
     * @param todo to add.
     */
    public String addTodo(TaskList taskList, Todo todo) {
        return "Got it. I've added this task:\n  " + todo + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
    }

    /**
     * Prints error message to the screen.
     *
     * @param message to print.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints error message when user enters an invalid input.
     *
     * @throws DukeException
     */
    public String invalidUserInput() throws DukeException {
        return new DukeException("Oops! I'm sorry, but I don't know what that means :-(").toString();
    }

    /**
     * Displays the exit message to user
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the tasks with descriptions that contain the given word.
     *
     * @param taskList current list of all tasks.
     * @param word given by user.
     */
    public String find(TaskList taskList, String word) {
        ArrayList<Task> matchingDates = taskList.find(word);

        String output = "Here are the matching tasks in your list: \n";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        return output;
    }

    public void printToConsole(String response) {
        System.out.println(response);
    }
}
