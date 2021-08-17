import java.util.List;

public class Presentation {

    protected Presentation() {

    }

    /**
     * Add a horizontal dash line in spaces between commands
     */
    protected void addSpace() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Respond something after a command is entered
     * @param input: Entered command on terminal
     */
    protected void respondWith(String input) {
        addSpace();
        System.out.println(input);
        addSpace();
    }

    /**
     * Buffer introduction before each command entered
     */
    protected void enterCommand() {
        System.out.print("Enter command: ");
    }

    /**
     * Print all available tasks on the array list
     * @param taskList: List of tasks
     */
    protected void printTaskList(List<Task> taskList) {
        addSpace();
        System.out.println("Here is the list of all tasks: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        addSpace();
    }
}
