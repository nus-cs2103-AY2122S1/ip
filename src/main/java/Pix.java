import java.util.ArrayList;
import java.util.Scanner;

public class Pix {
    private static ArrayList<Task> taskList = new ArrayList<Task>(0);

    /**
     * Does the logic for registering prompts.
     * @param command The command to be registered.
     */
    private static void EnterCommand(String[] command) {
        switch (command.length) {
            case 1:
                if (command[0].equals("list")) {
                    DisplayList();
                    NextCommand();
                } else if (command[0].equals("bye")) {
                    ExitPix();
                } else {
                    AddToList(String.join(" ", command));
                    NextCommand();
                }
                break;
            case 2:
                if (command[0].equals("done")) {
                    if (isInteger(command[1])) {
                        CompleteTask(Integer.parseInt(command[1]));
                        NextCommand();
                    }
                } else {
                    AddToList(String.join(" ", command));
                    NextCommand();
                }
                break;
            default:
                AddToList(String.join(" ", command));
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
     */
    private static void AddToList(String item) {
        Task newTask = new Task(item);
        taskList.add(newTask);
        System.out.println("(｀д´)ゝ Do this stupid thing: " + newTask);
    }

    /**
     * Sets the selected task to be completed.
     * @param n The
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
        System.out.println("Do you even know what you're typing..?");
    }

    public static void main(String[] args) {

        System.out.println("(╬≖_≖) This is Pix. Why did you summon me AGAIN...");
        System.out.println("ლಠ益ಠ)ლ What do want now?");

        NextCommand();
    }
}
