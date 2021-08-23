import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    static String filePath = "data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.toString());
            taskList = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        ui.readInput();

        try {
            storage.saveData(taskList);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }




    /**
     * Returns the message that shows which task is marked as completed.
     * Mark the task indicated by index as completed.
     * @param index the index of task to be marked
     * @param lst the list containing all tasks
     * @return the messages
     */


    /**
     * Returns error messages or a string showing the added task.
     * The input string must follow input format, otherwise error messages will be return.
     * The new task will be created and added to lst.
     * @param input input message
     * @param lst the list containing all tasks
     * @return a string showing the added task and number of tasks
     */
    public static String addList(String input, ArrayList<Task> lst) {
        ArrayList<String> validType = new ArrayList<>(
                Arrays.asList("deadline", "event", "todo"));
        String type = input.split(" ", 2)[0];
        String content;
        if (!validType.contains(type)) {
            return "I'm sorry, but I don't know what that means :-(";
        }
        try {
            content = input.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            return "The description of a todo cannot be empty.";
        }

        if (type.equals("todo")) {
            lst.add(new Todo(content));
        } else if (type.equals("deadline")) {
            String[] strings = content.split(" /by ");
            if (strings.length != 2) {
                return "Please check the format of your deadline.";
            }
            lst.add(new Deadline(content.split(" /by ")[0], content.split( " /by ")[1]));
        } else if (type.equals("event")) {
            String[] strings = content.split(" /at ");
            if (strings.length != 2) {
                return "Please check the format of your event.";
            }
            lst.add(new Event(content.split(" /at ")[0], content.split(" /at ")[1]));
        }

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n"
                + "      " + lst.get(lst.size() - 1).toString() + "\n"
                + "     Now you have " + lst.size() +" tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }











}
