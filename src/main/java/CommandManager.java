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

    /**
     *
     * @param command
     * @param taskList
     */
    public void respondDone(String command, List<Task> taskList) {
        command = command.substring(4).replaceAll("\\s+", "");
        int position = Integer.parseInt(command) - 1;
        Task calledTask = taskList.remove(position);
        calledTask.markAsCompleted();
        taskList.add(position, calledTask);
        respondWith("Nice! I've marked this task as done: \n" + calledTask);
        enterCommand();
    }

    /**
     *
     * @param command
     * @param taskList
     */
    public void respondTodo(String command, List<Task> taskList) {
        command = command.substring(4).trim();
        Task newTask = new Todo(command);
        taskList.add(newTask);
        respondWith("Got it! I've added this task:\n" + newTask +
                "\nNow you have " + taskList.size() + " tasks in the list");
        enterCommand();
    }

    /**
     *
     * @param command
     * @param taskList
     */
    public void respondDeadline(String command, List<Task> taskList) {
        int time = command.lastIndexOf("/by");
        String taskName = command.substring(8, time).trim();
        String taskTime = command.substring(time + 3).trim();
        Task newTask = new Deadline(taskName, taskTime);
        taskList.add(newTask);
        respondWith("Got it! I've added this task:\n" + newTask +
                "\nNow you have " + taskList.size() + " tasks in the list");
        enterCommand();
    }

    /**
     *
     * @param command
     * @param taskList
     */
    public void respondEvent(String command, List<Task> taskList) {
        int time = command.lastIndexOf("/at");
        String taskName = command.substring(5, time).trim();
        String taskTime = command.substring(time + 3).trim();
        Task newTask = new Event(taskName, taskTime);
        taskList.add(newTask);
        respondWith("Got it! I've added this task:\n" + newTask +
                "\nNow you have " + taskList.size() + " tasks in the list");
        enterCommand();
    }

    /**
     *
     */
    public void defaultResponse() {
        respondWith("OOPS!!! I'm sorry, but I don't know what that means :-(");
        enterCommand();
    }


    /**
     * Add a horizontal dash line in spaces between commands
     */
    private void addSpace() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Respond something after a command is entered
     * @param input: Entered command on terminal
     */
    private void respondWith(String input) {
        addSpace();
        System.out.println(input);
        addSpace();
    }

    /**
     * Buffer introduction before each command entered
     */
    private void enterCommand() {
        System.out.print("Enter command: ");
    }
}
