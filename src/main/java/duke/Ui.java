package duke;

import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private String command;

    public void showError(String error) {
        System.out.println(error);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you?\n";
        showLine();
        System.out.println(message);
        showLine();
        /**if(size() == 0) {
            System.out.println("There are no tasks");
            return null;
        } else {
            System.out.println("im here!!");
            printFile("data/duke.txt");
            return nill;
        }*/
    }

    public void showLine() {
        System.out.println("\n______________________________________________________________\n");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.command = input;
        return this.command;
    }

    public void respondToList(ArrayList<Task> tasks) {
        System.out.println(("\tHere are the tasks in your list:"));
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void respondToDone(ArrayList<Task> tasks, Integer taskNumber) {
        System.out.println("\tNice! I've marked this task as done: \n \t \t" +
                " [" + tasks.get(taskNumber - 1).getStatusIcon() + "] " + tasks.get(taskNumber - 1).getDescription());
    }

    public void respondToDelete(ArrayList<Task> tasks, Integer taskNumber) {
        System.out.println("\tNoted. I've removed this task: \n\t\t" + tasks.get(taskNumber).toString() +
                "\n\tNow you have " + Integer.toString(tasks.size() - 1) + " tasks in the list.");
    }

    public void respondToTodo(ArrayList<Task> tasks, ToDo todo) {
        System.out.println("\t" + "Got it. I've added this task: " + "\n\t\t" + todo.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public void respondToEvent(ArrayList<Task> tasks, Event event) {
        System.out.println("\tGot it. I've added this task: " + "\n\t\t" + event.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public void respondToDeadline(ArrayList<Task> tasks, Deadline deadline) {
        System.out.println("\t" + "Got it. I've added this task: " + "\n\t\t" + deadline.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public void respondToBlah() {
        System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void respondToBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

}
