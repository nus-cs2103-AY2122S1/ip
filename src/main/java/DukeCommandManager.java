import java.util.ArrayList;
import java.util.List;

public class DukeCommandManager {

    private List<Task> taskList;

    protected DukeCommandManager() {
        this.taskList = new ArrayList<>();
    }

    public void gettingStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        respondWith("Hello! I'm Duke. \nWhat can I do for you?");
        enterCommand();
    }
    public void respondBye() {
        addSpace();
        System.out.println("Program exiting... \nBye. Hope to see you again soon!\n");
    }

    public void processCommand(String command, String commandType) {
        try {
            switch (commandType) {
                case "help":
                    respondHelp();
                    break;
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
                    throw new DukeException("", DukeException.exceptionType.INVALID);
            }
        } catch (DukeException e) {
            if (e.type == DukeException.exceptionType.OUT_OF_BOUND) {
                respondWith("☹ OOPS!!! Your task number is out of bound!\n" +
                        "To use " + commandType + ", please enter '\"help'\" for instructions");
            } else if (e.type == DukeException.exceptionType.INCOMPLETE){
                respondWith("☹ OOPS!!! Your description of a " + commandType + " is incorrect!\n" +
                        "To use " + commandType + ", please enter '\"help'\" for instructions");
            } else if (e.type == DukeException.exceptionType.INVALID) {
                respondWith("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            enterCommand();
        }
    }


    private void respondHelp() {
        respondWith("Welcome to Duke! Here are a list of commands that you can use:\n" +
                "'\"help'\": Getting start with using Duke\n" +
                "'\"list'\": Display a list of all current tasks you have done\n" +
                "'\"todo'\": Add a TO_DO type of task on the list, no deadlines or time\n" +
                "      Regex: todo + <your task name>\n" +
                "'\"deadline'\": Add a task with a deadline timing to your list\n" +
                "      Regex: deadline + <your task name> + /by + <your deadline>\n" +
                "'\"event'\": Add an event task with starting or fixed timing to your list\n" +
                "      Regex: event + <your task name> + /at + <your deadline>\n" +
                "'\"done'\": Mark a specified task in your list as done\n" +
                "      Regex: done + <task number or all>\n" +
                "'\"delete'\": Delete a specified task in your list\n" +
                "      Regex: delete + <task number or all>\n" +
                "'\"bye'\": Exit the program\n" +
                "Enjoy :D"
        );
        enterCommand();
    }

    /**
     * Print all available tasks on the array list
     */
    private void respondList() {
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
    private void respondDone(String command) throws DukeException {
        command = command.substring(4).replaceAll("\\s+", "");
        try {
            int position = Integer.parseInt(command) - 1;
            if (position >= taskList.size() || position < 0) {
                throw new DukeException("", DukeException.exceptionType.OUT_OF_BOUND);
            } else {
                Task calledTask = taskList.remove(position);
                calledTask.markAsCompleted();
                taskList.add(position, calledTask);
                respondWith("Nice! I've marked this task as done: \n" + calledTask);
                enterCommand();
            }
        } catch (NumberFormatException e) {
            respondWith("☹ OOPS!!! Your description of " + "done" + " is incorrect!\n" +
                    "To use " + "done" + ", please enter '\"help'\" for instructions");
        }
    }

    /**
     *
     * @param command
     */
    public void respondDelete(String command) {
        command = command.substring(6).replaceAll("\\s+", "");
        try {
            int position = Integer.parseInt(command) - 1;
            if (position >= taskList.size() || position < 0) {
                throw new DukeException("", DukeException.exceptionType.OUT_OF_BOUND);
            } else {
                Task deletedTask = taskList.remove(position);
                respondWith("Noted. I've removed this task:\n" + deletedTask + "\n Now you have " +
                        taskList.size() + " tasks in the list");
            }
            enterCommand();
        } catch (NumberFormatException e) {
            respondWith("☹ OOPS!!! Your description of " + "delete" + " is incorrect!\n" +
                    "To use " + "delete" + ", please enter '\"help'\" for instructions");
        }
    }

    /**
     *
     * @param command
     */
    private void respondTodo(String command) throws DukeException {
        command = command.substring(4).trim();
        if (command.equals("")) {
            throw new DukeException("Error: ", DukeException.exceptionType.INCOMPLETE);
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
    private void respondDeadline(String command) throws DukeException {
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
     */
    private void respondEvent(String command) throws DukeException {
        int time = command.lastIndexOf("/at");
        if (time == -1) time = command.length() - 1;
        String taskName = command.substring(5, time).trim();
        String taskTime = command.substring(time + 3).trim();
        Task newTask = new Event(taskName, taskTime);
        taskList.add(newTask);
        if (taskName.equals("")) {
            throw new DukeException("", DukeException.exceptionType.INCOMPLETE);
        }
        respondWith("Got it! I've added this task:\n" + newTask +
                "\nNow you have " + taskList.size() + " tasks in the list");
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
