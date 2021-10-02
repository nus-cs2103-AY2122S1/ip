package ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.Storage;
import javafx.application.Platform;
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
    public static String start() {
        String greetingMsg = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetingMsg);
        System.out.println(breakline);
        return greetingMsg;
    }

    /**
     * Provides the exit message for the Duke Program
     */
    public static String exit() {
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(byeMsg);
        System.out.println(breakline);
        return byeMsg;
    }

    /**
     * Reads input from user to execute command
     * @return String of command executed
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.next();
        String input = scanner.nextLine();
        LogMessage msg = new LogMessage();
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
            task = Parser.parseStringIntoTask(input, cmd, false, msg);
            tasklist.add(task, msg);
            break;
        case "delete":
            int removedIdx = Integer.parseInt(input.trim());
            tasklist.delete(removedIdx);
            break;
        case "find":
            tasklist.findString(input.trim());
            break;
        case "addTag":
            // input should be in the form of taskNum and tagName
            tasklist.addTagToTask(input);
            break;
        case "findTag":
            tasklist.findTag(input);
            break;
        case "viewTag":
            tasklist.viewTagOfTask(input);
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
     * @param rawInput Command inputted
     * @return String of command
     */
    public LogMessage readCommand(String rawInput) {
        String[] cmdAndInput = rawInput.split(" ", 2);
        String cmd = cmdAndInput[0];
        String input = "";
        if (cmdAndInput.length > 1) {
            input = cmdAndInput[1];
        }

        LogMessage msg = new LogMessage();
        Task task;

        switch (cmd) {
        case "bye":
            msg = storage.save();
            msg.add(Ui.exit());
            Platform.exit();
            break;
        case "list":
            msg = tasklist.list();
            break;
        case "done":
            int idx = Integer.parseInt(input.trim()) - 1;
            msg = tasklist.setToCompleted(idx);
            break;
        case "todo":
        case "deadline":
        case "event":
            msg = new LogMessage();
            task = Parser.parseStringIntoTask(input, cmd, false, msg);
            tasklist.add(task, msg);
            break;
        case "delete":
            int removedIdx = Integer.parseInt(input.trim());
            msg = tasklist.delete(removedIdx);
            break;
        case "find":
            msg = tasklist.findString(input.trim());
            break;
        case "addTag":
            // input should be in the form of taskNum and tagName
            msg = tasklist.addTagToTask(input);
            break;
        case "findTag":
            msg = tasklist.findTag(input);
            break;
        case "viewTag":
            msg = tasklist.viewTagOfTask(input);
            break;
        default:
            try {
                String unknownCmdMsg = String.format("OOPS!!! I'm sorry, but I don't know what that means");
                throw new DukeException(unknownCmdMsg);
            } catch (DukeException e) {
                msg.add(e.getMessage());
                System.out.println(e.getMessage());
                System.out.println(Ui.breakline);
            }
        }
        return msg;
    }
}
