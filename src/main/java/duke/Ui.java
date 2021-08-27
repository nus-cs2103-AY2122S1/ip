package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
     */
    public void welcomeMessage() {
        lineProducer();
        System.out.println("    Hello I'm your friendly task-planning chatbot Duke!" +
                indentationAdder() + "To enter a deadline, please type in this format: 'deadline {title of item} /by d/mm/yyyy hh:mm'" + indentationAdder() +
                "To enter an event, please type in this format: 'event {title of item} /on d/mm/yyyy hh:mm''"+ indentationAdder() +
                "To enter a todo, please type in this format: 'todo {title of item}'" + indentationAdder() +
                "type help to know more commands available in this bot!");
        lineProducer();
    }

    /**
     * The message that is shown when the user first uses Duke since a local path has not been
     * created.
     */
    public void showLoadingError() {
        System.out.println("Creating new folder since path does not exist!");
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
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * A method that prints the final goodbye message when the program exits.
     *
     * @return A boolean that states false as the program exits.
     */
    public boolean byeMessage() {
        System.out.println("    Bye! Please visit me again!");
        return false;
    }

    /**
     * A message that is only shown when the user completes all his tasks.
     */
    public void printCompleted() {
        System.out.println("    " + "Congratulations! You've completed all your tasks!");
    }

    /**
     * A message that is printed when the user marks one of his tasks as 'done'.
     *
     * @param taskToChange The task that has been changed to 'done'.
     */
    public void markAsDone(Task taskToChange) {
        System.out.println("    Great job! I've marked the following as done" +
                indentationAdder() + taskToChange);
    }

    /**
     * A message that is printed when a task has been deleted from the list.
     *
     * @param toDelete The task that has been deleted.
     */
    public void markAsDeleted(Task toDelete) {
        System.out.println("    Note: I've removed the following task from your list:" +
                indentationAdder() + toDelete);
    }

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
     */
    public void addingTask(int totalTasks, String description, String time, String type) {
        Task toAdd;
        if (type.equals("D")) {
            toAdd = new Deadline(description, dateFormatting(time));
        } else if (type.equals("E")) {
            toAdd = new Event(description, dateFormatting(time));
        } else {
            toAdd = new Todo(description);
        }
        System.out.println("    Understood! The following task has been added:" + indentationAdder() + " " + toAdd);
        System.out.println("    You have " + totalTasks + " " + (totalTasks == 1? "task" : "tasks" ) + " in your current list");
    }

    /**
     * A message printed out containing the list of commands that are not to add a deadline, event or to do
     * that the user may not know exists and their functions.
     */
    public void helpMessage() {
        System.out.println("    The following commands are compatible with our task-planning chatbot!" + indentationAdder() +
                "list:" + indentationAdder() + "provides a list of items in your task list." + indentationAdder() +
                "done {number}:" + indentationAdder() + "ticks the task with that number as done!" + indentationAdder() +
                "delete {number}:" + indentationAdder() + "deletes the task with that number off the list." + indentationAdder() +
                "bye:" + indentationAdder() + "ends the program.");
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
