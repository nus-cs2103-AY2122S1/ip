package ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.Storage;
import task.Task;
import task.Tasklist;

public class Ui {

    private static String breakline = "____________________________________________________________";
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private Tasklist tasklist;

    /**
     * Creates the Ui to interact with user
     * @param storage Storage to hold location of list
     * @param tasklist tasklist to be stored inside storage
     */
    public Ui(Storage storage, Tasklist tasklist) {
        this.storage = storage;
        this.tasklist = tasklist;
    }

    public static void printBreakline() {
        System.out.println(breakline);
    }

    /**
     * Provides the initialization message for the Duke Program
     */
    public static void start() {
        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMsg);
        System.out.println(breakline);
    }

    /**
     * Provides the exit message for the Duke Program
     */
    public static void exit() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(byeMsg);
        System.out.println(breakline);
    }

    /**
     * Reads input from user to execute command
     * @return String of command executed
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.next();
        String input = scanner.nextLine();
        Task task;

        switch (cmd) {
        case "bye":
            storage.save();
            Ui.exit();
            return "bye";
        case "list":
            tasklist.list();
            break;
        case "done":
            int idx = Integer.parseInt(input.trim()) - 1;
            tasklist.setToCompleted(idx);
            break;
        case "todo":
        case "deadline":
        case "event":
            task = Parser.parseStringIntoTask(input, cmd, false);
            tasklist.add(task);
            break;
        case "delete":
            int removedIdx = Integer.parseInt(input.trim());
            tasklist.delete(removedIdx);
            break;
        case "find":
            tasklist.findString(input.trim());
            break;
        default:
            try {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(Ui.breakline);
            }
        }
        return input;
    }

    /**
     * Overloaded method to manually input command
     * @param input Command inputted
     * @return String of command
     */
    public String readCommand(String input) {
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.next();
        Task task;

        switch (cmd) {
        case "bye":
            storage.save();
            Ui.exit();
            return "bye";
        case "list":
            tasklist.list();
            break;
        case "done":
            int idx = Integer.parseInt(input.trim()) - 1;
            tasklist.setToCompleted(idx);
            break;
        case "todo":
        case "deadline":
        case "event":
            task = Parser.parseStringIntoTask(input, cmd, false);
            tasklist.add(task);
            break;
        case "delete":
            int removedIdx = Integer.parseInt(input.trim());
            tasklist.delete(removedIdx);
            break;
        default:
            try {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(Ui.breakline);
            }
        }
        return input;
    }
}
