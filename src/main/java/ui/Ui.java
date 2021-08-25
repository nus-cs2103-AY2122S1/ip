package ui;

import duke.*;
import task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static String breakline = "____________________________________________________________";
    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private Tasklist tasklist;

    public Ui(Storage storage, Tasklist tasklist) {
        this.storage = storage;
        this.tasklist = tasklist;
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
                tasklist.getTask(idx).setToCompleted();
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
                tasklist.getTask(idx).setToCompleted();
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
