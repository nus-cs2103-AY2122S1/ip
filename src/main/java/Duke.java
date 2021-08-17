import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner;


    public static void main(String[] args) {
        System.out.println("Hello, I am Duke. What can I do for you?");
        scanner = new Scanner(System.in);
        waitResponse();
        scanner.close();
    }

    private static void waitResponse() {
        String command = Duke.scanner.next();
        String action = Duke.scanner.nextLine().trim();
        handleRequest(command, action);
    }

    private static void handleRequest(String command, String action) {
        try {
            switch (command) {
                case "todo":
                    handleTodo(action);
                    break;
                case "deadline":
                    handleDeadline(action);
                    break;
                case "event":
                    handleEvent(action);
                    break;
                case "list":
                    handleList();
                    break;
                case "done":
                    handleDone(action);
                    break;
                case "bye":
                    handleBye();
                    break;
                default :
                    throw new InvalidInputException("This is an unknown command.");
            }
        } catch (NoActionException | NoTimeException | InvalidInputException | TaskNotFoundException e) {
            System.out.println(e);
            System.out.println("Please try again");
            waitResponse();
        }
    }
    private static void handleTodo(String action) throws NoActionException{
        if (action.length() == 0) {
            throw new NoActionException("Command 'todo' requires a task action");
        }
        Task newTask = new Todo(action);
        tasks.add(newTask);
        System.out.printf(
                "Got it. I've added this task: \n\t %s \nNow you have %d task in the list.%n",
        newTask, tasks.size());
        waitResponse();
    }


    private static void handleDeadline(String action) throws NoActionException, NoTimeException{
        if (action.length() == 0) {
            throw new NoActionException("Command 'deadline' requires a task action");
        }
        String[] deadlineInputs = action.split("/by", 2);
        if (deadlineInputs.length <=1){
            throw new NoTimeException(
                    "Command 'deadline' requires a deadline to be specified. Use /by to specify a deadline.");
        }
        Task newTask = new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim());
        tasks.add(newTask);
        System.out.printf(
                "Got it. I've added this task: \n  %s \nNow you have %d task in the list.%n",
        newTask, tasks.size());
        waitResponse();
    }

    private static void handleEvent(String action) throws NoActionException, NoTimeException{
        if (action.trim().length() == 0) {
            throw new NoActionException("Command 'event' requires a task action");
        }
        String[] eventInputs = action.split("/at", 2);
        if (eventInputs.length <=1){
            throw new NoTimeException(
                    "Command 'event' requires a time to be specified. Use /at to specify a time.");
        }
        Task newTask = new Event(eventInputs[0].trim(), eventInputs[1].trim());
        tasks.add(newTask);
        System.out.printf(
                "Got it. I've added this task:\n\t %s\nNow you have %d task in the list.%n",
        newTask, tasks.size());
        waitResponse();
    }

    private static void handleList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
        waitResponse();
    }

    private static void handleBye() {
        System.out.println("Bye. Hope to see you again!");
    }

    private static void handleDone(String action) throws InvalidInputException, TaskNotFoundException{
        try {
            int taskNumber = Integer.parseInt(action);
            if (taskNumber <= tasks.size()) {
                Task taskToComplete = tasks.get(taskNumber - 1);
                if (!taskToComplete.isComplete()) {
                    taskToComplete.complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskToComplete);
                }else {
                    System.out.println("This task is already completed.");
                }
            } else {
                throw new TaskNotFoundException("the task chosen does not exist. Use 'list' to see all your tasks.");
            }
            waitResponse();
        } catch (NumberFormatException e) {
            throw new InvalidInputException("command 'done' require an integer as the second parameter");
        }
    }
}