import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * Gnosis class is the main programme to execute chat-bot assistant.
 * Commands Gnosis can provide a task tracker to user:
 * "list" - displays all tasks
 * "done (task number)" - marks specified task as done
 * "bye" - exits program
 * default - adds user input as task
 *
 * @author Pawandeep Singh
 * @version A-Enums
 *
 * */
public class Gnosis {

    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        String input;
        tasks = new ArrayList<>();

        // Display greeting message
        displayTopDivider();
        System.out.println(GnosisConstants.GREET_MESSAGE);
        displayBottomDivider();

        // terminates user input only when "bye" is inputted by user
        do {
            command = sc.next();
            input = sc.nextLine();

            // display and execute commands
            displayTopDivider();

            try {
                GnosisCommand gc = GnosisCommand.valueOf(command.toUpperCase().trim());
                executeCommand(gc, input.trim());
            } catch (GnosisException ge) {
                System.out.println(ge.toString());
            } catch (NumberFormatException nfe) {
                System.out.println(GnosisConstants.DONE_COMMAND_NUM_INPUT_EXCEPT_MESSAGE);
            } catch (IllegalArgumentException iae ) {
                System.out.println(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
            }
            displayBottomDivider();

        } while (!command.equalsIgnoreCase("BYE"));

        sc.close(); // close scanner
    }


    /**
     * Executes user commands
     * */
    public static void executeCommand(GnosisCommand gc, String input) throws GnosisException,
            NumberFormatException {
        int taskIndex;
        // convert command to lower case to match Enum
        switch (gc) {
            case TODO:
                addTodo(input);
                displayNumOfTasks();
                break;
            case DEADLINE:
                addDeadline(input);
                displayNumOfTasks();
                break;
            case EVENT:
                addEvent(input);
                displayNumOfTasks();
                break;
            case LIST:
                listTasks();
                break;
            case DONE:
                // only if "done" command is call, we retrieve task index from user
                taskIndex = Integer.parseInt(input.trim()) - 1;
                MarkTaskAsDone(taskIndex);
                break;
            case DELETE:
                taskIndex = Integer.parseInt(input.trim()) - 1;
                deleteTask(taskIndex);
                displayNumOfTasks();
                break;
            case BYE:
                displayByeMessage();
                break;
            default:
                throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }
    }

    //Corresponding user command methods

    public static void addTodo(String todo) throws GnosisException {
        if (todo.trim().equalsIgnoreCase("")) {
            // t0do empty exception
            throw new GnosisException(GnosisConstants.TODO_EMPTY_EXCEPT_MESSAGE);
        }
        Todo td = new Todo(todo);
        tasks.add(td);
        System.out.println("Todo added:");
        System.out.println(td);
    }

    public static void addDeadline(String deadlineInput) throws GnosisException {
        String[] splitTaskInput = deadlineInput.split("/by");
        if (splitTaskInput.length != 2) {
            //deadline empty exception
            throw new GnosisException(GnosisConstants.DEADLINE_EMPTY_EXCEPT_MESSAGE);
        }
        String taskName = splitTaskInput[0];
        String taskDeadline = splitTaskInput[1];

        Deadline dl = new Deadline(taskName, taskDeadline);
        tasks.add(dl);
        System.out.println("Deadline added:");
        System.out.println(dl);

    }

    public static void addEvent(String eventInput) throws GnosisException {
        String[] splitTaskInput = eventInput.split("/at");
        if (splitTaskInput.length != 2) {
            //event empty exception
            throw new GnosisException(GnosisConstants.EVENT_EMPTY_EXCEPT_MESSAGE);
        }
        String taskName = splitTaskInput[0];
        String taskSchedule = splitTaskInput[1];

        Event et = new Event(taskName,taskSchedule);
        tasks.add(et);
        System.out.println("Event added:");
        System.out.println(et);
    }

    public static void MarkTaskAsDone(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }
        tasks.get(taskIndex).setDone(true);
        System.out.println("Task " + (taskIndex+1) +" marked as done:" );
        System.out.println("\t" + tasks.get(taskIndex));
    }

    public static void deleteTask(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }
        Task t = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        System.out.println("Understood. Task has been deleted:");
        System.out.println(t);
    }

    public static void listTasks() {
        int len = tasks.size();
        System.out.println("Listing all tasks in your list:");
        for (int i = 0; i < len; i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    //Utility methods for output

    public static void displayTopDivider() {
        System.out.println(GnosisConstants.DISPLAY_FORMAT);
    }
    public static void displayBottomDivider() {
        System.out.println(GnosisConstants.DISPLAY_FORMAT + '\n');
    }
    public static void displayByeMessage() {
        System.out.println(GnosisConstants.BYE_MESSAGE);
    }
    public static void displayNumOfTasks() {
        System.out.println("Total tasks in the list: " + tasks.size());
    }
}
