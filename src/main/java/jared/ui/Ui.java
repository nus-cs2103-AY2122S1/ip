package jared.ui;

import jared.common.DukeException;
import jared.common.Message;
import jared.parser.Parser;
import jared.task.TaskList;

/**
 * Deals with user interactions.
 */
public class Ui {

    /**
     * Constructor for Ui.
     */
    public Ui() {
        showWelcomeMessage();
    }

    /**
     * Prints welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println(Message.MESSAGE_WELCOME);
    }

    /**
     * Starts the scanner to scan for tasks from user.
     */
    public String runLogic(String input, TaskList tasks) {
        String command = Parser.parseCommand(input);
        try {
            if (command.equals("bye")) {
                return Message.MESSAGE_EXIT;
            } else if (command.equals("list")) {
                return tasks.list();
            } else if (command.equals("done")) {
                return tasks.done(input);
            } else if (command.equals("delete")) {
                return tasks.delete(input);
            } else if (command.equals("find")) {
                return tasks.find(input);
            } else if (command.equals("sort")) {
                return tasks.sort();
            } else if (command.equals("help")) {
                return tasks.help();
            } else {
                return tasks.add(command, input);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
