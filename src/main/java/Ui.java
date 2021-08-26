import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {

    public Ui() {

    }
    /**
     * Produces a consistent line for everything the bot says.
     *
     * @return a line of hyphens.
     */
    public String lineProducer() {
        String line = "   ------------------------------------------------------------------";
        System.out.println(line);
        return line;
    }

    /**
     * Does a consistent indentation for the line where the bot outputs a message.
     *
     * @return an extra line and 4 spaces.
     */
    public String indentationAdder() {
        return "\n    ";
    }

    public void welcomeMessage() {
        lineProducer();
        System.out.println("    Hello I'm your friendly task-planning chatbot Duke!" +
                indentationAdder() + "To enter a deadline, please type in this format: 'deadline {title of item} /by d/mm/yyyy hh:mm'" + indentationAdder() +
                "To enter an event, please type in this format: 'event {title of item} /on d/mm/yyyy hh:mm''"+ indentationAdder() +
                "To enter a todo, please type in this format: 'todo {title of item}'" + indentationAdder() +
                "type help to know more commands available in this bot!");
        lineProducer();
    }

    public void showLoadingError() {
        System.out.println("Creating new folder since path does not exist!");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public boolean byeMessage() {
        System.out.println("    Bye! Please visit me again!");
        return false;
    }

    public void printCompleted() {
        System.out.println("    " + "Congratulations! You've completed all your tasks!");
    }

    public void markAsDone(Task taskToChange) {
        System.out.println("    Great job! I've marked the following as done" +
                indentationAdder() + taskToChange);
    }

    public void markAsDeleted(Task toDelete) {
        System.out.println("    Note: I've removed the following task from your list:" +
                indentationAdder() + toDelete);
    }

    public LocalDateTime dateFormatting(String stringDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(stringDate, dateTimeFormatter);
        return localDate;
    }

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

    public void helpMessage() {
        System.out.println("    The following commands are compatible with our task-planning chatbot!" + indentationAdder() +
                "list:" + indentationAdder() + "provides a list of items in your task list." + indentationAdder() +
                "done {number}:" + indentationAdder() + "ticks the task with that number as done!" + indentationAdder() +
                "delete {number}:" + indentationAdder() + "deletes the task with that number off the list." + indentationAdder() +
                "bye:" + indentationAdder() + "ends the program.");
    }
}
