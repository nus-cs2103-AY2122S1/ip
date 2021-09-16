package duke;

import java.util.Scanner;

/**
 * Class deals with the Ui.
 * Does the different action depend on what the use types.
 * It deals with the design (e.g. the logo and the divider line).
 */
public class Ui {
    String logo;
    Storage storage;
    TaskList tasks;
    Scanner scanner = new Scanner(System.in);

    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.tasks = taskList;
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    /**
     * Draws line.
     */
    public void drawLine() {
        System.out.println("___________________________________________\n");
    }

    /**
     * Get the input from user.
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Greets the user.
     */
    public String greet() {
        String response = "";
        response += logo;
        response += "Hello! I'm Duke\n" + "What can I do for you?\n";
        return response;
    }

    /**
     * Save the final list to the file and bid user farewell.
     */
    public String goodbye() {
        storage.write(tasks.getTaskList(), storage.getFilePath());
        String response = "Bye. Hope to see you again soon!";
        return response;
    }

    /**
     * Displays the all the information about task in the list.
     */
    public String listTasks() {
        String response;
        response = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.length(); i++) {
            response += "\t" + (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        return response;
    }

    /**
     * Mark the task at specific index as done.
     *
     * @param taskNumber Index of the task you want to mark as done.
     */
    public String markDone(int taskNumber) {
        try {
            tasks.getTask(taskNumber).markAsDone();
            String response = "Nice! I've marked this task as done:\n";
            tasks.getTask(taskNumber).markAsDone();
            storage.write(tasks.getTaskList(), storage.getFilePath());
            response += "\t[" + tasks.getTask(taskNumber).getStatusIcon() + "] "
                    + tasks.getTask(taskNumber).getDescription() + "\n";
            return response;
        } catch (ArrayIndexOutOfBoundsException e) {
            return e.toString();
        }

    }

    /**
     * Deletes the task at specific index.
     *
     * @param taskNumber Index of the task you want to delete.
     */
    public String delete(int taskNumber) {
        String response = "Noted. I've removed this task:\n";
        response += "\t" + tasks.getTask(taskNumber) + "\n";
        tasks.deleteTask(taskNumber);
        storage.write(tasks.getTaskList(), storage.getFilePath());
        response += "Now you have " + tasks.length() + " in the list.\n";
        return response;
    }

    /**
     * Adds task to the list.
     *
     * @param task Task that you want to add.
     */
    public String add(Task task) {
        tasks.addTask(task);
        storage.write(tasks.getTaskList(), storage.getFilePath());
        String response = "Got it. I've added this task:\n";
        response += "\t" + task + "\n";
        response += "Now you have " + tasks.length() + " tasks in the list.\n";
        return response;
    }

    public String findTasks(String s) {
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.length(); i++) {
            if (tasks.getTask(i).getDescription().contains(s)) {
                response += "\t" + (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
            }
        }
        return response;
    }
}
