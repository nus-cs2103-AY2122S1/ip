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
    private static void EnterCommand(String[] command) {
        switch (command[0]) { //Split into cases depending on the length of the command
            case "bye":
                if (command.length == 1) {
                    ExitPix();
                } else {
                    UnknownCommand();
                    NextCommand();
                }
                break;
            case "list":
                if (command.length == 1) {
                    DisplayList();
                } else {
                    UnknownCommand();
                }
                NextCommand();
                break;
            case "done":
                if (command.length == 2 && isInteger(command[1])) {
                    CompleteTask(Integer.parseInt(command[1]));
                } else {
                    UnknownCommand();
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
                    UnknownCommand();
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
                    UnknownCommand();
                }
                NextCommand();
                break;
            default:
                UnknownCommand();
                NextCommand();
        }
    }

    /**
     * Displays the itemList.
     */
    private static void DisplayList() {
        System.out.println("ヽ(ｏ`皿′ｏ)ﾉ Why can't you keep track of these yourself:");
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
    private static void AddTask(String item, TaskType type, String date) {
        if (item.equals("")) {
            UnknownCommand();
        } else {
            switch (type) {
                case TODO:
                    ToDo toDo = new ToDo(item);
                    taskList.add(toDo);
                    System.out.println("(｀д´)ゝ Added this task: \n" + toDo);
                    System.out.println("You have " + taskList.size() + " task(s) in your list");
                    break;
                case DEADLINE:
                    Deadline deadline = new Deadline(item, date);
                    taskList.add(deadline);
                    System.out.println("(｀д´)ゝ Added this task: \n" + deadline);
                    System.out.println("You have " + taskList.size() + " task(s) in your list");
                    break;
                case EVENT:
                    Event event = new Event(item, date);
                    taskList.add(event);
                    System.out.println("(｀д´)ゝ Added this task: \n" + event);
                    System.out.println("You have " + taskList.size() + " task(s) in your list");
                    break;
            }
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
            System.out.println("┻━┻ミ＼（≧ロ≦＼） You can't complete what literally isn't there!");
        }
    }

    /**
     * Reads the input from the user and triggers the logic for the command.
     */
    private static void NextCommand() {
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

    /**
     * Signals to the user that the command does not exist.
     */
    private static void UnknownCommand() {
        System.out.println("Unknown Command: Do you even know what you're typing..?");
    }

    public static void main(String[] args) {

        System.out.println("(╬≖_≖) This is Pix. Why did you summon me AGAIN...");
        System.out.println("ლಠ益ಠ)ლ What do want now?");

        NextCommand();
    }
}
