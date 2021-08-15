import java.util.ArrayList;
import java.util.List;

public class Meow {
    private List<Task> tasksList = new ArrayList<>();

    /**
     * A public constructor to initialize a Meow object.
     *
     */
    public Meow() {

    }

    /**
     * Print the greeting message from the chat bot cat Meow.
     *
     */
    public void greet() {
        System.out.println("Meow: Hi human, I'm your cat Meow~ What can I do for you?");
    }

    /**
     * Print the commands entered by the user.
     *
     * @param input The user input.
     */
    public void echo(String input) {
        System.out.println(input);
    }

    /**
     * Print the goodbye message from the chat bot cat Meow.
     *
     */
    public void exit() {
        System.out.println("Meow: Bye human, see you soon! Your cat Meow is going to sleep now~");
    }

    /**
     * Return a boolean indicating whether the user wants to exit or not
     * by checking user's input. This method is not case-sensitive.
     *
     * @param input The user input.
     * @return A boolean indicating whether the user wants to exit or not.
     */
    public boolean checkExit(String input) {
        String userInput = input.toLowerCase();
        if (userInput.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add the user input to the list of tasks, and print out
     * the task added.
     *
     * @param inputTask The user input for a task to be added.
     */
    public void addTask(String inputTask) {
        Task task = new Task(inputTask);
        tasksList.add(task);
        System.out.println("added: " + task.getDescription());
    }

    /**
     * Print a list of tasks that the user has added.
     *
     */
    public void displayList() {
        int len = tasksList.size();
        if (len > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < len; i++) {
                Task task = tasksList.get(i);
                System.out.println(i + 1 + ". " + "[" + task.getStatusIcon() + "] " + task.getDescription());
            }
        } else {
            System.out.println("Meow: You have not added any tasks yet, please add one now~");
        }
    }

    /**
     * Return a boolean indicating whether the user wants to display
     * a list of tasks added or not by checking user's input.
     * This method is not case-sensitive.
     *
     * @param input The user input.
     * @return A boolean indicating whether the user wants to display
     * a list of tasks added.
     */
    public boolean checkDisplayList(String input) {
        String userInput = input.toLowerCase();
        if (userInput.equals("list")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Complete the specific task as done in the task list
     * based on the task number provided by the user.
     *
     * @param taskNumber The task number that the user wants to mark as done.
     */
    public void completeTask(int taskNumber) {
        Task completedTask = tasksList.get(taskNumber - 1);
        completedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.getDescription());

    }

    /**
     * Check whether the task is able to be marked as done, 0 indicating that
     * this is an invalid task, Integer.MAX_VALUE indicating that this task is
     * not in the task list, any number other than 0 or Integer.MAX_VALUE indicating
     * the task number to be marked as done.
     *
     * @param input The task number that the user wants to mark as done.
     * @return An integer indicating which task to be marked as done.
     */
    public int checkCompleteTask(String input) {
        int infinity = Integer.MAX_VALUE;
        String done = input.substring(0, 4).toLowerCase();
        try {
            if (done.equals("done")) {
                String str = input.substring(5, 6);
                int taskNumber = Integer.parseInt(str);
                if (taskNumber <= tasksList.size()) {
                    return taskNumber;
                } else {
                    System.out.println("Meow: Hi human, the task you want to complete is not in your task list, try entering a correct task number~");
                    return infinity;
                }
            } else {
                return 0;
            }
        } catch (NumberFormatException exception) {
            System.out.println(exception.toString());
            return 0;
        }

    }

    public void addTodo(String todo) {

    }
}
