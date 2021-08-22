package duke;

import java.util.Scanner;
import java.util.List;

public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\nWhat can I do for you today?");
    }

    public String getNextLine() {
        return this.sc.nextLine();
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showLoadingError() {
        System.out.println("Error loading save file :(");
    }

    public void showAddedMessage(Task task, TaskList taskList) {
        List<Task> savedTasks = taskList.getTasks();
        System.out.println("I've added this task:\n" + task);
        System.out.println("Now you have " + savedTasks.size() + " tasks in the list!");
    }
}
