package main.java;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * The Duke program implements an application that helps to track tasks that the user requires to be tracked.
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Duke {

    /**
     * Initialises app.
     * @param args empty args.
     */
    public static void main(String[] args) {
        Ui messages = new Ui();
        TaskList engine = new TaskList();

        messages.welcomeMessage();
        engine.runProgram();
        messages.goodbyeMessage();
    }
}
