package duke;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.errors.DukeException;
import duke.task.Task;

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
     * Returns a message and the list of tasks in task list.
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
     * Returns the message to notify the user after a task has been marked as done.
     *
     * @param taskList list of tasks.
     * @param donePos position of task to mark as done.
     */
    public String done(TaskList taskList, int donePos) {
        return "Nice! I've marked this task as done:\n  " + taskList.getTasks().get(donePos - 1).toString();
    }

    /**
     * Returns the message to notify the user after a task has been deleted.
     *
     * @param taskList list of tasks.
     * @param deletePos position of task to delete.
     */
    public String delete(TaskList taskList, int deletePos) {
        return "Noted. I've removed this task:\n  " + taskList.getTasks().get(deletePos - 1).toString()
                + "\nNow you have " + (taskList.getTasks().size() - 1) + " tasks in the list.";
    }

    /**
     * Returns a message with the list of tasks with dates that match the date given.
     *
     * @param taskList list of tasks.
     * @param localDate date to compare to.
     */
    public String checkDate(TaskList taskList, LocalDate localDate) {
        ArrayList<Task> matchingDates = taskList.checkDate(localDate);

        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks on " + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ": \n");
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output.append("  " + i + ". " + matchingDates.get(i - 1).toString() + "\n");
        }
        return output.toString();
    }

    /**
     * Returns a message to notify the user after a task has been added.
     *
     * @param taskList list of tasks.
     * @param task to add.
     */
    public String add(TaskList taskList, Task task) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + taskList.getTasks().size()
                + " tasks in the list.";
    }

    /**
     * Returns an error message.
     *
     * @param message to print.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns an error message when user enters an invalid input.
     *
     * @throws DukeException
     */
    public String invalidUserInput() throws DukeException {
        throw new DukeException("Oops! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Displays the exit message to user
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message with a list of the tasks with descriptions that contain the given word.
     *
     * @param taskList current list of all tasks.
     * @param word given by user.
     */
    public String find(TaskList taskList, String word) {
        ArrayList<Task> matchingDates = taskList.find(word);

        StringBuilder output = new StringBuilder();
        output.append("Here are the matching tasks in your list: \n");
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output.append(i + ". " + matchingDates.get(i - 1).toString() + "\n");
        }
        return output.toString();
    }

    /**
     * Returns a message to notify the user that hard disk and task list have been cleared.
     *
     * @return String message
     */
    public String clear() {
        return "Noted, I have cleared your list and hard disk.";
    }

    /**
     * Returns a message and the list of archive names to user.
     *
     * @return String message and list
     */
    public String listArchives() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are your archives: \n");

        File[] file = new File("src/archive").listFiles();
        int counter = 1;
        for (File f : file) {
            if (f.isFile()) {
                stringBuilder.append(" " + counter + ". " + f.getName() + "\n");
                counter++;
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Returns a message to notify the user that the archive file has been deleted.
     *
     * @param fileName of archive file.
     * @return message.
     */
    public String deleteArchive(String fileName) {
        return "Noted, " + fileName + " Archive has been deleted.";
    }

    /**
     * Returns a message to notify the user that given archive has loaded in.
     *
     * @param fileName of archive file.
     * @return message
     */
    public String loadArchive(String fileName) {
        return "You have loaded " + fileName + " Archive.";
    }

    /**
     * Returns a message to notify the user that a new archive file has been created and current hard disk is saved
     * to the archive file.
     *
     * @param fileName of archive file.
     * @return message
     */
    public String newArchive(String fileName) {
        return "Got it! A new " + fileName + " Archive has been created.";
    }

    /**
     * Returns a message to display all available commands that he user can use.
     *
     * @return message with all available commands.
     */
    public String help() {
        String helpMessage = "(Note – date format: yyyy/mm/dd)\n"
                + "Commands you can run:\n"
                + " * todo (DESC) – creates a todo task\n"
                + " * deadline (DESC) /by (DATE) – creates a deadline task\n"
                + " * event (DESC) /at (DATE) – creates an event task\n"
                + " * list – lists all tasks\n"
                + " * done (INDEX) – mark a task as done \n"
                + " * delete (INDEX) – deletes a task\n"
                + " * find (KEYWORD) – finds all tasks that contain the keyword\n"
                + " * check (DATE) – finds all tasks with matching date\n"
                + " * clear – clears task list and hard disk\n"
                + " * archive /saveAs (FILENAME) – creates new archive file\n"
                + " * archive /load (FILENAME) – loads an archive file\n"
                + " * archive /delete (FILENAME) – deletes an archive file\n"
                + " * archive /list – lists all archive files";

        return helpMessage;
    }

    /**
     * Prints Duke's response to the console.
     *
     * @param response to print.
     */
    public void printToConsole(String response) {
        System.out.println(response);
    }
}
