package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interations with user
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    public void list(TaskList taskList) {
        ArrayList<Task> savedInputs = taskList.getTasks();
        StringBuilder outputList = new StringBuilder();
        outputList.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= savedInputs.size(); i++) {
            outputList.append(i + "." + savedInputs.get(i - 1).toString() + "\n");
        }
        System.out.println(outputList);
    }

    public void done(TaskList taskList, int donePos) {
        System.out.println("Nice! I've marked this task as done:\n  " +
                taskList.getTasks().get(donePos - 1).toString());
    }

    public void delete(TaskList taskList, int deletePos) {
        System.out.println("Noted. I've removed this task:\n  " + taskList.getTasks().get(deletePos - 1).toString());
        System.out.println("Now you have " + (taskList.getTasks().size() - 1) + " tasks in the list.");
    }

    public void checkDate(TaskList taskList, LocalDate localDate) {
        ArrayList<Task> matchingDates = taskList.checkDate(localDate);

        String output = "";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        System.out.println(output);
    }

    public void addEvent(TaskList taskList, Event event) {
        System.out.println("Got it. I've added this task:\n  " + event);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void addDeadline(TaskList taskList, Deadline deadline) {
        System.out.println("Got it. I've added this task:\n  " + deadline);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void addTodo(TaskList taskList, Todo todo) {
        System.out.println("Got it. I've added this task:\n  " + todo);
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void invalidUserInput() throws DukeException {
        throw new DukeException("Oops! I'm sorry, but I don't know what that means :-(");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void find(TaskList taskList, String word) {
        ArrayList<Task> matchingDates = taskList.find(word);

        String output = "Here are the matching tasks in your list: \n";
        for (int i = 1; i <= matchingDates.size(); i++) {
            output = output + i + ". " + matchingDates.get(i - 1).toString() + "\n";
        }
        System.out.println(output);
    }
}
