import java.util.ArrayList;
import java.util.List;

public class DukeCommandManager {

    private final List<Task> taskList;
    protected DukeCommandManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Process incoming command of Duke - one for all method
     * @param command Command entered by user in Duke
     * @param commandType Type of command entered
     */
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
                    respondTask(command, Task.Type.TODO);
                    break;
                case "deadline":
                    respondTask(command, Task.Type.DEADLINE);
                    break;
                case "event":
                    respondTask(command, Task.Type.EVENT);
                    break;
                case "delete":
                    respondDelete(command);
                    break;
                default:
                    respondWith("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            if (e.type == DukeException.TYPE.OUT_OF_BOUND) {
                respondWith("☹ OOPS!!! Your task number is out of bound!\n" +
                        "To use '" + commandType + "', please enter 'help' for instructions");
            } else if (e.type == DukeException.TYPE.INCOMPLETE){
                respondWith("☹ OOPS!!! Your description of '" + commandType + "' is incomplete!\n" +
                        "To use '" + commandType + "', please enter 'help' for instructions");
            }
        } catch (NumberFormatException e) {
            respondWith("☹ OOPS!!! Your description of '" + commandType + "' is incorrect!\n" +
                    "To use '" + commandType + "', please enter 'help' for instructions");
        }
    }

    /**
     * Initializing Duke
     */
    public void gettingStart() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        respondWith("Hello! I'm Duke. \nType in 'help' if you are new to us.\nWhat can I do for you?");
    }

    /**
     * Exiting Duke
     */
    public void exitProgram() {
        System.out.println("\nProgram exiting... \nBye. Hope to see you again soon!\n");
    }

    /**
     * Getting helps with Duke command
     */
    private void respondHelp() {
        respondWith("Welcome to Duke! Here are a list of commands that you can use:\n" +
                "'help':     Instructions and command formula\n\n" +
                "'list':     Display a list of all current tasks you have\n\n" +
                "'todo':     Add a task to do with no fixed timing\n" +
                "            Formula: 'todo' + <your task name>\n\n" +
                "'deadline': Add a task with a deadline timing to your list\n" +
                "            Formula: 'deadline' + <your task name> + (optional: '/by' + <your deadline>)\n\n" +
                "'event':    Add an event task with starting or fixed timing to your list\n" +
                "            Formula: 'event' + <your task name> + (optional: '/at' + <your event time>)\n\n" +
                "'done':     Mark a specified task in your list as done\n" +
                "            Formula: 'done' + <task index> (To mark all tasks as done, enter 'done all')\n\n" +
                "'delete':   Delete a specified task in your list\n" +
                "            Formula: 'delete' + <task index> (To delete all tasks in list, enter 'delete all')\n\n" +
                "'bye':      Exit the program\n"
        );
    }

    /**
     * Print all currently available tasks in Duke
     */
    private void respondList() {
        String output = "Here is the list of all tasks: \n";
        for (int i = 0; i < taskList.size(); i++) {
            output += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        respondWith(output);
    }

    /**
     * Mark a task by its index as completed.
     * All tasks are marked as completed if entered 'done all'
     * @param command Task command
     */
    private void respondDone(String command) throws DukeException {
        command = command.substring(4).replaceAll("\\s+", "");
        if (command.equals("all")) {
            for (Task task : taskList) {
                task.markAsCompleted();
            }
            respondWith("Nice! I've marked all tasks in your list as done!");
        } else {
            int position = Integer.parseInt(command) - 1;
            if (position >= taskList.size() || position < 0) {
                throw new DukeException("", DukeException.TYPE.OUT_OF_BOUND);
            } else {
                Task calledTask = taskList.remove(position);
                calledTask.markAsCompleted();
                taskList.add(position, calledTask);
                respondWith("Nice! I've marked this task as done: \n" + calledTask);
            }
        }
    }

    /**
     * Delete a task inside the list by its index.
     * All tasks are deleted if entered 'delete all'
     * @param command Task command
     */
    public void respondDelete(String command) {
        command = command.substring(6).replaceAll("\\s+", "");
        if (command.equals("all")) {
            taskList.clear();
            respondWith("Noted. I've reset your list and remove all tasks");
        } else {
            int position = Integer.parseInt(command) - 1;
            if (position >= taskList.size() || position < 0) {
                throw new DukeException("Error", DukeException.TYPE.OUT_OF_BOUND);
            } else {
                Task deletedTask = taskList.remove(position);
                respondWith("Noted. I've removed this task:\n" + deletedTask + "\n Now you have " +
                        taskList.size() + " tasks in the list");
            }
        }
    }

    /**
     * Adding a specified task to the list.
     * There are 3 types of task: TODO, DEADLINE, and EVENT
     * @param command Task command
     * @param type Task type
     */
    private void respondTask(String command, Task.Type type) {
        command = command.split(" ", 2)[1].trim();
        if (command.equals("")) {
            throw new DukeException("Error", DukeException.TYPE.INCOMPLETE);
        } else {
            Task newTask = (type == Task.Type.TODO) ? new Todo(command)
                            : (type == Task.Type.DEADLINE) ? new Deadline(command)
                            : new Event(command);
            taskList.add(newTask);
            respondWith("Got it! I've added this task:\n" + newTask +
                    "\nNow you have " + taskList.size() + " tasks in the list");
        }
    }
    /**
     * Template of respond after each command-reply cycle
     * @param input: Entered command on terminal
     */
    private void respondWith(String input) {
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(input);
        System.out.println();
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("Enter command: ");
    }
}
