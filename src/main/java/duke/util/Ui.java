package duke.util;

import java.util.Scanner;

import duke.Duke;

import duke.exceptions.UserInputError;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Ui class that deals with interactions with the user.
 */
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

    /**
     * Constructor to create a Ui instance.
     */
    public Ui(){
        sc = new Scanner(System.in);
    }

    /**
     * Check if there is incoming user input.
     *
     * @return boolean value if there is an input.
     */
    public boolean hasInput() {

        return sc.hasNextLine();
    }

    /**
     * Get the next user input.
     *
     * @return User input string.
     */
    public String nextInput() {

        return sc.nextLine();
    }

    /**
     * Check if Duke should exit.
     *
     * @return Boolean value if Duke is stopped.
     */
    public boolean hasEnded() {

        return isEndChat;
    }

    /**
     * Method to end chat with Duke.
     */
    public void setEndChat() {

        isEndChat = true;
    }

    /**
     * Close the Scanner and exit Duke.
     */
    public void end() {
        Duke.renderOutput("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Greet user when Duke is opened.
     */
    public void greet() {
        String output = "Hello! Welcome to\n" + LOGO + "\nHow may i help you?\n";
        System.out.println(LINE.trim());
        output.lines().forEach(op -> System.out.println("      " + op));
        System.out.println(LINE.trim());
    }

    public void find(String key, TaskList ls) {
        StringBuilder op = new StringBuilder();

        for (int i = 0; i < ls.length(); i++) {
            if(ls.getTask(i).descContains(key)) {
                op.append(ls.getTask(i).toString()).append("\n");
            }
        }
        Duke.renderOutput("Here are the matching tasks in your list:\n" + op);
    }

    /**
     * Output a list of all the current tasks.
     */
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

    /**
     * Mark task as complete.
     *
     * @param index Indicates which task to complete.
     * @throws UserInputError
     */
    public void markTaskComplete(int index) throws UserInputError {
        Task task = Duke.taskList.getTask(index);
        if (task.isDone()) {
            Duke.renderOutput("Great! But you have already completed this task!");
        } else {
            task.markDone();
            Duke.renderOutput("Nice! I've marked this task as done: \n" + task);
        }
    }

    /**
     * Method to add new Task of either type: Todo, Event, Deadline to list.
     *
     * @param input Task description.
     * @param type Enum type of Task.
     * @throws UserInputError
     */
    public void addNewTask(String input, Task.Type type) throws UserInputError {
        Task newTask = Task.createTask(input, type);
        Duke.taskList.addTask(newTask);
        addTaskOutput(newTask);
    }

    /**
     * Format Task to render desired add task output.
     *
     * @param task Task just added.
     */
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

    /**
     * Delete Task from list.
     *
     * @param index Index of task user wants to delete.
     * @throws UserInputError
     */
    public void deleteTask(int index) throws UserInputError {
        Task deleted = Duke.taskList.getTask(index);
        Duke.taskList.deleteTask(index);
        deleteTaskOutput(deleted);
    }

    /**
     * Format Task to render desired delete task output.
     *
     * @param task Task just deleted.
     */
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
