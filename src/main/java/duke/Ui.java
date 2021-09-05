package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Ui deals with the user interactions where it outputs a message based on what
 * which Command was given by the Parser.
 */
public class Ui {

    public Ui() {

    }
    /**
     * Produces a consistent line for everything the bot says.
     *
     * @return A line of hyphens.
     */
    public String lineProducer() {
        String line = "   ------------------------------------------------------------------";
        System.out.println(line);
        return line;
    }

    /**
     * Does a consistent indentation for the line where the bot outputs a message.
     *
     * @return An extra line and 4 spaces.
     */
    public String indentationAdder() {
        return "\n    ";
    }

    /**
     * The message that first greets the user when the program starts.
     *
     * @return Welcome message.
     */
    public String welcomeMessage() {
        String toWelcome = ("Hello I'm your friendly task-planning chatbot Duke!\n"
                + "To enter a deadline, please type in this format: 'deadline {title of item} "
                + "/by d/mm/yyyy hh:mm'\n"
                + "To enter an event, please type in this format: 'event {title of item} /on d/mm/yyyy hh:mm'\n"
                + "To enter a todo, please type in this format: 'todo {title of item}'\n"
                + "type help to know more commands available in this bot!");
        System.out.println(toWelcome);
        return toWelcome;
    }

    /**
     * The message that is shown when the user first uses Duke since a local path has not been
     * created.
     *
     * @return Loading Error message.
     */
    public String showLoadingError() {
        String loadingError = ("Creating new folder since path does not exist!");
        System.out.println(loadingError);
        return loadingError;
    }

    /**
     * This method provides an avenue for the user to input their command so that the program
     * can carry out different actions.
     *
     * @return a String that consists of everything the user inputted.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Outputs the error message given by the exception thrown.
     *
     * @param errorMessage The message that is stored in the error thrown.
     * @return The error message.
     */
    public String showError(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }

    /**
     * A method that prints the final goodbye message when the program exits.
     *
     * @return The goodbye message.
     */
    public String byeMessage() {
        String goodbye = "Bye! Please visit me again!";
        return goodbye;
    }

    /**
     * A message that is only shown when the user completes all his tasks.
     *
     * @return String that shows the completion message.
     */
    public String printCompleted() {
        String completionMessage = ("Congratulations! You've completed all your tasks!");
        System.out.println(completionMessage);
        return completionMessage;
    }

    /**
     * A message that is printed when the user marks one of his tasks as 'done'.
     *
     * @param taskToChange The task that has been changed to 'done'.
     * @return Message that tells us it is marked as done.
     */
    public String markAsDone(Task taskToChange) {
        String markedAsDone = ("Great job! I've marked the following as done:\n"
                + taskToChange);
        System.out.println(markedAsDone);
        return markedAsDone;
    }

    /**
     * A message that is printed when a task has been deleted from the list.
     *
     * @param toDelete The task that has been deleted.
     * @return The deleted message.
     */
    public String markAsDeleted(Task toDelete) {
        String deletedMessage = ("Note: I've removed the following task from your list:\n"
                + toDelete);
        System.out.println(deletedMessage);
        return deletedMessage;
    }

    /**
     * Formats the date and time to what the program wants.
     *
     * @param stringDate The user inputted date and time.
     * @return A LocalDateTime object that contains information that the user inputted.
     */
    public LocalDateTime dateFormatting(String stringDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(stringDate, dateTimeFormatter);
        return localDate;
    }

    /**
     * A message that is printed that states what task has been added to the list as well as the current
     * number of tasks that are on the list of tasks.
     *
     * @param totalTasks The total number of tasks in the list.
     * @param description The description of the task.
     * @param time The time which the task is to be completed(Deadline) or to begin(Event).
     * @param type The type of task that has been added (Deadline, Event or To do).
     * @return The message that shows after adding task.
     */
    public String addingTask(int totalTasks, String description, String time, String type) {
        Task toAdd;
        if (type.equals("D")) {
            toAdd = new Deadline(description, dateFormatting(time));
        } else if (type.equals("E")) {
            toAdd = new Event(description, dateFormatting(time));
        } else {
            toAdd = new Todo(description);
        }
        String addedMessage = ("Understood! The following task has been added:\n" + " " + toAdd
                + "\nYou have " + totalTasks + " " + (totalTasks == 1 ? "task" : "tasks")
                + " in your current list");
        System.out.println(addedMessage);
        return addedMessage;
    }

    /**
     * A message printed out containing the list of commands that are not to add a deadline, event or to do
     * that the user may not know exists and their functions.
     *
     * @return The helpful message.
     */
    public String helpMessage() {
        String helpfulMessage = ("The following commands are compatible with our task-planning chatbot!\n"
                + "list:" + "\nprovides a list of items in your task list.\n"
                + "done {number}:" + "\nticks the task with that number as done!\n"
                + "delete {number}:\n" + "deletes the task with that number off the list.\n"
                + "bye:\n" + "ends the program.");
        System.out.println(helpfulMessage);
        return helpfulMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ui)) {
            return false;
        }
        return true;
    }
}
