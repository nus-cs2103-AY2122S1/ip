package duke.util;

import java.util.Scanner;

import duke.Duke;

import duke.exceptions.UserInputError;

import duke.tasks.Task;

public class Ui {
    private static final String INDENT = "      ";
    private static final String LINE =
            "     ____________________________________________________________\n";
    private static final String LOGO = " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner sc;
    private boolean isEndChat = false;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public boolean hasInput() {

        return sc.hasNextLine();
    }

    public String nextInput() {

        return sc.nextLine();
    }

    public boolean hasEnded() {

        return isEndChat;
    }

    public void setEndChat() {

        isEndChat = true;
    }

    public void end() {
        Duke.renderOutput("Bye. Hope to see you again soon!");
        sc.close();
    }

    public void greet() {
        String output = "Hello! Welcome to\n" + LOGO + "\nHow may i help you?\n";
        System.out.println(LINE.trim());
        output.lines().forEach(op -> System.out.println("      " + op));
        System.out.println(LINE.trim());
    }

    public void renderList() {
        StringBuilder op = new StringBuilder();
        for (int i = 0; i < Duke.taskList.length(); i++) {
            op
                    .append(i + 1)
                    .append(". ")
                    .append(Duke.taskList.getTask(i).toString())
                    .append("\n");
        }
        Duke.renderOutput("Here are the tasks in your list:\n" + op);
    }

    public void markTaskComplete(int index) throws UserInputError {
        Task task = Duke.taskList.getTask(index);
        if (task.isDone()) {
            Duke.renderOutput("Great! But you have already completed this task!");
        } else {
            task.markDone();
            Duke.renderOutput("Nice! I've marked this task as done: \n" + task);
        }
    }

    public void addNewTask(String input, Task.Type type) throws UserInputError {
        Task newTask = Task.createTask(input, type);
        Duke.taskList.addTask(newTask);
        addTaskOutput(newTask);
    }

    protected void addTaskOutput(Task task) {
        String output =
                "Got it. I've added this task:\n" +
                        INDENT +
                        task.toString() +
                        "\nNow you have " +
                        Duke.taskList.length() +
                        " tasks in the list.";
        Duke.renderOutput(output);
    }

    public void deleteTask(int index) throws UserInputError {
        Task deleted = Duke.taskList.getTask(index);
        Duke.taskList.deleteTask(index);
        deleteTaskOutput(deleted);
    }

    protected void deleteTaskOutput(Task task) {
        String output =
                "Noted. I've removed this task:\n" +
                        INDENT +
                        task.toString() +
                        "\nNow you have " +
                        Duke.taskList.length() +
                        " tasks in the list.";
        Duke.renderOutput(output);
    }

}
