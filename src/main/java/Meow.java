import java.util.ArrayList;
import java.util.List;

public class Meow {
    private List<String> tasksList = new ArrayList<String>();

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
        tasksList.add(inputTask);
        System.out.println("added: " + inputTask);
    }

    /**
     * Print a list of tasks that the user has added.
     *
     */
    public void displayList() {
        int len = tasksList.size();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                System.out.println(i + 1 + ". " + tasksList.get(i));
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
}
