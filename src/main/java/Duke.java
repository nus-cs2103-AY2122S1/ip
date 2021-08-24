package main.java;

import main.java.tasklist.TaskList;
import main.java.ui.Ui;

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
