import java.util.ArrayList;
import java.util.List;

public class DukeCommandManager {

    private List<Task> taskList;

    protected DukeCommandManager() {
        this.taskList = new ArrayList<>();
    }

    public void gettingStart() {
        respondWith("Hello! I'm Duke. \nWhat can I do for you?");
        enterCommand();
    }
    public void respondBye() {
        addSpace();
        System.out.println("Program exiting... \nBye. Hope to see you again soon!\n");
    }

    public void respondHelp() {

    }

    public void respondDelete(String command) {
        command = command.substring(6).replaceAll("\\s+", "");
        int position = Integer.parseInt(command) - 1;
        if (position > taskList.size() || position < 0) {
            throw new DukeException("delete", "", DukeException.exceptionType.OUT_OF_BOUND);
        } else {
            Task deletedTask = taskList.remove(position);
            respondWith("Noted. I've removed this task:\n" + deletedTask + "\n Now you have " +
                    taskList.size() + " tasks in the list");
        }
    }

    public void processCommand(String command, String commandType) {
        try {
            switch (commandType) {
                case "list":
                    respondList();
                    break;
                case "done":
                    respondDone(command);
                    break;
                case "todo":
                    respondTodo(command);
                    break;
                case "deadline":
                    respondDeadline(command);
                    break;
                case "event":
                    respondEvent(command);
                    break;
                case "delete":
                    respondDelete(command);
                    break;
                default:
                    defaultResponse();
            }
        } catch (DukeException e) {
            if (e.type == DukeException.exceptionType.OUT_OF_BOUND) {
                respondWith("☹ OOPS!!! Your task number is out of bound!\n" +
                        "To use " + commandType + ", please enter '\"help'\" for instructions");
            } else {
                respondWith("☹ OOPS!!! Your description of a " + commandType + " is incorrect!\n" +
                        "To use " + commandType + ", please enter '\"help'\" for instructions");
            }
            enterCommand();
        }
    }

    /**
     * Print all available tasks on the array list
     */
    public void respondList() {
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
     */
    public void respondDone(String command) throws DukeException {
        command = command.substring(4).replaceAll("\\s+", "");
        int position = Integer.parseInt(command) - 1;
        if (position > taskList.size()) {
            throw new DukeException("done", "", DukeException.exceptionType.OUT_OF_BOUND);
        } else {
            Task calledTask = taskList.remove(position);
            calledTask.markAsCompleted();
            taskList.add(position, calledTask);
            respondWith("Nice! I've marked this task as done: \n" + calledTask);
            enterCommand();
        }
    }

    /**
     *
     * @param command
     */
    public void respondTodo(String command) throws DukeException {
        command = command.substring(4).trim();
        if (command.equals("")) {
            throw new DukeException("todo", "Error: ", DukeException.exceptionType.INCOMPLETE);
        } else {
            Task newTask = new Todo(command);
            taskList.add(newTask);
            respondWith("Got it! I've added this task:\n" + newTask +
                    "\nNow you have " + taskList.size() + " tasks in the list");
        }
        enterCommand();
    }

    /**
     *
     * @param command
     */
    public void respondDeadline(String command) throws DukeException {
        int time = command.lastIndexOf("/by");
        String taskName = command.substring(8, time).trim();
        String taskTime = command.substring(time + 3).trim();
        Task newTask = new Deadline(taskName, taskTime);
        taskList.add(newTask);
        if (taskName.equals("")) {
            throw new DukeException("deadline", "", DukeException.exceptionType.INCOMPLETE);
        }
        respondWith("Got it! I've added this task:\n" + newTask +
                "\nNow you have " + taskList.size() + " tasks in the list");
        enterCommand();
    }

    /**
     *
     * @param command
     */
    public void respondEvent(String command) throws DukeException {
        int time = command.lastIndexOf("/at");
        if (time == -1) time = command.length() - 1;
        String taskName = command.substring(5, time).trim();
        String taskTime = command.substring(time + 3).trim();
        Task newTask = new Event(taskName, taskTime);
        taskList.add(newTask);
        if (taskName.equals("")) {
            throw new DukeException("event", "", DukeException.exceptionType.INCOMPLETE);
        }
        respondWith("Got it! I've added this task:\n" + newTask +
                "\nNow you have " + taskList.size() + " tasks in the list");
        enterCommand();
    }

    public void defaultResponse() {
        respondWith("OOPS!!! I'm sorry, but I don't know what that means :-(");
        enterCommand();
    }


    /**
     * Add a horizontal dash line in spaces between commands
     */
    private void addSpace() {
        System.out.println();
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
