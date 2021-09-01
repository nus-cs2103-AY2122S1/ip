package jared.ui;

import java.util.Scanner;

import jared.common.DukeException;
import jared.common.Message;
import jared.parser.Parser;
import jared.task.TaskList;

/**
 * Deals with user interactions.
 */
public class Ui {
    private final Scanner scan;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        scan = new Scanner(System.in);
        showWelcomeMessage();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(Message.MESSAGE_WELCOME);
    }

    /**
     * Prints exit message.
     */
    public void showExitMessage() {
        System.out.println(Message.MESSAGE_EXIT);
    }

    /**
     * Starts the scanner to scan for tasks from user.
     */
    public String runLogic(String input, TaskList tasks) {
        String command = Parser.parseCommand(input);

        try {
            if (command.equals("bye")) {
                showExitMessage();
                System.exit(0);
                scan.close();
            } else if (command.equals("list")) {
                return tasks.list();
            } else if (command.equals("done")) {
                return tasks.done(input);
            } else if (command.equals("delete")) {
                return tasks.delete(input);
            } else if (command.equals("find")) {
                return tasks.find(input);
            } else {
                return tasks.add(command, input);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return "Error";
    }
}
