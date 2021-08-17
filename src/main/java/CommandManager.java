import java.util.List;

public class CommandManager {

    protected CommandManager() {

    }

    public void gettingStart() {
        respondWith("Hello! I'm Duke. \nWhat can I do for you?");
        enterCommand();
    }
    public void respondBye() {
        addSpace();
        System.out.println("Program exiting... \nBye. Hope to see you again soon!\n");
    }
    /**
     * Print all available tasks on the array list
     * @param taskList: List of tasks
     */
    public void respondList(List<Task> taskList) {
        addSpace();
        System.out.println("Here is the list of all tasks: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        addSpace();
        enterCommand();
    }
    public void respondDone(String command, List<Task> taskList) {
        command = command.substring(4).replaceAll("\\s+", "");
        int position = Integer.parseInt(command) - 1;
        Task calledTask = taskList.remove(position);
        calledTask.markAsCompleted();
        taskList.add(position, calledTask);
        respondWith("Nice! I've marked this task as done: \n" + calledTask);
        enterCommand();
    }
    public void respondTodo(String command, List<Task> taskList) {
        command = command.substring(4).trim();
        respondWith("Added: " + command);
        taskList.add(new Todo(command));
        enterCommand();
    }
    public void respondDeadline(String command, List<Task> taskList) {
        command = command.substring(8).trim();
        respondWith("Added: " + command);
        taskList.add(new Deadline(command));
        enterCommand();
    }
    public void respondEvent(String command, List<Task> taskList) {
        command = command.substring(5).trim();
        respondWith("Added: " + command);
        taskList.add(new Event(command));
        enterCommand();
    }
    public void defaultResponse() {
        respondWith("OOPS!!! I'm sorry, but I don't know what that means :-(");
        enterCommand();
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
}
