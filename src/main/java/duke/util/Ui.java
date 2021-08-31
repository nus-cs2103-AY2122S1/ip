package duke.util;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Ui class that deals with interactions with the user.
 */
public class Ui {
    private static final String INDENT = "      ";
    private static final String LINE =
            "     _______________________________________\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private TaskList taskList;

    public Ui(TaskList tasklist) {
        this.taskList = tasklist;
    }

    /**
     * Wrapper to render Duke output in a consistent format.
     *
     * @param str String representing Duke output.
     */
    public String formatOutput(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE);
        str.lines().forEach(line -> sb.append("         ").append(line).append('\n'));
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Format Task to render desired add task output.
     *
     * @param task Task just added.
     */
    public String addTaskOutput(Task task) {
        String output =
                "Got it. I've added this task:\n"
                        + INDENT
                        + task.toString()
                        + "\nNow you have "
                        + taskList.length()
                        + " tasks in the list.";
        return formatOutput(output);
    }

    /**
     * Format Task to render desired delete task output.
     *
     * @param task Task just deleted.
     */
    public String deleteTaskOutput(Task task) {
        String output =
                "Noted. I've removed this task:\n"
                        + INDENT
                        + task.toString()
                        + "\nNow you have "
                        + taskList.length()
                        + " tasks in the list.";
        return formatOutput(output);
    }

//    /**
//     * Greet user when Duke is opened.
//     */
//    public void greet() {
//        String output = "Hello! Welcome to\n" + LOGO + "\nHow may i help you?\n";
//        System.out.println(LINE.trim());
//        output.lines().forEach(op -> System.out.println("      " + op));
//        System.out.println(LINE.trim());
//    }

//    /**
//     * Find all tasks who's description contains the keyword users are looking for.
//     *
//     * @param key keyword
//     * @param ls users tasks
//     */
//    public String find(String key, TaskList ls) {
//        StringBuilder op = new StringBuilder();
//
//        for (int i = 0; i < ls.length(); i++) {
//            if (ls.getTask(i).descContains(key)) {
//                op.append(ls.getTask(i).toString()).append("\n");
//            }
//        }
//        return Duke.renderOutput("Here are the matching tasks in your list:\n" + op);
//    }
//
//    /**
//     * Output a list of all the current tasks.
//     */
//    public String renderList() {
//        return Duke.taskList.toString();
//    }
//

//
//    /**
//     * Mark task as complete.
//     *
//     * @param index Indicates which task to complete.
//     * @throws UserInputError
//     */
//    public String markTaskComplete(int index) throws UserInputError {
//        Task task = Duke.taskList.getTask(index);
//        if (task.isDone()) {
//            return Duke.renderOutput("Great! But you have already completed this task!");
//        } else {
//            task.markDone();
//            return Duke.renderOutput("Nice! I've marked this task as done: \n" + task);
//        }
//    }
//
//    /**
//     * Method to add new Task of either type: Todo, Event, Deadline to list.
//     *
//     * @param input Task description.
//     * @param type Enum type of Task.
//     * @throws UserInputError
//     */
//    public String addNewTask(String input, Task.Type type) throws UserInputError {
//        Task newTask = Task.createTask(input, type);
//        Duke.taskList.addTask(newTask);
//        return addTaskOutput(newTask);
//    }

//
//    /**
//     * Delete Task from list.
//     *
//     * @param index Index of task user wants to delete.
//     * @throws UserInputError
//     */
//    public String deleteTask(int index) throws UserInputError {
//        Task deleted = Duke.taskList.getTask(index);
//        Duke.taskList.deleteTask(index);
//        return deleteTaskOutput(deleted);
//    }
}
