package jared.ui;

import jared.common.DukeException;
import jared.common.Message;
import jared.parser.Parser;
import jared.task.TaskList;

import java.util.Scanner;

/**
 * Deals with user interactions.
 */
public class Ui {
    private final Scanner scan;

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
    public void runScanner(TaskList tasks) {
        while (scan.hasNextLine()) {
            String next = scan.nextLine();
            String command = Parser.parseCommand(next);
            try {
                if (command.equals("bye")) {
                    showExitMessage();
                    System.exit(0);
                    scan.close();
                } else if (command.equals("list")) {
                    tasks.list();
                } else if (command.equals("done")) {
                    tasks.done(next);
                } else if (command.equals("delete")) {
                    tasks.delete(next);
                } else {
                    tasks.add(command, next);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
