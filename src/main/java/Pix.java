import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pix {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private static final ArrayList<Task> taskList = new ArrayList<>(0);

    /**
     * Does the logic for registering prompts.
     * @param command The command to be registered.
     */
    private static void EnterCommand(String[] command) throws PixException {
        try {
            switch (command[0]) { //Split into cases depending on the length of the command
                case "bye":
                    if (command.length == 1) {
                        ExitPix();
                    } else {
                        NextCommand();
                        throw new PixUnknownCommandException();
                    }
                    break;
                case "list":
                    if (command.length == 1) {
                        DisplayList();
                    } else {
                        throw new PixUnknownCommandException();
                    }
                    NextCommand();
                    break;
                case "done":
                    if (command.length == 2 && isInteger(command[1])) {
                        CompleteTask(Integer.parseInt(command[1]));
                    } else {
                        throw new PixUnknownCommandException();
                    }
                    NextCommand();
                    break;
                case "todo":
                    ArrayList<String> tempArrayToDo = new ArrayList<>(Arrays.asList(command).subList(1, command.length));
                    String taskName = String.join(" ", tempArrayToDo);
                    AddTask(taskName, TaskType.TODO, "");
                    NextCommand();
                    break;
                case "deadline":
                    int splitterDeadline = -1; //Finds where the "/by" is
                    for (int i = 1; i < command.length; i++) {
                        if (command[i].equals("/by")) {
                            splitterDeadline = i;
                            break;
                        }
                    }

                    if (splitterDeadline != -1) {
                        ArrayList<String> tempTaskName = new ArrayList<>(Arrays.asList(command).subList(1, splitterDeadline));
                        ArrayList<String> tempArray = new ArrayList<>(Arrays.asList(command).subList(splitterDeadline + 1, command.length));
                        String taskNameDeadline = String.join(" ", tempTaskName);
                        String date = String.join(" ", tempArray);
                        AddTask(taskNameDeadline, TaskType.DEADLINE, date);
                    } else { //Cannot find the "/by"
                        throw new PixInvalidTaskException();
                    }

                    NextCommand();
                    break;
                case "event":
                    int splitterEvent = -1; //Finds where the "/at" is
                    for (int i = 1; i < command.length; i++) {
                        if (command[i].equals("/at")) {
                            splitterEvent = i;
                            break;
                        }
                    }

                    if (splitterEvent != -1) {
                        ArrayList<String> tempTaskName = new ArrayList<>(Arrays.asList(command).subList(1, splitterEvent));
                        ArrayList<String> tempArray = new ArrayList<>(Arrays.asList(command).subList(splitterEvent + 1, command.length));
                        String taskNameDeadline = String.join(" ", tempTaskName);
                        String date = String.join(" ", tempArray);
                        AddTask(taskNameDeadline, TaskType.EVENT, date);
                    } else { //Cannot find the "/at"
                        throw new PixInvalidTaskException();
                    }
                    NextCommand();
                    break;
                case "delete":
                    if (command.length == 2 && isInteger(command[1])) {
                        DeleteTask(Integer.parseInt(command[1]));
                    } else {
                        throw new PixUnknownCommandException();
                    }
                    NextCommand();
                    break;
                default:
                    throw new PixUnknownCommandException();
            }
        } catch (PixUnknownCommandException e) {
            System.out.println(e.getMessage());
            NextCommand();
        } catch (PixInvalidTaskException e) {
            System.out.println(e.getMessage());
            NextCommand();
        }
    }

    /**
     * Displays the itemList.
     */
    private static void DisplayList() {
        System.out.println("Why can't you keep track of these yourself:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
    }

    /**
     * Adds the Task to the taskList.
     * @param item The task to be added to the taskList.
     * @param type The type of task to be added.
     * @param date The date/time of the task (if applicable)
     */
    private static void AddTask(String item, TaskType type, String date) throws PixException {
        try {
            if (item.equals("")) {
                throw new PixInvalidTaskException();
            } else {
                switch (type) {
                    case TODO:
                        ToDo toDo = new ToDo(item);
                        taskList.add(toDo);
                        System.out.println(" this task: \n" + toDo);
                        System.out.println("You now have " + taskList.size() + " task(s) in your list");
                        break;
                    case DEADLINE:
                        Deadline deadline = new Deadline(item, date);
                        taskList.add(deadline);
                        System.out.println("Added this task: \n" + deadline);
                        System.out.println("You now have " + taskList.size() + " task(s) in your list");
                        break;
                    case EVENT:
                        Event event = new Event(item, date);
                        taskList.add(event);
                        System.out.println("Added this task: \n" + event);
                        System.out.println("You now have " + taskList.size() + " task(s) in your list");
                        break;
                }
            }
        } catch (PixInvalidTaskException e) {
            System.out.println(e.getMessage());
            NextCommand();
        }
    }

    /**
     * Sets the selected task to be completed.
     * @param n The number of the Task to be completed.
     */
    private static void CompleteTask(int n) {
        try {
            taskList.get(n - 1).CompleteTask();
            System.out.println("Wow. You did it. Yay.");
            System.out.println(taskList.get(n - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't complete what literally isn't there!");
        }
    }

    /**
     * Deletes the selected task from the Task List.
     * @param n The number of the Task to be deleted.
     */
    private static void DeleteTask(int n) {
        try {
            Task taskToDelete = taskList.get(n - 1);
            System.out.println("Given up already? Task removed:");
            System.out.println(taskToDelete);
            taskList.remove(n - 1);
            System.out.println("You now have " + taskList.size() + " task(s) in your list");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't delete what literally isn't there!");
        }
    }

    /**
     * Reads the input from the user and triggers the logic for the command.
     */
    private static void NextCommand() throws PixException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] splitInput = input.split(" ", 0);
        EnterCommand(splitInput);
    }

    /**
     * Closes and exits Pix.
     */
    private static void ExitPix() {
        System.out.println("Please don't come back...");
    }

    /**
     * Checks if the String is an integer.
     * @param str String to be tested.
     */
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) throws PixException {

        System.out.println("This is Pix. Why did you summon me AGAIN...");
        System.out.println("What do want now?");

        NextCommand();
    }
}
