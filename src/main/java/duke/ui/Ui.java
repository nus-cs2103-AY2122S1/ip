package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    public Ui() {}

    //todo show loading error need?

    public void showWelcome() {
        //        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String message = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(message);
    }

    public void showExit() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
    }

    public void showLine() {
        String divider = "------------------------------------------------------";
        System.out.println(divider);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showDeleteTask(Task removedTask, TaskList tasks) {
        String displayedMessage = "Noted. I've removed this task:\n"
                + "  " + removedTask.toString() + "\n"
                + getTotalTaskString(tasks);
        System.out.println(displayedMessage);
    }

    public String readCommand(Scanner sc) {
        String command = "";
        //if (sc.hasNextLine()) {
            command = sc.nextLine();
        //}
        return  command.trim();
    }

    public void showAddTaskMessage(Task task, TaskList tasks) {
        String successMessage = "Got it. I've added this task:";
        String taskString = task.toString();
        String result = successMessage + "\n"
                + "  " + taskString + "\n"
                + getTotalTaskString(tasks);
        System.out.println(result);
    }

    public void showMarkTaskMessage(Task task) {
        String displayedMessage = "Nice! I've marked this task as done:\n"
                + "  "
                + task.toString();
        System.out.println(displayedMessage);
    }

    public void showTasksWithSearchTerm(TaskList tasks) {
        if (tasks.isEmpty()) {
            String emptyTaskList = "  " + "No task matched the search term!";
            System.out.println(emptyTaskList);
        } else {
            String message = "Here are the matching tasks in your list:";
            System.out.println(message);
            for (int i = 1; i <= tasks.size(); i++) {
                Task currentTask = tasks.getTask(i - 1);
                String displayedTask = i + "." + currentTask.toString();
                System.out.println(displayedTask);
            }
        }
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            String emptyTaskList = "There are currently no tasks yet!";
            System.out.println(emptyTaskList);
        } else {
            String message = "Here are the tasks in your list:";
            System.out.println(message);
            for (int i = 1; i <= tasks.size(); i++) {
                Task currentTask = tasks.getTask(i - 1);
                String displayedTask = i + "." + currentTask.toString();
                System.out.println(displayedTask);
            }
        }
    }

    private int getTotalTaskNumber(TaskList tasks) {
        return tasks.size();
    }

    private String getTotalTaskString(TaskList tasks) {
        return String.format("Now you have %d tasks in the list.", getTotalTaskNumber(tasks));
    }



}
